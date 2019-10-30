import java.util.ArrayList;

public class ComputerPlayer extends Player {

	private ArrayList<Card> played;
	private ArrayList<String> discarded;

	public ComputerPlayer() {
		super.setName("Computer");
		clearPlayed();
	}

	@Override
	public Card leadCard(boolean brokeHearts) {
		// TODO Auto-generated method stub
		if(super.has2Clubs())
			return super.removeCard(0);	
		else if (!queenPlayed() && super.contains(new Card("Q", "Spades", 13)) == -1 && super.contains(new Card("A", "Spades", 0)) == -1 
				&& super.contains(new Card("K", "Spades", 0)) == -1 && !outOfSuit("Spades")) {
			return super.removeCard(playHigh1("Spades"));
		}
		else if ((brokeHearts || (outOfSuit("Diamonds") && outOfSuit("Clubs") && outOfSuit("Spades"))) && haveLow("Hearts") != -1) {
			return super.removeCard(haveLow("Hearts"));
		}
		else if ((!sparse("Diamonds") && !outOfSuit("Diamonds")) || (!sparse("Clubs") && !outOfSuit("Clubs"))) {
			int rand;
			while (true) {
				rand = (int)(2*Math.random());
				if (rand == 0 && !sparse("Diamonds") && !outOfSuit("Diamonds")) {
					return super.removeCard(playHigh1("Diamonds"));
				}
				else if (!sparse("Clubs") && !outOfSuit("Clubs")) {
					return super.removeCard(playHigh1("Clubs"));
				}
			}
		}
		else if ((!outOfSuit("Diamonds") && haveLow("Diamonds") != -1) || (!outOfSuit("Clubs") && haveLow("Clubs") != -1)) {
			int rand;
			while (true) {
				rand = (int)(2*Math.random());
				if (rand == 0 && (!outOfSuit("Diamonds") && haveLow("Diamonds") != -1)) {
					return super.removeCard(haveLow("Diamonds"));
				}
				else if ((!outOfSuit("Clubs") && haveLow("Clubs") != -1)) {
					return super.removeCard(haveLow("Clubs"));
				}
			}
		}

		else while (true) {
			int ref = (int)(super.handSize()*Math.random());
			Card card = super.seeCard(ref);
			if (canLead(brokeHearts, ref) && safeLead(card, brokeHearts)) {
				return super.removeCard(ref);
			}
		}
	}

	private boolean safeLead(Card card, boolean brokeHearts) {
		// TODO Auto-generated method stub
		if (!queenPlayed()) {
			return (!card.equals(new Card("A", "Spades", 0)) && !card.equals(new Card("K", "Spades", 0)) && !card.equals(new Card("Q", "Spades", 13)))
					|| (numSuit("Clubs") == 0 && numSuit("Diamonds") == 0 && (numSuit("Hearts") == 0 || !brokeHearts)
					&& playBelow(new Card("Q", "Spades", 13)) == -1);
		}
		else return true;
	}

	private boolean canLead(boolean brokeHearts, int ref) {
		// TODO Auto-generated method stub
		if(super.has2Clubs())
			return seeCard(ref).equals(new Card("2", "Clubs", 0));

		if (brokeHearts)
			return true;
		else return (!seeCard(ref).getSuit().equals("Hearts")) || (outOfSuit("Diamonds") && outOfSuit("Clubs") && outOfSuit("Spades"));
	}

	@Override
	public Card playCard(Card[] playedCards, String lead) {
		// TODO Auto-generated method stub
		if (outOfSuit(lead)) { 
			if (!firstTrick(playedCards) && super.contains(new Card("Q", "Spades", 13)) != -1)
				return super.removeCard(super.contains(new Card("Q", "Spades", 13)));
			else if (super.contains(new Card("A", "Spades", 0)) != -1)
				return super.removeCard(super.contains(new Card("A", "Spades", 0)));
			else if (super.contains(new Card("K", "Spades", 0)) != -1)
				return super.removeCard(super.contains(new Card("K", "Spades", 0)));
			else if (!firstTrick(playedCards) && hasHighHearts() != -1)
				return super.removeCard(hasHighHearts());
			else return super.removeCard(highCard());
		}
		else {
			//play in suit
			if (firstTrick(playedCards)) {
				return super.removeCard(playHigh1(lead));
			}

			if (super.contains(new Card("Q", "Spades", 13)) != -1 && 
					(getHigh(playedCards, lead).equals(new Card("A", "Spades", 0)) || getHigh(playedCards, lead).equals(new Card("K", "Spades", 0))))
				return super.removeCard(super.contains(new Card("Q", "Spades", 13)));

			if (numNotPlayed(playedCards) == 1) {
				if (points(playedCards) == 0)
					return super.removeCard(playHigh1(lead));
				else if (playBelow(getHigh(playedCards, lead)) != -1)
					return super.removeCard(playBelow(getHigh(playedCards, lead)));
				else return super.removeCard(playHigh1(lead));
			}
			else if (queenPlayed()) {
				if (points(playedCards) == 0 && !sparse(lead))
					return super.removeCard(playHigh1(lead));
				else if (playBelow(getHigh(playedCards, lead)) != -1)
					return super.removeCard(playBelow(getHigh(playedCards, lead)));
				else return super.removeCard(playHigh1(lead));
			}
			else {
				if (points(playedCards) == 0 && !sparse(lead))
					return super.removeCard(playHigh2(lead));
				else if (playBelow(getHigh(playedCards, lead)) != -1)
					return super.removeCard(playBelow(getHigh(playedCards, lead)));
				else if (haveLow(lead) != -1)
					return super.removeCard(haveLow(lead));
				else return super.removeCard(playHigh2(lead));
			}
		}
	}

