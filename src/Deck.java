import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards= new ArrayList<Card>();

    public Deck(){
        String[] rankArray = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] suitArray = { "H", "D", "C", "S"};
        for (int i = 0; i < rankArray.length; i++){
            for (int j = 0; j < suitArray.length; j++){
                cards.add(new Card(rankArray[i], suitArray[j]));
            }
        }
    }

    public int getCardSize(){
        return this.cards.size();
    }

    public void shuffle(){
        Collections.shuffle(cards);
        System.out.printf("Shuffle done: %d cards in the deck%n", this.cards.size());
    }

    public Card deal(){
        return this.cards.remove(0);
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        System.out.printf("Get card: %s %n", deck.deal());
        System.out.println("Remaining card size: " + deck.getCardSize());
    }
}