import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.geometry.HPos;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import java.util.Random;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.lang.StringBuffer;
import java.io.FileOutputStream;
import java.io.FileReader;
import javafx.scene.input.MouseEvent;
import java.util.Random;


public class GuessingGameController {
    @FXML private TextField textField;
    @FXML private Label lastGuess;
    @FXML private Button submitButton;
    @FXML private Label feedbackLabel;
    @FXML private Label instructionLabel;

     private Stage stage;
     private Scene scene;
     private Parent root;
     Random rand = new Random();
     public int numberToGuess = rand.nextInt(1001);
     private int numberOfGuesses = 15;
     private int maxNumber = 1000;
     private boolean isFinished = false;

    public int min = 1;
    public int max = 100;
    public int difficulty;
    public Game currGame = null;

    //easy mode is the default setting
    public boolean easy = true;
    public boolean medium = false;
    public boolean hard = false;


    public void initialize() throws IOException {
        System.out.println(numberToGuess);
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
    public void showInstructions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuessingGameInstructions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void showLeaderBoard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuessingGameLeaderBoard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void onEnter(ActionEvent ae){
    String strGuess = textField.getText();
    setLastGuess(strGuess);
    if (numberOfGuesses == 0) {
        resetGame();
        return;
    } else if (isNumeric(strGuess) == true) {
        evaluateGuess(strGuess);

    } else {
        feedbackLabel.setText("Please enter a valid number and try again.");
    }
    textField.setText("");
    }


    //setting the label above the textfield to the last guess so the user doesn't forget what they put
    @FXML
    void setLastGuess(String guess) {
        lastGuess.setText(guess);

    }

    //checking whether the entered value is a valid number or not
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
        public void setEasy() {

            easy = true;
            medium = false;
            hard = false;
            numberOfGuesses = 15;
            maxNumber = 1000;
            numberToGuess = rand.nextInt(1001);
            System.out.println(numberToGuess);
            feedbackLabel.setText("Difficulty is now set to EASY");
            instructionLabel.setText("The difficulty is set to easy. I am thinking of a number between 1 and 1000. You will have 15 guesses.");
        }

        @FXML
        public void setMedium() {
            easy = false;
            medium = true;
            hard = false;
            numberOfGuesses = 10;
            maxNumber = 10000;
            numberToGuess = rand.nextInt(10001);
            System.out.println(numberToGuess);
            feedbackLabel.setText("Difficulty is now set to MEDIUM");
            instructionLabel.setText("The difficulty is set to medium. I am thinking of a number between 1 and 10000. You will have 10 guesses.");

        }

        @FXML
        public void setHard() {
            easy = false;
            medium = false;
            hard = true;
            numberOfGuesses = 7;
            maxNumber = 100000;
            numberToGuess = rand.nextInt(100001);
            System.out.println(numberToGuess);
            feedbackLabel.setText("Difficulty is now set to HARD");
            instructionLabel.setText("The difficulty is set to hard. I am thinking of a number between 1 and 100000. You will have 7 guesses.");

        }


    @FXML
    public void evaluateGuess(String guess) {
        System.out.println(numberToGuess);

        int guessInt = Integer.parseInt(guess);
        if (guessInt > numberToGuess && numberOfGuesses != 1) {
            setFeedbackLabel("Your guess was too high. You have " + (numberOfGuesses - 1) + " tries left");
            numberOfGuesses--;
        } else if (guessInt < numberToGuess && numberOfGuesses != 1) {
            setFeedbackLabel("Your guess was too low. You have " + (numberOfGuesses - 1) + " tries left");
            numberOfGuesses--;
        } else if (guessInt == numberToGuess) {
            isFinished = true;
            setFeedbackLabel("Your guess was correct!! Press ENTER or click on the RESTART button to play again!");
            numberOfGuesses = 0;
            updateStatistics();
        } else {
            setFeedbackLabel("You have run out of guesses! Press ENTER or click on the RESTART button to play again!");
            numberOfGuesses--;
            updateStatistics();
        }
    }

    void setFeedbackLabel(String str) {
        feedbackLabel.setText(str);
    }

    @FXML
    public void resetGame() {
        if (easy) {
            setEasy();
        } else if (medium) {
            setMedium();
        } else {
            setHard();
        }
        setFeedbackLabel("");
        isFinished = false;
        lastGuess.setText("");
    }
    public void updateStatistics() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("StatisticsGuessingGame.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            String stStatistics = Files.readAllLines(Paths.get("StatisticsGuessingGame.txt")).get(0);
            String ndStatistics = Files.readAllLines(Paths.get("StatisticsGuessingGame.txt")).get(1);

            int winRate = Integer.parseInt(ndStatistics.substring(12));
            int numGames = Integer.parseInt(stStatistics.substring(15));
            if (isFinished) {
                winRate++;
                inputStr = inputStr.replace(ndStatistics.substring(0, 12) + (winRate - 1),
                    ndStatistics.substring(0, 12) + winRate);
            }
            numGames++;
            inputStr = inputStr.replace(stStatistics.substring(0, 15) + (numGames - 1),
                stStatistics.substring(0, 15) + numGames);

            FileOutputStream fileOut = new FileOutputStream("StatisticsGuessingGame.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }



}
