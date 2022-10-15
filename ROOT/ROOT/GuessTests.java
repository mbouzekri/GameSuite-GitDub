public class GuessTests {
    public static void main(String[] args) {
        GuessingGame gg = new GuessingGame();
        gg.setHard();
        gg.newGame();
        System.out.println("Guesses before: " + gg.currGame.guessesLeft);
        System.out.println("Mystery Number: " + gg.currGame.randNum);
        while (!gg.currGame.isFinished) {
            gg.makeGuess("55");
        }
        System.out.println("Guesses after: " + gg.currGame.guessesLeft);

        gg.makeGuess(gg.currGame.randNum + "");
    }
}
