import java.util.ArrayList;
import java.util.Scanner;

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

    private String getPlaceID(int row, int col){
        // return "%-3s" String for better display
        if ((row == 2 && (col == 0 || col == 4)) || (row == 3 && (col == 0 || col == 4))){
            return "   ";
        } else if (row <= 1){    // first two rows
            int placeID = 5 * row + col + 1;
            return String.format("%-3s", Integer.toString(placeID));
        } else {         // last two rows
            int placeID = 10 + 3 * (row - 2) + col;
            return String.format("%-3s", Integer.toString(placeID));
        }
    }

    private int[] positionToIndex(int position){
        // if invalid position, will return {-1, -1} for {row, col}
        int row = -1;
        int col = -1;
        if (position >= 1 && position <= 5){
            row = 0;
            col = position - 1;
        } else if (position >= 6 && position <= 10){
            row = 1;
            col = position - 6;
        } else if (position >= 11 && position <= 13){
            row = 2;
            col = position - 10;
        } else if (position >= 13 && position <= 16){
            row = 3;
            col = position - 13;
        }
        return new int[] {row, col};
    }

    private void displayBoard(){
        // Prints the current state of the grid.
        for (int i = 0; i < this.gameBoard.length; i++){
            for (int j = 0; j < this.gameBoard[0].length; j++){
                Card card = this.gameBoard[i][j];
                if (card != null){
                    System.out.print(card.toString() + "   ");
                } else {
                    // calculate place id to display
                    System.out.print(getPlaceID(i, j) + "   ");
                }
            }
            System.out.print("\n");
        }
    }

    private int getValidInput(String input){
        // Handles user input, including error checking for invalid or taken spots: return -1 for invalid input

        // handle discard situation
        if (input.equals("discard")){
            // check if available discard
            if (discardsRemaining > 0){
                // return a random value larger than 16, but not equal to -1 (means invalid input)
                return 17;
            } else {
                System.out.println("No discarding place remaining!\n Please enter a position number between 1 to 16 to place the card.");
                return -1;
            }

        // handle placing card situation
        } else {
            try {
                int position = Integer.parseInt(input);
                // check whether the position is empty
                int[] index = positionToIndex(position);
                if (this.gameBoard[index[0]][index[1]] == null){
                    return position;
                } else {
                    System.out.println("Position already occupied!\n Please re-enter an empty position number to " +
                            "place the card, or enter 'discard' to discard the card..");
                    return -1;
                }

            } catch (Exception e){
                System.out.println("Invalid position!\n Please re-enter an empty position number without spaces " +
                        "between 1 to 16 to place the card, or enter 'discard' to discard the card.");
                return -1;
            }
        }
    }

    private void placeCard(Card card, int position){
        // Puts the card in the correct [row][col] on the board or handles a discard.

        // get row, col
        int[] cor = positionToIndex(position);
        int row = cor[0];
        int col = cor[1];

        if (row != -1 && col != -1){
            // valid position, place card
            this.gameBoard[row][col] = card;
        } else {
            // discard card
            this.discardsRemaining -= 1;
        }

    }

    private int calculateTotalScore(){
        // Loops through all 4 rows and 5 columns, calls calculateHandScore for each, and sums the results.
        return 1;
    }

    private int calculateHandScore(ArrayList<Card> hand){
        // The core scoring logic for a single hand.
        return 1;
    }

    public static void main(String[] args) {
        BlackjackSolitaire game = new BlackjackSolitaire();
//        // test 1: print empty playboard
//        game.displayBoard();

//        // test 2: place some card and print the playboard
//        Card card1 = new Card("3", "H");
//        Card card2 = new Card("A", "D");
//        Card card3 = new Card("10", "H");
//        game.placeCard(card1, 10);
//        game.placeCard(card2, 13);
//        game.placeCard(card3, 2);
//        game.displayBoard();

//        // test 3: test testing user input
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please place the card 3H: ");
//        String input = scanner.nextLine();
//        int position = game.getValidInput(input);
//        System.out.println("The position is " + position);

    }
}