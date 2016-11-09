package poker;

public class Card {
	private int num;
	private char suit;
	
	
	public Card(int num, char suit) {
		this.num = num;
		this.suit = suit;
	}
	
	
	public int getNum() {
		return num;
	}
	public char getSuit() {
		return suit;
	}
	
	
	//toString - prints Card as normal format: e.g. "Ace of Spades"
	public String toString() {
		String str = "";
				
		if (this.getNum() == 14) {
			str += "Ace of ";
		} else if (this.getNum() == 11) {
			str += "Jack of ";
		} else if (this.getNum() == 12) {
			str += "Queen of ";
		} else if (this.getNum() == 13) {
			str += "King of ";
		} else {
			str += this.getNum() + " of ";
		}
		
		if (this.getSuit() == 'C') {
			str += "Clubs";
		} else if (this.getSuit() == 'D') {
			str += "Diamonds";
		} else if (this.getSuit() == 'H') {
			str += "Hearts";
		} else {
			str += "Spades";
		}
		
		return str;
	}
}