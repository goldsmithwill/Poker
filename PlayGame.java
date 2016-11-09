package poker;

import java.util.*;

public class PlayGame {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to 5-card draw!");
		//while loop that continues until "q" is pressed to quit
		//allows for as many games as needed 
		while (true) {
			System.out.println("Press any key for a new game, or \"q\" to quit");
			if (console.next().equals("q")) {
				System.out.println();
				System.out.println("Thanks for playing!");
				break;
			}
			
			System.out.println("How many players? (1-5)");
			int numP = console.nextInt();
			if (numP < 1 || numP > 5) {
				throw new IllegalArgumentException("Invalid number of players");
			}
			Game g = new Game(numP);
			
			
			Player[] pArray = g.getPlayers();
			for (int i = 1; i <= g.getNumPlayers(); i++) {
				System.out.println("Name of player" + i + "?");
				pArray[i - 1] = new Player(console.next());
			}
			
			
			Deck d = new Deck();
			d.shuffle();
			g.deal(d);
			
			
			//ranks hands in the same loop as printing them out
			for (int i = 0; i < g.getNumPlayers(); i++) {;
				System.out.println(pArray[i]);
				System.out.println();
			}
			
			
			//players can choose to draw up to 3 cards, assumes that players pick 
			//different cards to replace
			int numDraws = 0;
			
			for (int i = 0; i < g.getNumPlayers(); i++) {
				System.out.println(pArray[i].getName() + ", how many cards would you like to replace?");
				numDraws = console.nextInt();
				for (int j = 0; j < numDraws; j++) {
					System.out.println("Which card to replace? (Cards 1-5)");
					pArray[i].dealCard(d, console.nextInt() - 1);
				}
				pArray[i].sortHand();
			}
			
			for (int i = 0; i < g.getNumPlayers(); i++) {
				pArray[i].rankHand();
				System.out.println(pArray[i]);
				System.out.println();
			}
			
			
			//takes hand ranks and decides winner
			int bestHandRank = 0;
			int relHighCard = 0;
			Player winner = null;
			
			for (int i = 0; i < g.getNumPlayers(); i++) {
				if (pArray[i].getHandStrength() > bestHandRank) {
					winner = pArray[i];
					bestHandRank = winner.getHandStrength();
					relHighCard = winner.getRelevantHighCard();
				} else if (pArray[i].getHandStrength() == bestHandRank) {
					if (pArray[i].getRelevantHighCard() > relHighCard) {
						winner = pArray[i];
						bestHandRank = winner.getHandStrength();
						relHighCard = winner.getRelevantHighCard();
					} else if (pArray[i].getRelevantHighCard() == relHighCard) {
						winner = pArray[i].compareHand(winner);
						bestHandRank = winner.getHandStrength();
						relHighCard = winner.getRelevantHighCard();
					}
				}
			}
			
			System.out.println(winner.getName() + " is the winner!");
			System.out.println();
		}
		console.close();
	}
}