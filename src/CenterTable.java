import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CenterTable extends JPanel {

	public JButton cpu1;
	public JButton cpu2;
	public JButton cpu3;
	public JButton playerCard;
	public JLabel label;
	public JButton btnDeal;
	public JButton btnNext;
	
	/**
	 * Create the panel.
	 */
	public CenterTable(MainGUI main) {
		setBackground(new Color(0, 128, 0));
		setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(CenterTable.class.getResource("/images/cardBack.png")));
		label.setBounds(151, 86, 96, 131);
		add(label);
		
		cpu2 = new JButton("");
		cpu2.setBackground(Color.GRAY);
		cpu2.setBounds(151, 11, 96, 131);
		cpu2.setVisible(false);
		
		btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setVisible(false);
				btnDeal.setVisible(false);
				playerCard.setVisible(true);
				cpu1.setVisible(true);
				cpu2.setVisible(true);
				cpu3.setVisible(true);
				main.hand1.setVisible(true);
				main.hand2.setVisible(true);
				main.hand3.setVisible(true);
				main.logic.deal();
				main.playerHand.setUp(main.logic.getPlayer(0));
				main.playerHand.repaint();
				MainGUI.trick1();
			}
		});
		btnDeal.setBounds(151, 220, 96, 23);
		add(btnDeal);
		add(cpu2);
		
		cpu1 = new JButton("");
		cpu1.setBackground(Color.GRAY);
		cpu1.setBounds(45, 86, 96, 131);
		cpu1.setVisible(false);
		add(cpu1);
		
		playerCard = new JButton("");
		playerCard.setBackground(Color.GRAY);
		playerCard.setBounds(151, 153, 96, 131);
		playerCard.setVisible(false);
		add(playerCard);
		
		cpu3 = new JButton("");
		cpu3.setBackground(Color.GRAY);
		cpu3.setBounds(257, 86, 96, 131);
		cpu3.setVisible(false);
		add(cpu3);
		
		btnNext = new JButton("Next");
		btnNext.setVisible(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNext.setVisible(false);
				if (MainGUI.logic.getPlayer(0).handSize() > 0) {
					MainGUI.trick1();
				}
				else {
					btnNext.setText("Next");
					MainGUI.logic.addPoints();
					MainGUI.playerHand.totalScore.setText(MainGUI.scorify(MainGUI.logic.getScore(0) + ""));
					MainGUI.playerHand.currentScore.setText("000");
					for (int i = 0; i < 3; i++) {
						MainGUI.frame.totalScores[i].setText(MainGUI.scorify(MainGUI.logic.getScore(i + 1) + ""));
						MainGUI.frame.currentScores[i].setText("000");
						MainGUI.frame.repaint();
					}
					MainGUI.logic.deal();
					main.hand1.setVisible(true);
					main.hand2.setVisible(true);
					main.hand3.setVisible(true);
					MainGUI.playerHand.setUp(main.logic.getPlayer(0));
					main.playerHand.repaint();
					MainGUI.trick1();
				}
				MainGUI.center.updateCenter();
			}
		});
		btnNext.setBounds(257, 37, 96, 23);
		add(btnNext);

	}
	
	public void updateCenter() {
		for (int i = 0; i < MainGUI.logic.centerCards().length; i++) {
			Card card = MainGUI.logic.centerCards()[i];
			if (card != null) {
				if (i == 0) {
					playerCard.setBackground(Color.white);
					String suit = card.getSuit() + "";
					String val = card.getVal();
					playerCard.setText(convSuit(suit) + " " + val);
					if (suit.startsWith("H") || suit.startsWith("D")) {
						playerCard.setForeground(Color.red);
					}
					else {
						playerCard.setForeground(Color.black);
					}
				}
				else if (i == 1) {
					cpu1.setBackground(Color.white);
					String suit = card.getSuit() + "";
					String val = card.getVal();
					cpu1.setText(convSuit(suit) + " " + val);
					if (suit.startsWith("H") || suit.startsWith("D")) {
						cpu1.setForeground(Color.red);
					}
					else {
						cpu1.setForeground(Color.black);
					}
				}
				else if (i == 2) {
					cpu2.setBackground(Color.white);
					String suit = card.getSuit() + "";
					String val = card.getVal();
					cpu2.setText(convSuit(suit) + " " + val);
					if (suit.startsWith("H") || suit.startsWith("D")) {
						cpu2.setForeground(Color.red);
					}
					else {
						cpu2.setForeground(Color.black);
					}
				}
				else if (i == 3) {
					cpu3.setBackground(Color.white);
					String suit = card.getSuit() + "";
					String val = card.getVal();
					cpu3.setText(convSuit(suit) + " " + val);
					if (suit.startsWith("H") || suit.startsWith("D")) {
						cpu3.setForeground(Color.red);
					}
					else {
						cpu3.setForeground(Color.black);
					}
				}
			}
			else {
				if (i == 0) {
					playerCard.setText("");
					playerCard.setBackground(Color.gray);
				}
				else if (i == 1) {
					cpu1.setText("");
					cpu1.setBackground(Color.GRAY);
				}
				else if (i == 2) {
					cpu2.setText("");
					cpu2.setBackground(Color.GRAY);
				}
				else if (i == 3) {
					cpu3.setText("");
					cpu3.setBackground(Color.GRAY);
				}
			}
		}
		MainGUI.playerHand.currentScore.setText(MainGUI.scorify(MainGUI.logic.getRoundPoints(0) + ""));
		MainGUI.playerHand.repaint();
		for (int i = 0; i < 3; i++) {
			MainGUI.frame.currentScores[i].setText(MainGUI.scorify(MainGUI.logic.getRoundPoints(i + 1) + ""));
			MainGUI.frame.repaint();
		}
		repaint();
	}
	
	private char convSuit(String suit) {
		if (suit.equals("Hearts")) {
			return '♥';
		}
		else if (suit.equals("Spades")) {
			return '♠';
		}
		else if (suit.equals("Clubs")) {
			return '♣';
		}
		else {
			return '♦';
		}
	}
}
