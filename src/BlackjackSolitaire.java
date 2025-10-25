import java.util.ArrayList;

public class BlackjackSolitaire {
    private Deck deck;
    private Card[][] gameBoard;
    private int discardsRemaining;

    public BlackjackSolitaire(){
        // initialize deck and shuffle it
        this.deck = new Deck();
        this.deck.shuffle();
        // initialize gameBoard, 4 rows and 5 cols
        this.gameBoard = new Card[4][5];
        // initialize discardsRemaining
        this.discardsRemaining = 4;
    }

    public void play(){
        // The main game loop that drives everything.
    }

    private void displayBoard(){
        // Prints the current state of the grid.
    }

    private int getValidInput(){
        // Handles user input, including error checking for invalid or taken spots
        return 1;
    }

    private void placeCard(Card card, int position){
        // Puts the card in the correct [row][col] on the board or handles a discard.
    }

    private int calculateTotalScore(){
        // Loops through all 4 rows and 5 columns, calls calculateHandScore for each, and sums the results.
        return 1;
    }

    private int calculateHandScore(ArrayList<Card> hand){
        // The core scoring logic for a single hand.
        return 1;
    }
}