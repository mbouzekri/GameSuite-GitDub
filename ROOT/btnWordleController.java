import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import java.nio.file.Files;
import java.nio.file.Paths;

public class btnWordleController {

     private Stage stage;
     private Scene scene;
     private Parent root;
     public Label numGames;
     public Label winRate;

    /**
     * Method to initialize
     * @throws IOException
     */
     public void initialize() throws IOException {
         numGames.setText(Files.readAllLines(Paths.get("Statistics.txt")).get(0).substring(15));
         winRate.setText(Files.readAllLines(Paths.get("Statistics.txt")).get(1).substring(11));
     }

     /**
      * Method to switch back to main wordle game
      * @param event
      * @throws IOException
      */
    public void switchToWordle(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WordleMainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
