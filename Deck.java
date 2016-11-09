package poker;
import java.util.*;

/*
 * Deck is represented by an ArrayList: shuffle by moving references around,
 * take cards off top of deck by taking off the end of array
 */
public class Deck {
	public ArrayList<Card> d;
	private int size;

	
	public Deck() {
		this.d = new ArrayList<Card>(52);
		Card c = null;
		for (int i = 2; i <= 56; i++) {
			if (i == 15 || i == 29 || i == 43) {
				continue;
			} else if (i <= 14) {
				c = new Card(i, 'C');
			} else if (i <= 28) {
				c = new Card(i - 14, 'D');
		    } else if (i <= 42) {
		    	c = new Card(i - 28, 'H');
		    } else {
		        c = new Card(i - 42, 'S');
		    }
			d.add(c);
		this.size = 52;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	//takes top card off deck and returns it
	public Card getTop() {
		Card c = this.d.remove(this.size - 1);
		this.d.trimToSize();
		this.size = d.size();
		return c;
	}
	
	//shuffle method used by generating random array positions
	public void shuffle() {
		Random rand = new Random();
		int value;
		Card temp;
		for (int i = 0; i < 52; i++) {
			value = rand.nextInt(51);
			//swaps cards in two places
			temp = d.get(i);
			d.set(i, d.get(value));
			d.set(value, temp);
		}
	}
	
	//main method for testing shuffle method/constructor
	public static void main(String[] args) {
		Deck d1 = new Deck();
		System.out.println(Arrays.toString(d1.d.toArray()));
		d1.shuffle();
		System.out.println(Arrays.toString(d1.d.toArray()));
	}
}