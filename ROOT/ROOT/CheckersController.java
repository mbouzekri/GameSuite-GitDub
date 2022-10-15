import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class CheckersController {

    
     private Stage stage;
     private Scene scene;
     private Parent root;
     public GridPane grid;
     private boolean firstPieceSelected = false;
     private Board board;
     private int ox;
     private int oy;
    
     
    @FXML
    public void initialize() throws IOException {
        board = new Board();
	Circle circle = new Circle(50.0, Color.BLUE);
	grid.add(circle, 0, 0);
        grid.setStyle("-fx-grid-lines-visible: true");
    }
    

    @FXML
    public void switchToLobby(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mouseEntered(MouseEvent e) {
        Node source = (Node)e.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);

        if (!firstPieceSelected) {
            ox = colIndex;
            oy = rowIndex;
            firstPieceSelected = true;
            System.out.println("first selected");
        } else {
            firstPieceSelected = false;
            if (board.validCapture(ox, oy, (int)colIndex, (int)rowIndex)) {
                board.capture(ox, oy, (int)colIndex, (int)rowIndex);
            } else if (board.validMove(ox, oy, (int)colIndex, (int)rowIndex)) {
                board.move(ox, oy, (int)colIndex, (int)rowIndex);
                System.out.println("Move made");
            }
        }
        System.out.println("col = " + colIndex +" row = " + rowIndex);
    }
    

}
