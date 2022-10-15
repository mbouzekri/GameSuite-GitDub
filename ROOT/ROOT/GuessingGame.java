import java.lang.Math;

public class GuessingGame {
    public int min = 1;
    public int max = 100;
    public int difficulty;
    public Game currGame = null;

    //easy mode is the default setting
    public boolean easy = true;
    public boolean medium = false;
    public boolean hard = false;

    //inner game class
    public class Game {
        public int randNum;
        public int guessesLeft;
        public boolean isFinished;
        
        //sets up new game variables for reference in GuessingGame class
        public Game(int min, int max, int difficulty) {
            
    
            //difficulty determines number of guesses player has to win
            if (difficulty == 1) {
                guessesLeft = 15;
                max = 1000;
            } else if (difficulty == 2) {
                guessesLeft = 10;
                max = 1500;
            } else {
                guessesLeft = 20;
            }
    
            this.randNum = (int) (Math.random() * (max - min) + min);
            this.isFinished = false;
        }
    
        public int getNum() {
            return this.randNum;
        }
    
        public int getGuesses() {
            return this.guessesLeft;
        }
    
        public void setFinished(boolean isFinished) {
            this.isFinished = isFinished;
        }
    }

    public void newGame() {
        if (easy) {
            difficulty = 0;
        } else if (medium) {
            difficulty = 1;
        } else if (hard) {
            difficulty = 2;
        }
        currGame = new Game(min, max, difficulty);
    }

    public int makeGuess(String strGuess) {
        //initialize guess to guaranteed wrong value (to make the compiler happy)
        int guess = max + 1;

        //checks if game is still going
        if (currGame.isFinished) {
            System.out.println("Game over. Play again");
            return max + 1;
        } else {
            currGame.guessesLeft -= 1;
        }

        //convert String user input to int
        try {
            guess = Integer.parseInt(strGuess);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }

        //negative if guess is lower than mystery num, positive if higher, and 0 if equal
        int isCorrect = guess - currGame.getNum();

        // win state of game
        if (isCorrect == 0) {
            System.out.println("Correct!");
            currGame.setFinished(true);
        } else if (isCorrect < 0) {
            System.out.println("Too Low");
        } else {
            System.out.println("Too High");
        }

        //ends game if out of guesses
        if (currGame.guessesLeft == 0) {
            currGame.setFinished(true);
        }
        //again: return value is negative if guess is lower than mystery num, positive if higher, and 0 if equal
        return isCorrect;
    }

    //sets min and max for random number
    public void setRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void setEasy() {
        easy = true;
        medium = false;
        hard = false;
        System.out.println("Difficulty: EASY");
    }

    public void setMedium() {
        easy = false;
        medium = true;
        hard = false;
        System.out.println("Difficulty: MEDIUM");
    }

    public void setHard() {
        easy = false;
        medium = false;
        hard = true;
        System.out.println("Difficulty: HARD");
    }
}
