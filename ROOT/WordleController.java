
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
import java.io.IOException;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.lang.StringBuffer;
import java.io.FileOutputStream;
import java.io.FileReader;
import javafx.scene.input.MouseEvent;


public class WordleController {

     private Stage stage;
     private Scene scene;
     private Parent root;
     public GridPane grid;
     public Label infoBar;

     public boolean wordGuessed = false;
     public boolean wr = false;
     public int m = 0;
     public int n = 0;
     Random rand = new Random();
     public String word;

     public Text[][] cells = new Text[6][5];
     public Rectangle[][] rects = new Rectangle[6][5];
    
     @FXML
     public void initialize() throws IOException {
         word = Files.readAllLines(Paths.get("Words.txt")).get(rand.nextInt(2316));
         word = word.toUpperCase();
         System.out.println(word);
         for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 6; j++) {
                 cells[j][i] = new Text("");
                 rects[j][i] = new Rectangle(60, 60);
                 grid.add(rects[j][i], i, j);
                 grid.add(cells[j][i], i, j);
                 grid.setHalignment(cells[j][i], HPos.CENTER);
                 rects[j][i].setFill(Color.web("BCA7CA", 1.0));
                 rects[j][i].setStroke(Color.web("39294E", 1.0));
                 rects[j][i].setStrokeWidth(5);
             }
         }
         grid.setStyle("-fx-vgap: 7;-fx-hgap: 7;");
         grid.setAlignment(Pos.CENTER);
     }


    @FXML
    public void switchToLobby(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showLeaderBoard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LeaderBoardScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showInstructions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WordleInstructions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Method used to reset the game.
     */
    public void resetGame() throws IOException {
        wordGuessed = false;
        infoBar.setText("Guess the WORDLE!");
        word = Files.readAllLines(Paths.get("Words.txt")).get(rand.nextInt(2316));
        word = word.toUpperCase();
        System.out.println(word);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                cells[j][i].setText("");
                m = 0;
                n = 0;
                rects[j][i].setFill(Color.web("BCA7CA", 1.0));
            }
        }
        setRow(0);
        setCol(0);
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        grid.requestFocus();
        if (m < 6) {
            if (event.getCode().isLetterKey()) {
                    setCellText(event);
                    setCol(1);
            }
            if (event.getCode() == KeyCode.BACK_SPACE) {
                    if (!cells[m][n].getText().equals("")) {
                        cells[m][n].setText("");
                    } else {
                        setCol(-1);
                        cells[m][n].setText("");
                    }

            }
            if (event.getCode() == KeyCode.ENTER && !cells[m][4].getText().equals("")) {
                for (int i = 0; i < 5; i++) {
                    setFill(cells[m][i], String.valueOf(word.charAt(i)), i);
                }
                infoBar.setText("Try another word. You have " + (5 - m) + " tries left!");
                checkWord();
                setRow(1);
                setCol(0);
            }
        }
    }

    
    public void setFill(Text txt, String wrd, int i) {
        if (txt.getText().equals(wrd)) {
            rects[m][i].setFill(Color.GREEN);
        } else {
            for (int j = 0; j < 5; j++) {
                if (txt.getText().equals(String.valueOf(word.charAt(j)))) {
                    rects[m][i].setFill(Color.ORANGE);
                }
            }
        }
        if (rects[m][i].getFill() != Color.ORANGE && rects[m][i].getFill() != Color.GREEN) {
            rects[m][i].setFill(Color.GREY);
        }
    }

    /**
     * Method used to change the current row.
     * @param change  [in row]
     */
    public void setRow(int change) {
        if (m < 6) {
            m += change;
        }
    }

    /**
     * Method used to change the current column.
     * @param change  [in column]
     */
    public void setCol(int change) {
        if (change < 0 && n > 0) {
            n += change;
        } else if (change > 0 && n < 4) {
            n += change;
        } else if (change == 0) {
            n = 0;
        }
    }

    /**
     * Method used set the current cell text.
     * @param event  [Keyboard event]
     */
    public void setCellText(KeyEvent event) {
        cells[m][n].setText(event.getText().toUpperCase());
        cells[m][n].setFont(new Font("Arial", 40));
    }


    /**
     * Method used to check the user's guess against the actual word.
     */
    public void checkWord() {
        if (rects[m][0].getFill() == Color.GREEN && rects[m][1].getFill() == Color.GREEN
            && rects[m][2].getFill() == Color.GREEN && rects[m][3].getFill() == Color.GREEN
            && rects[m][4].getFill() == Color.GREEN) {
            wordGuessed = true;
            infoBar.setText("You won! Want to try again?");
            updateStatistics();
        } else if (m == 5) {
            infoBar.setText("You ran out of tries. The word was " + word + ".");
            updateStatistics();
        }

    }

    public void updateStatistics() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("Statistics.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            String stStatistics = Files.readAllLines(Paths.get("Statistics.txt")).get(0);
            String ndStatistics = Files.readAllLines(Paths.get("Statistics.txt")).get(1);

            int winRate = Integer.parseInt(ndStatistics.substring(12));
            int numGames = Integer.parseInt(stStatistics.substring(15));
            if (wordGuessed) {
                winRate++;
                inputStr = inputStr.replace(ndStatistics.substring(0, 12) + (winRate - 1),
                    ndStatistics.substring(0, 12) + winRate);
            }
            numGames++;
            inputStr = inputStr.replace(stStatistics.substring(0, 15) + (numGames - 1),
                stStatistics.substring(0, 15) + numGames);

            FileOutputStream fileOut = new FileOutputStream("Statistics.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }
}
