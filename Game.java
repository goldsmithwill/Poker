package poker;

public class Game {
	private Player[] players;
	private int numPlayers;
	
	public Game(int numPlayers) {
		//at most 5 players per game
		this.numPlayers = numPlayers;
		this.players = new Player[this.numPlayers];
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player p1) {
		this.players[numPlayers] = p1;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	//deals out the cards in a turn-based style by calling dealCard(),
	//which is in the Player class
	public void deal(Deck d) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < this.numPlayers; j++) {
				players[j].dealCard(d, i);
			}
		}
		for (int i = 0; i < numPlayers; i++) {
			players[i].sortHand();
		}
	}
}