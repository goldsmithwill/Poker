package poker;

public class Player {
	private String name;
	private Card[] hand;
	private int handStrength;
	//this field breaks ties, AKA it shows how high or low a straight or a pair is
	private int relevantHighCard;
	
	public Player(String name) {
		this.name = name;
		this.hand = new Card[5];
		this.handStrength = 0;
	}
	
	
	public String getName() {
		return name;
	}

	public int getHandStrength() {
		return handStrength;
	}

	public int getRelevantHighCard() {
		return relevantHighCard;
	}



	public void dealCard(Deck d, int position) {
		this.hand[position] = d.getTop();
	}
	
	
	//sorts hand from lowest to highest cards (currently selection sort)
	public void sortHand() {
		Card min = null;
		int minIndex = -1;
		for (int i = 0; i < hand.length - 1; i++) {
			//find smallest remaining card
			min = null;
			for (int j = i; j < hand.length; j++) {
				if (min == null || this.hand[j].getNum() < min.getNum()) {
					min = this.hand[j];
					minIndex = j;
				}
			}
			//swap smallest card with position i
			Card temp = this.hand[i];
			this.hand[i] = min;
			this.hand[minIndex] = temp;
		}
	}
	
	
	//goes thru all possible ranks a hand could have
	/* HAND RANKINGS
	 * 1 = high card
	 * 2 = pair
	 * 3 = two pair
	 * 4 = three of a kind
	 * 5 = straight
	 * 6 = flush
	 * 7 = full house
	 * 8 = four of a kind
	 * 9 = straight flush
	 */
	public void rankHand() {
		//getting card values to avoid calling getNum() often
		int num1 = this.hand[0].getNum();
		int num2 = this.hand[1].getNum();
		int num3 = this.hand[2].getNum();
		int num4 = this.hand[3].getNum();
		int num5 = this.hand[4].getNum();
				
		//straight flush - tests for straight and flush at the same time
		for (int i = 1; i < 5; i++) {
			if (this.hand[i].getNum() != this.hand[i - 1].getNum() + 1 
					|| this.hand[i].getSuit() != this.hand[i - 1].getSuit()) {
				break;
			}
			if (i == 4) {
				this.handStrength = 9;
				this.relevantHighCard = num5;
				return;
			}
		}
		//ace-low straight flush
		if (this.isAceLowSF(num1, num2, num3, num4, num5)) {
			this.handStrength = 9;
			this.relevantHighCard = num5;
		}
		
	
		//four of a kind
		if (num1 == num2 && num1 == num3 && num1 == num4) {
			this.handStrength = 8;
			this.relevantHighCard = num4;
			return;
		} else if (num5 == num4 && num5 == num3 && num5 == num2) {
			this.handStrength = 8;
			this.relevantHighCard = num5;
		}
		
		//full house
		if (num1 == num2 && num1 == num3 && num4 == num5) {
			this.handStrength = 7;
			this.relevantHighCard = num1;
			return;
		} else if (num1 == num2 && num5 == num3 && num5 ==num4) {
			this.handStrength = 7;
			this.relevantHighCard = num5;
			return;
		}
		
		//flush
		for (int i = 1; i < 5; i++) {
			if (this.hand[i].getSuit() != this.hand[i - 1].getSuit()) {
				break;
			}
			if (i == 4) {
				this.handStrength = 6;
				this.relevantHighCard = num5;
				return;
			}
		}
		
		//straight
		for (int i = 1; i < 5; i++) {
			if (this.hand[i].getNum() != this.hand[i - 1].getNum() + 1) {
				break;
			}
			if (i == 4) {
				this.handStrength = 5;
				this.relevantHighCard = num5;
				return;
			}
		}
		//ace-low straight
		if (this.isAceLow(num1, num2, num3, num4, num5)) {
			this.handStrength = 5;
			this.relevantHighCard = num5;
		}
		
		//three of a kind
		if (num1 == num2 && num1 == num3) {
			this.handStrength = 4;
			this.relevantHighCard = num1;
			return;
		} else if (num2 == num3 && num2 == num4) {
			this.handStrength = 4;
			this.relevantHighCard = num2;
			return;
		} else if (num3 == num4 && num3 == num5) {
			this.handStrength = 4;
			this.relevantHighCard = num3;
			return;
		}
		
		//two pair
		if (num1 == num2 && num3 == num4) {
			this.handStrength = 3;
			this.relevantHighCard = num4;
			return;
		} else if (num1 == num2 && num4 == num5) {
			this.handStrength = 3;
			this.relevantHighCard = num5;
			return;
		} else if (num2 == num3 && num4 == num5) {
			this.handStrength = 3;
			this.relevantHighCard = num5;
			return;
		}
		
		//pair
		if (num1 == num2) {
			this.handStrength = 2;
			this.relevantHighCard = num2;
			return;
		} else if (num2 == num3) {
			this.handStrength = 2;
			this.relevantHighCard = num3;
			return;
		} else if (num3 == num4) {
			this.handStrength = 2;
			this.relevantHighCard = num4;
			return;
		} else if (num4 == num5) {
			this.handStrength = 2;
			this.relevantHighCard = num5;
			return;
		}
		
		//high card
		this.handStrength = 1;
		this.relevantHighCard = num5;
	}
	
	//helper method for rankHand(): checks for ace-low straight
	public boolean isAceLow(int num1, int num2, int num3, int num4, int num5) {
		if (num1 != 14 || num2 != 2 || num3 != 3 || num4 != 4 || num5 != 5) {
			return false;
		}
		return true;
	}
	
	//helper method for rankHand(): checks for ace-low straight flush
	public boolean isAceLowSF(int num1, int num2, int num3, int num4, int num5) {
		//straight
		if (!isAceLow(num1, num2, num3, num4, num5)) {
			return false;
		}
		//flush
		for (int i = 1; i < 5; i++) {
			if (this.hand[i].getSuit() != this.hand[i - 1].getSuit()) {
				return false;
			}
		}
		return true;
	}
	
	//compareHand() method: if relevant high cards are same, compares hands to find winner
	public Player compareHand(Player other) {
		int length = this.hand.length;
		for (int i = 0; i < length; i++) {
			if (this.hand[length - i].getNum() == other.hand[length - i].getNum()) {
				continue;
			} else if (this.hand[length - i].getNum() > other.hand[length - i].getNum()) {
				return this;
			} else {
				return other;
			}
		}
		return this;
	}
	
	//toString()
	public String toString() {
		String str = "";
		str += this.name + "'s hand: ";
		for (int i = 0; i < this.hand.length; i++) {
			str += this.hand[i] + ", ";
		}
		return str;
	}
}	