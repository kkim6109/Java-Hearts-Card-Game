import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hand extends JPanel {

	MainGUI main;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	public boolean waitToClick;
	public JLabel lblPlayer;
	public JLabel currentScore;
	public JLabel totalScore;

	public Hand(MainGUI man) {
		main = man;
		waitToClick = false;
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 206, 209));
		add(panel_1);
		
		currentScore = new JLabel("000");
		currentScore.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_1.add(currentScore);
		lblPlayer = new JLabel("Player1");
		lblPlayer.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBounds(648, 558, 574, 45);
		add(lblPlayer);
		setBackground(new Color(0, 128, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 112, 147));
		add(panel);
		
		totalScore = new JLabel("000");
		totalScore.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel.add(totalScore);
	}

	public void setUp(Player player) {
		JButton btnTest = new JButton();;
		setBackground(new Color(0, 128, 0));
		setLayout(null);

		for (int i = 0; i < player.handSize(); i++) {
			if (player.getClass() == HumanPlayer.class) {
				String suit = player.seeCard(i).getSuit() + "";
				String val = player.seeCard(i).getVal();
				btnTest = new JButton(convSuit(suit) + " " + val);
				btnTest.setBounds(i * 98, 70, 96, 131);
				if (suit.startsWith("H") || suit.startsWith("D")) {
					btnTest.setForeground(Color.red);
				}
				btnTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (MainGUI.logic.getLead() == 0) {
							for (int j = 0; j < player.handSize(); j++) {
								if (player.seeCard(j).equals(new Card("2", "Clubs", 0))) {
									HumanPlayer play = (HumanPlayer) player;
									play.setRef(j);
									MainGUI.logic.leadCard();
									remove(buttons.get(j));
									buttons.remove(j);
									repaint();
								}
								else if (player.seeCard(j).getSuit().equals(suit) && player.seeCard(j).getVal().equals(val)) {
									HumanPlayer play = (HumanPlayer) player;
									play.setRef(j);
									MainGUI.logic.leadCard();
									remove(buttons.get(j));
									buttons.remove(j);
									repaint();
								}
							}
						}
						else {
							for (int j = 0; j < player.handSize(); j++) {
								if (player.seeCard(j).getSuit().equals(suit) && player.seeCard(j).getVal().equals(val)) {
									HumanPlayer play = (HumanPlayer) player;
									play.setRef(j);
									MainGUI.logic.playCard(0);
									remove(buttons.get(j));
									buttons.remove(j);
									repaint();
								}
							}
						}
						MainGUI.canPress = false;
						MainGUI.center.updateCenter();
						MainGUI.trick2();
					}
				});
			}
			else {
				btnTest.setBounds(i * 98, 70, 96, 131);
				btnTest.setIcon(new ImageIcon(CenterTable.class.getResource("/images/cardBack.png")));
			}
			buttons.add(btnTest);
		}

		for (JButton button : buttons) {
			add(button);
		}
	}

	private void updateHand() {
		//clear hand
		//for (JButton button : buttons)
		//add(button);
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