	private int highCard() {
		// TODO Auto-generated method stub
		int ref = 0;

		for (int i = 0; i < super.handSize(); i++) {
			if (super.seeCard(i).getPoint() == 0 && super.seeCard(i).compareTo(super.seeCard(ref)) == 1)
				ref = i;
		}

		return ref;
	}

	private int hasHighHearts() {
		// TODO Auto-generated method stub
		int ref = -1;

		for (int i = 0; i < super.handSize(); i++) {
			if (super.seeCard(i).getSuit().equals("Hearts") && (ref == -1 || super.seeCard(i).compareTo(super.seeCard(ref)) == 1))
				ref = i;
		}

		return ref;
	}

	private int numNotPlayed(Card[] playedCards) {
		int count = 0;	
		for(Card play: playedCards)
			if (play == null)
				count++;	
		return count;
	}

	private Card getHigh(Card[] playedCards, String lead) {
		Card highCard = null;
		for (int i = 0; i < playedCards.length; i++)
			if (playedCards[i] != null && playedCards[i].getSuit().equals(lead) && (highCard == null || playedCards[i].compareTo(highCard) == 1)) {
				highCard = playedCards[i];
			}	
		return highCard;
	}

	private int points(Card[] playedCards) {
		int count = 0;
		for (int i = 0; i < playedCards.length; i++)
			if (playedCards[i] != null)
				count += playedCards[i].getPoint();
		return count;
	}

	private boolean queenPlayed() {
		for (Card aCard: played) {
			if (aCard.equals(new Card("Q", "Spades", 13)))
				return true;
		}
		return false;
	}

	private boolean sparse(String lead) {
		for (String discard: discarded)
			if (discard.equals(lead))
				return true;

		int count = 0;

		for (Card aCard: played)
			if (aCard.getSuit().equals(lead))
				count++;

		for (int i = 0; i < super.handSize(); i++)
			if (seeCard(i).getSuit().equals(lead))
				count++;

		return count > 8;
	}

	private int playBelow(Card highCard) {
		int ref = -1;
		for (int i = 0; i < super.handSize(); i++)
			if (seeCard(i).getSuit().equals(highCard.getSuit()) && seeCard(i).compareTo(highCard) == -1 && (ref == -1 || seeCard(i).compareTo(seeCard(ref)) == 1))
				ref = i;

		return ref;
	}

	private int haveLow(String lead) {
		int ref = -1, countL = 0, countH = 0;
		for (int i = 0; i < super.handSize(); i++) {
			if (super.seeCard(i).getSuit().equals(lead) && (ref == -1 || super.seeCard(ref).compareTo(super.seeCard(ref)) == -1))
				ref = i;
		}

		if (ref == -1)
			return -1;

		String val = super.seeCard(ref).getVal();

		for (Card aCard: played) {
			if (aCard.getSuit().equals(lead) && aCard.compareTo(super.seeCard(ref)) == -1)
				countL++;
			else if (aCard.getSuit().equals(lead) && aCard.compareTo(super.seeCard(ref)) == 1)
				countH++;
		}

		if (numBelow(val) - countL < 3 && numAbove(val) - countH >= numBelow(val) - countL)
			return ref;
		else return -1;
	}

	private int numAbove(String val) {
		// TODO Auto-generated method stub
		if (val.equals("A"))
			return 0;
		else if (val.equals("K"))
			return 1;
		else if (val.equals("Q"))
			return 2;
		else if (val.equals("J"))
			return 3;
		else return 14 - Integer.parseInt(val);
	}

	private int numBelow(String val) {
		// TODO Auto-generated method stub
		if (val.equals("A"))
			return 12;
		else if (val.equals("K"))
			return 11;
		else if (val.equals("Q"))
			return 10;
		else if (val.equals("J"))
			return 9;
		else return -2 + Integer.parseInt(val);
	}

