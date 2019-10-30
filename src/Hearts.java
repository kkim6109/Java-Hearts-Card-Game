
public class Hearts {
	private Player[] players;
	private Deck cards;
	private int[] roundPoints;
	private Card[] centerCards;
	private int roundNumber, leadRef, winRef, trickPoint;
	private boolean brokeHearts;
	
	public Hearts() {
		players = new Player[4];
		players[0] = new HumanPlayer("");
		players[1] = new ComputerPlayer();
		players[2] = new ComputerPlayer();
		players[3] = new ComputerPlayer();
		
		cards = new Deck();
		roundPoints = new int[4];
		centerCards = new Card[4];
		roundNumber = 1;
	}
	
	public Card[] centerCards() {
		return centerCards;
	}
	
	public Player getPlayer(int ref) {
		return players[ref];
	}
	
	public int getLead() {
		return leadRef;
	}

	public void deal() {
		roundPoints = new int[4];
		cards.shuffle();
		brokeHearts = false;
		
		for (int i = 0; i < 52; i++)
			players[i%4].addCard(cards.deal());
		
		for (int i = 0; i < players.length; i++)
			if (players[i].has2Clubs())
				leadRef = i;
	}
	
	public void passCards() {
		int direc = roundNumber % 4;
		Card[][] passing = new Card[players.length][];
		
		if (direc != 0) {
			for (int i = 0; i < players.length; i++)
				passing[i] = players[i].pass();
			
			for (int i = 0; i < players.length; i++)
				for (Card aCard: passing[i])
					players[(i + direc) % 4].addCard(aCard);
		}
	}
	
	public void leadCard() {
		centerCards[leadRef] = players[leadRef].leadCard(brokeHearts);
		winRef = leadRef;
		
		if (centerCards[leadRef].getSuit().equals("Hearts")) {
			brokeHearts = true;
			trickPoint++;
		}
		else if (centerCards[leadRef].getSuit().equals("Spades") && centerCards[leadRef].getVal().equals("Q")) {
			trickPoint += 13;
		}
		
		for (int i = 1; i < players.length; i++) {
			((ComputerPlayer) players[i]).addCardPlayed(centerCards[leadRef]);
		}
	}
	
	public void playCard(int ref) {
		centerCards[ref] = players[ref].playCard(centerCards, centerCards[leadRef].getSuit());
		
		if (centerCards[ref].getSuit().equals(centerCards[leadRef].getSuit()) &&
				centerCards[ref].compareTo(centerCards[winRef]) == 1)
			winRef = ref;
		
		if (centerCards[ref].getSuit().equals("Hearts")) {
			brokeHearts = true;
			trickPoint++;
		}
		else if (centerCards[ref].getSuit().equals("Spades") && centerCards[ref].getVal().equals("Q")) {
			trickPoint += 13;
		}
		
		for (int i = 1; i < players.length; i++) {
			((ComputerPlayer) players[i]).addCardPlayed(centerCards[ref]);
			
			if (!centerCards[ref].getSuit().equals(centerCards[leadRef].getSuit()))
				((ComputerPlayer) players[i]).personDiscarded(centerCards[leadRef].getSuit());
		}
	}
	
	public void winTrick() {
		leadRef = winRef;
		roundPoints[winRef] += trickPoint;
		trickPoint = 0;
		centerCards = new Card[4];
	}
	
	public void addPoints() {
		int ref = shootMoon();
		if (ref != -1) {
			for (int i = 0; i < players.length; i++)
				if (i != ref)
					players[i].addPoints(26);
		}
		else {
			for (int i = 0; i < players.length; i++)
				players[i].addPoints(roundPoints[i]);
		}
		
		for (int i = 1; i < players.length; i++) {
			((ComputerPlayer)players[i]).clearPlayed();
		}
	}
	
	public int getScore(int ref) {
		return players[ref].getPoints();
	}
	
	public int getRoundPoints(int ref) {
		return roundPoints[ref];
	}
	
	public boolean isWinner() {
		for (Player play: players)
			if (play.getPoints() >= 100)
				return true;
		
		return false;
	}
	
	public String winnerName() {
		int ref = 0;
		String win = players[0].getName();
		
		for (int i = 1; i < players.length; i++) {
			if (players[i].getPoints() < players[ref].getPoints()) {
				win = players[i].getName();
				ref = i;
			}
			else if (players[i].getPoints() == players[ref].getPoints()) {
				win += " & " + players[i].getName();
			}
		}
		
		return win;
	}
	
	private int shootMoon() {
		for (int i = 0; i < roundPoints.length; i++)
			if (roundPoints[i] == 26)
				return i;
		
		return -1;
	}
}