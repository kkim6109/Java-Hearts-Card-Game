
public class HumanPlayer extends Player {

	private int ref;
	private int[] passing;
	
	public HumanPlayer(String name) {
		super.setName(name);
	}
	
	@Override
	public Card leadCard(boolean brokeHearts) {
		// TODO Auto-generated method stub
		if (canLead(brokeHearts)) {
			Card play = seeCard(ref);
			super.removeCard(ref);
			return play;
		}
		else return null;
	}

	private boolean canLead(boolean brokeHearts) {
		// TODO Auto-generated method stub
		if(super.has2Clubs())
			return seeCard(ref).equals(new Card("2", "Clubs", 0));
		
		if (brokeHearts)
			return true;
		else return !seeCard(ref).getSuit().equals("Hearts") || (outOfSuit("Diamonds") && outOfSuit("Clubs") && outOfSuit("Spades"));
	}

	@Override
	public Card playCard(Card[] playedCards, String lead) {
		// TODO Auto-generated method stub
		if (canPlay(lead)) {
			Card play = seeCard(ref);
			super.removeCard(ref);
			return play;
		}
		else return null;
	}

	private boolean canPlay(String lead) {
		// TODO Auto-generated method stub
		if (outOfSuit(lead))
			return true;
		else return lead.equals(seeCard(ref).getSuit());
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
		Card[] pass = new Card[passing.length];
		
		for (int i = 0; i < passing.length; i++) {
			pass[i] = seeCard(passing[i]);
		}
		
		for (int i = 0; i < passing.length; i++) {
			super.removeCard(passing[i]);
		}
		
		return pass;
	}
	
	public void setPassRefs(int[] refs) {
		passing = refs;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}
	
	public Card seeCard(int ref) {
		return super.seeCard(ref);
	}
}