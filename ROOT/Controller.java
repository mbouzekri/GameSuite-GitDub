import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

     private Stage stage;
     private Scene scene;
     private Parent root;

     private Stage stageCredits = new Stage();
     private Scene sceneCredits;
     private Parent rootCredits;

     /**
      * Method to switch to war
      * @param event
      * @throws IOException
      */
     @FXML
     public void switchToWar(MouseEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CheckersMainScene.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     }
    /**
     * Method to switch to wordle
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

    /**
     * Method to switch to guessing game
     * @param event
     * @throws IOException
     */
    public void switchToGuessingGame(MouseEvent event) throws IOException {
      root = FXMLLoader.load(getClass().getResource("GuessingGame.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    /**
     * Method to show the credit scene
     * @param event
     * @throws IOException
     */
    public void showCredits(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreditsScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 
     * Method to exit the game
     * @param event
     * @throws IOException
     */
    public void ExitGame(MouseEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stageCredits.close();
    }


 }
