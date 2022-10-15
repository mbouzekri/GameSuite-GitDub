public class Card {
    private int suit; //Clubs = 0; Diamonds = 1; Hearts = 2; Spades = 3;
    private int value; //Jack = 11; Queen = 12; King = 13; Ace = 14;

    public Card(int s, int v){
	if(s >= 0 && s <= 3){
	    suit = s;
	} else {
	    throw new java.lang.IllegalArgumentException("Bad Suit Ya Dingus");
	}
	if(v >= 2 &&  v <= 14){
	    value = v;
	} else {
	    throw new java.lang.IllegalArgumentException("Bad Suit Ya Dingus");
	}
    }

    public int getSuit(){
	return suit;
    }

    public int getValue(){
	return value;
    }

    public boolean equals(Object other){
	if(other instanceof Card){
	    Card o = (Card) other;
	    if(o.value == this.value){
		return true;
	    }
	}
	return false;
    }

    public static void main(String[] args){
	Card a = new Card(2,4);
	Card b = new Card(3,4);
	System.out.println(a.equals(b));
    }
}
