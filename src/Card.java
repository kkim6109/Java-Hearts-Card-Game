
public class Card implements Comparable {
	private String suit, val;
	private int points;
	
	public Card(String val, String suit, int point) {
		this.val = val;
		this.suit = suit;
		this.points = point;
	}
	
	public String getVal() {
		return val;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getPoint() {
		return points;
	}
	
	public boolean equals(Card aCard) {
		return suit.equals(aCard.getSuit()) && val.equals(aCard.getVal());
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		String str = ((Card) arg0).getVal();
		
		if (str.equals(val))
			return 0;
		else if (makeInt(getVal()) > makeInt(str))
			return 1;
		else return -1;
	}
	
	private int makeInt(String val) {
		
		if (val.equals("A"))
			return 14;
		else if (val.equals("K"))
			return 13;
		else if (val.equals("Q"))
			return 12;
		else if (val.equals("J"))
			return 11;
		else return Integer.parseInt(val);
	}
	
	public int compare2(Card otherCard) {
		if (getSuit().equals(otherCard.getSuit()))
			return compareTo(otherCard);
		else if (suitInt(getSuit()) > suitInt(otherCard.getSuit()))
			return 1;
		else return -1;
	}
	
	private int suitInt(String suit) {
		if (suit.equals("Clubs"))
			return 0;
		else if (suit.equals("Diamonds"))
			return 1;
		else if (suit.equals("Spades"))
			return 2;
		else return 3;
	}
}
