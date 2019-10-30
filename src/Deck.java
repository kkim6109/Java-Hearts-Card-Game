
public class Deck {
	
	Card[] cards;
	int next;
	
	public Deck() {
		cards = new Card[52];
		String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
		String[] vals = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		int points;
		
		for (int i = 0; i < suits.length; i++)
			for (int j = 0; j < vals.length; j++) {
				if (suits[i].equals("Hearts"))
					points = 1;
				else if (suits[i].equals("Spades") && vals[j].equals("Q"))
					points = 13;
				else
					points = 0;
				
				cards[(vals.length * i) + j] = new Card(vals[j], suits[i], points);
			}
		
		shuffle();
	}
	
	public Card deal() {
		Card toDeal = cards[next];
		next++;
		return toDeal;
	}
	
	public void shuffle() {
		next = 0;
		int rand;
		Card temp;
		
		for (int i = 0; i < cards.length; i++) {
			rand = (int)((cards.length - i)*Math.random());
			
			temp = cards[i + rand];
			cards[i + rand] = cards[i];
			cards[i] = temp;
		}
	}
}