	private int playHigh1(String lead) {
		int ref = -1, refQ = -1;

		for (int i = 0; i < super.handSize(); i++) {
			if (lead.equals("Spades") && 
					super.seeCard(i).equals(new Card("Q", "Spades", 13)))
				refQ = i;
			else if (super.seeCard(i).getSuit().equals(lead) && (ref == -1 || super.seeCard(i).compareTo(super.seeCard(ref)) == 1)) {
				ref = i;
				//System.out.println(i);
			}
			/*else {
				/*System.out.println(super.seeCard(i).getSuit() + " " + super.seeCard(i).getVal());
				if (ref != -1)
					System.out.println(super.seeCard(ref).compareTo(super.seeCard(ref)));*/
			//}
		}

		if (ref != -1)
			return ref;
		else return refQ;
	}

	private boolean firstTrick(Card[] cards) {
		for (Card aCard: cards) {
			if (aCard != null && aCard.equals(new Card("2", "Clubs", 0)))
				return true;
		}
		return false;
	}

	// if not safe for king/ace of spades
	private int playHigh2(String lead) {
		int ref = -1, refKA = -1, refQ = -1;

		for (int i = 0; i < super.handSize(); i++) {
			if (lead.equals("Spades") && 
					(super.seeCard(i).equals(new Card("A", "Spades", 0)) || super.seeCard(i).equals(new Card("K", "Spades", 0))))
				refKA = i;
			else if (lead.equals("Spades") && 
					super.seeCard(i).equals(new Card("Q", "Spades", 13)))
				refQ = i;
			else if (super.seeCard(i).getSuit().equals(lead) && (ref == -1 || super.seeCard(i).compareTo(super.seeCard(ref)) == 1))
				ref = i;
		}

		if (ref != -1)
			return ref;
		else if (refKA != -1)
			return refKA;
		else return refQ;
	}

	private boolean outOfSuit(String lead) {
		// TODO Auto-generated method stub
		for (int i = 0; i < super.handSize(); i++)
			if (lead.equals(seeCard(i).getSuit()))
				return false;
		return true;
	}

	@Override
	public Card[] pass() {
		// TODO Auto-generated method stub
		Card[] passing = new Card[3];

		int count = 0;
		if (numSuit("Clubs") < 3) {
			for (int i = super.handSize() - 1; i >= 0; i--)
				if (super.seeCard(i).getSuit().equals("Clubs")) {
					passing[count] = super.removeCard(i);
					count++;
				}
		}

		if (numSuit("Diamonds") < 3) {
			for (int i = super.handSize() - 1; i >= 0; i--)
				if (super.seeCard(i).getSuit().equals("Diamonds") && count < 3) {
					passing[count] = super.removeCard(i);
					count++;
				}
		}	

		if (numSuit("Hearts") < 3) {
			for (int i = super.handSize() - 1; i >= 0; i--)
				if (super.seeCard(i).getSuit().equals("Hearts") && count < 3) {
					passing[count] = super.removeCard(i);
					count++;
				}
		}

		int rand;
		while (count < 3) {
			if (numSuit("Spades") < 4 && super.contains(new Card("Q", "Spades", 13)) != -1) {
				passing[count] = super.removeCard(super.contains(new Card("Q", "Spades", 13)));
				count++;
			}
			else if (numSuit("Spades") < 4 && super.contains(new Card("A", "Spades", 0)) != -1) {
				passing[count] = super.removeCard(super.contains(new Card("A", "Spades", 0)));
				count++;
			}
			else if (numSuit("Spades") < 4 && super.contains(new Card("K", "Spades", 0)) != -1) {
				passing[count] = super.removeCard(super.contains(new Card("K", "Spades", 0)));
				count++;
			}
			else {
				rand = (int)(2*Math.random());
				if (rand == 0 && !outOfSuit("Diamonds")) {
					passing[count] = super.removeCard(playHigh1("Diamonds"));
					count++;
				}
				else if (!outOfSuit("Clubs")) {
					passing[count] = super.removeCard(playHigh1("Clubs"));
					count++;
				}
			}
		}

		return passing;
	}

	private int numSuit(String string) {
		// TODO Auto-generated method stub
		int count = 0;

		for (int i = 0; i < super.handSize(); i++)
			if (super.seeCard(i).getSuit().equals(string)) {
				count++;
			}

		return count;
	}

	public void addCardPlayed(Card aCard) {
		played.add(aCard);
	}

	public void personDiscarded(String suit) {
		discarded.add(suit);
	}

	public void clearPlayed() {
		played = new ArrayList<Card>();
		discarded = new ArrayList<String>();
	}
}