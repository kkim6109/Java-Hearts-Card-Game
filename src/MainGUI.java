import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MainGUI extends JFrame {

	public static JPanel contentPane;
	private JTextField txtPlayer;
	public static CenterTable center;
	public static Hearts logic;
	public static JPanel cpu1;
	public static JPanel cpu2;
	public static JPanel cpu3;
	public static Hand playerHand;
	public static MainGUI frame = new MainGUI();
	public static boolean canPress;
	public static JLabel hand2;
	public static JLabel hand3;
	public static JLabel hand1;
	private JLabel lblYrex;
	private JLabel lblSayHill;
	public JLabel[] currentScores = new JLabel[3];
	private JLabel label;
	private JPanel panel_1;
	public JLabel[] totalScores = new JLabel[3];
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setResizable(false);
					logic = new Hearts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		canPress = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1412, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		center = new CenterTable(frame);
		center.setBounds(451, 228, 402, 300);
		contentPane.add(center);

		playerHand = new Hand(frame);
		playerHand.currentScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerHand.totalScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerHand.setBounds(0, 558, 1297, 203);
		contentPane.add(playerHand);

		txtPlayer = new JTextField();
		txtPlayer.setText("Player1");
		txtPlayer.setBounds(1307, 693, 89, 23);
		contentPane.add(txtPlayer);
		txtPlayer.setColumns(10);

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logic.getPlayer(0).setName(txtPlayer.getText());
				playerHand.lblPlayer.setText(txtPlayer.getText());
				repaint();
			}
		});
		btnEnter.setBounds(1307, 727, 89, 23);
		contentPane.add(btnEnter);
		
		hand1 = new JLabel("");
		hand1.setIcon(new ImageIcon(MainGUI.class.getResource("/images/fannedCardsL.png")));
		hand1.setBounds(0, 194, 122, 353);
		hand1.setVisible(false);
		contentPane.add(hand1);
		
		hand2 = new JLabel("");
		hand2.setIcon(new ImageIcon(MainGUI.class.getResource("/images/fannedCardsT.png")));
		hand2.setBounds(483, 0, 343, 114);
		hand2.setVisible(false);
		contentPane.add(hand2);
		
		hand3 = new JLabel("");
		hand3.setIcon(new ImageIcon(MainGUI.class.getResource("/images/fannedCardsR.png")));
		hand3.setBounds(1292, 194, 114, 353);
		hand3.setVisible(false);
		contentPane.add(hand3);
		
		JLabel lblNewLabel = new JLabel("Al");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		lblNewLabel.setBounds(132, 361, 122, 29);
		contentPane.add(lblNewLabel);
		
		lblYrex = new JLabel("Avolioliolio");
		lblYrex.setHorizontalAlignment(SwingConstants.CENTER);
		lblYrex.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		lblYrex.setBounds(590, 130, 142, 29);
		contentPane.add(lblYrex);
		
		lblSayHill = new JLabel("Say Hill");
		lblSayHill.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayHill.setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		lblSayHill.setBounds(1160, 361, 122, 29);
		contentPane.add(lblSayHill);
		//219, 112, 147
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(169, 311, 49, 39);
		contentPane.add(panel);
		
		currentScores[0] = new JLabel("000");
		currentScores[0].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel.add(currentScores[0]);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(219, 112, 147));
		panel_1.setBounds(169, 401, 49, 39);
		contentPane.add(panel_1);

		totalScores[0] = new JLabel("000");
		totalScores[0].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_1.add(totalScores[0]);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 206, 209));
		panel_2.setBounds(542, 125, 49, 39);
		contentPane.add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(219, 112, 147));
		panel_3.setBounds(729, 125, 49, 39);
		contentPane.add(panel_3);
		
		currentScores[1] = new JLabel("000");
		currentScores[1].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_2.add(currentScores[1]);
		totalScores[1] = new JLabel("000");
		totalScores[1].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_3.add(totalScores[1]);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 206, 209));
		panel_4.setBounds(1195, 311, 49, 39);
		contentPane.add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(219, 112, 147));
		panel_5.setBounds(1195, 401, 49, 39);
		contentPane.add(panel_5);
		
		currentScores[2] = new JLabel("000");
		currentScores[2].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_4.add(currentScores[2]);
		totalScores[2] = new JLabel("000");
		totalScores[2].setFont(new Font("Gill Sans MT", Font.BOLD, 24));
		panel_5.add(totalScores[2]);
	}

	public static void trick1() {
		int ref = logic.getLead();
		if (ref != 0) {
			logic.leadCard();
			center.updateCenter();

			ref++;
			ref %= 4;
		}

		while (ref != 0) {
			logic.playCard(ref);
			center.updateCenter();

			ref++;
			ref %= 4;
		}

		canPress = true;
		center.updateCenter();
	}
	
	public static void trick2() {
		int count = 1;
		while (logic.centerCards()[count] == null) {
			logic.playCard(count);
			center.updateCenter();
			
			count++;
			count %= 4;
		}
		
		playerHand.waitToClick = true;
		center.btnNext.setVisible(true);
		if (logic.getPlayer(0).handSize() == 0) {
			center.btnNext.setText("Next Round");
			hand1.setVisible(false);
			hand2.setVisible(false);
			hand3.setVisible(false);
		}
		MainGUI.logic.winTrick();
		/*
		if (logic.getPlayer(0).handSize() > 0) {
			trick1();
		}*/
	}
	
	public static String scorify(String score) {
		String result = "";
		for (int i = 0; i < 3 - score.length(); i++) {
			result += "0";
		}
		result += score;
		return result;
	}
}
