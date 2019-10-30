import java.util.ArrayList;

public abstract class Player {

	private String name;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int score = 0;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addCard(Card newCard) {
		hand.add(newCard);
		organize();
	}

	private void organize() {
		// TODO Auto-generated method stub
		int minIndex;
		for (int i=0; i < hand.size()-1; i++)
		    {
			//find smallest element in list starting at location i
			minIndex = i;
			for (int j = i+1; j < hand.size(); j++)
			    if (hand.get(j).compare2(hand.get(minIndex)) == -1)
				    minIndex = j;

			//swap list[i] with smallest element
			Card temp = hand.get(i);
			hand.set(i, hand.get(minIndex));
			hand.set(minIndex, temp);
		    }
	}

	public void addPoints(int points) {
		score += points;
	}

	public int getPoints() {
		return score;
	}

	public boolean has2Clubs() {
		for (Card aCard: hand)
			if (aCard.equals(new Card("2", "Clubs", 0)))
				return true;

		return false;
	}

	public int handSize() {
		return hand.size();
	}

	protected Card seeCard(int ref) {
		if (ref < hand.size())
			return hand.get(ref);
		else return null;
	}

	protected Card removeCard(int ref)  {
		return hand.remove(ref);
	}

	protected int contains(Card aCard) {
		for (int i = 0; i < hand.size(); i++)
			if (hand.get(i).equals(aCard))
				return i;

		return -1;
	}

	public abstract Card leadCard(boolean brokeHearts);
	public abstract Card playCard(Card[] playedCards, String lead);
	public abstract Card[] pass();
}
