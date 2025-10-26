import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck deck;
    private Card[][] gameBoard;
    private int discardsRemaining;
    private int cardNum;

    public BlackjackSolitaire(){
        // initialize deck and shuffle it
        this.deck = new Deck();
        this.deck.shuffle();
        // initialize gameBoard, 4 rows and 5 cols
        this.gameBoard = new Card[4][5];
        // initialize discardsRemaining
        this.discardsRemaining = 4;
        // total card number in the gameboard
        this.cardNum = 0;
    }

    public void play(){
        // The main game loop that drives everything.
        int round = 1;
        Scanner scanner = new Scanner(System.in);
        while (this.cardNum < 16){
            Card card = deck.deal();
            System.out.printf("=================== Round %d ===================%n", round);

            System.out.println("Here is the current game board:");
            displayBoard();
            System.out.printf("Please place the card '%s' to an empty place by entering the position number" +
                    ", or entering 'discard' to discard the card (Discards remaining: %d): %n"
                    , card.toString().trim(), this.discardsRemaining);
            String input = scanner.nextLine();
            int position = getValidInput(input);

            // invalid input
            while (position == -1){
                input = scanner.nextLine();
                position = getValidInput(input);
            }

            // valid input, place or discard card
            placeCard(card, position);
            System.out.println("Game board after placing or discarding the card:");
            displayBoard();

            System.out.print("===============================================\n\n");
            round += 1;
        }

        System.out.println("Congratulation! You finished the game. The final game board:");
        displayBoard();
        System.out.println("Calculating the final score...");

        // calculate scores
        int point = calculateTotalScore();
        System.out.printf("%n%nGame over! You scored %d points.", point);
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
        } else if (position >= 14 && position <= 16){
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
                System.out.println("No discarding place remaining! Please enter a position number between 1 to 16 to place the card:");
                return -1;
            }

        // handle placing card situation
        } else {
            try {
                int position = Integer.parseInt(input);

                // 17, 18, 19, 20 -> discard
                if (position >= 17 && position <= 20) {
                    if (discardsRemaining > 0){
                        return position;
                    }
                    System.out.println("No discarding place remaining! Please enter a position number between 1 to 16 to place the card:");
                    return -1;
                }

                int[] index = positionToIndex(position);
                // check valid index
                if (index[0] == -1 || index[1] == -1) {
                    System.out.println("Invalid position! Please enter a position number between 1 to 16 to place the card:");
                    return -1;
                }
                // check whether the position is empty
                if (this.gameBoard[index[0]][index[1]] == null){
                    return position;
                } else {
                    System.out.println("Position already occupied! Please re-enter an empty position number to " +
                            "place the card, or enter 'discard' to discard the card:");
                    return -1;
                }

            } catch (Exception e){
                System.out.println("Invalid position! Please re-enter an empty position number without spaces " +
                        "between 1 to 16 to place the card, or enter 'discard' to discard the card:");
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
            // update cardNum
            this.cardNum ++;
        } else {
            // discard card
            this.discardsRemaining --;
        }

    }

    private int calculateTotalScore(){
        // Loops through all 4 rows and 5 columns, calls calculateHandScore for each, and sums the results.
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<Card> cardList;
        // row scores
        for (int i = 0; i < gameBoard.length; i++){
            cardList = new ArrayList<>(Arrays.asList(gameBoard[i]));
            scores.add(calculateHandScore(cardList));
        }
        // col scores
        for (int c = 0; c < gameBoard[0].length; c++){
            cardList = new ArrayList<>();
            for (int r = 0; r < gameBoard.length; r++){
                Card card = gameBoard[r][c];
                cardList.add(card);
            }
            scores.add(calculateHandScore(cardList));
        }
        // for debug
        // System.out.println(scores);

        // sum the scores
        int totalScore = 0;
        for (int j = 0; j < scores.size(); j ++){
            // Blackjack at index 4 and 8
            int score = scores.get(j);
            if ((j == 4 || j == 8) && score == 21){
                totalScore += 10;
            } else if (score == 21){
                totalScore += 7;
            } else if (score == 20) {
                totalScore += 5;
            } else if (score == 19) {
                totalScore += 4;
            } else if (score == 18) {
                totalScore += 3;
            } else if (score == 17) {
                totalScore += 2;
            }   else if (score <= 16) {
                totalScore += 1;
            } // > 21: BUST += 0
        }
        return totalScore;
    }

    private int calculateHandScore(ArrayList<Card> hand){
        // The core scoring logic for a single hand.
        int score = 0;
        int aceCount = 0;
        for (Card curCard : hand){
            if (curCard != null){
                // record Ace number
                if (curCard.getValue() == 11){
                    aceCount += 1;
                } else {
                    score += curCard.getValue();
                }
            }
        }

        // treat Ace value
        score += aceCount * 11;
        while (score > 21 && aceCount > 0){
            score -= 10;
            aceCount --;
        }
        return score;
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

//        // test 4: play
//        game.play();

        // test 5: scoring
        game.placeCard(new Card("9", "H"), 1);
        game.placeCard(new Card("4", "H"), 2);
        game.placeCard(new Card("7", "H"), 3);
        game.placeCard(new Card("5", "H"), 4);
        game.placeCard(new Card("Q", "H"), 5);
        game.placeCard(new Card("A", "H"), 6);
        game.placeCard(new Card("2", "H"), 7);
        game.placeCard(new Card("4", "H"), 8);
        game.placeCard(new Card("2", "H"), 9);
        game.placeCard(new Card("A", "H"), 10);
        game.placeCard(new Card("7", "H"), 11);
        game.placeCard(new Card("2", "H"), 12);
        game.placeCard(new Card("A", "H"), 13);
        game.placeCard(new Card("3", "H"), 14);
        game.placeCard(new Card("5", "H"), 15);
        game.placeCard(new Card("J", "H"), 16);
        int score = game.calculateTotalScore();
        System.out.println("Total score: " + score);
    }
}