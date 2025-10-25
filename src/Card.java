public class Card {
    // default rank value = "0"
    private String rank = "0";
    private String suit = "";

    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        if (this.rank.equals("A")){
            return 11;
        } else if (this.rank.equals("J") || this.rank.equals("Q") || this.rank.equals("K") || this.rank.length() == 2){
            return 10;
        } else {
            return this.rank.charAt(0) - '0';
        }
    }

    public String toString(){
        return this.rank + this.suit;
    }

    public static void main(String[] args) {
        Card card = new Card("10", "H");
        System.out.println("The card value is " + card.getValue());
        System.out.println("The card is "+ card.toString());
    }
}