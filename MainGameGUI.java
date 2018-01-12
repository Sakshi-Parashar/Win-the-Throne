import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class MainGameGUI extends JFrame {

	JLabel[][] board = new JLabel[10][10];
	JLabel[] players = new JLabel[4];
	Icon[] bpic = new ImageIcon[100];
	Icon[] plpic = new ImageIcon[4];
	JPanel dicePanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JPanel playerPanel = new JPanel();
	JTextField playerText[] = new JTextField[4];
	Icon dice;
	JLabel dicel;
	JButton dbutton; 

	static int gotNo[] = new int[4];
	static int num;
	String []s = new String[4];

	Color c1 = new Color(1,1,1);
	Color c2 = new Color(6,39,161);
	Color c3 = new Color(135,135,135);
	Font f1 = new Font("Old English Text MT",Font.BOLD,40);
	Font f2 = new Font("Old English Text MT",Font.PLAIN,30);

	AudioPlay ap;
	long clipTime;

	public MainGameGUI(int num, String s[], int prev[]) {
		this.num = num;		
		
		for ( int i = 0 ; i < num ; i++ ) {
			gotNo[i] = prev[i];
			this.s[i] = s[i];
		}

		ap = new AudioPlay("Audio\\MainGame.wav");

		Icon bg = new ImageIcon("New Folder\\Fire4.jpg");
		JLabel background = new JLabel(bg);
		

		setPlayers();
		setDice();
		setBoard();

		add(background);


		setSize(1500,800);
		setVisible(true);
		setLocation(0,0);
		setLayout(null);
		setTitle("Win the Throne");	
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void setBoard() {
		int k=0;
		for(int i=9;i>=0;i--){
			if(i%2 != 0){
				for(int j=0;j<10;j++){
				bpic[k] = new ImageIcon("Board\\"+(k+1)+".jpeg");
				board[i][j] = new JLabel(bpic[k]);
				board[i][j].setBounds((j*70),((i*70)),70,70);
				k++; 
				}
			}
			else {
				for(int j=9;j>=0;j--){
				bpic[k] = new ImageIcon("Board\\"+(k+1)+".jpeg");
				board[i][j] = new JLabel(bpic[k]);
				board[i][j].setBounds((j*70),((i*70)),70,70);
				k++; 	
				}
			}
		}
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				boardPanel.add(board[i][j]);
				add(board[i][j]);
			}
		}
		boardPanel.setBounds(0,0,1000,800);
	}

	public void setPlayers() {
		for ( int i = 0 ; i< num ; i++ ) {
			plpic[i] = new ImageIcon("Icons\\GOT"+(gotNo[i]+1)+".jpg");
			players[i] = new JLabel(plpic[i]);
			players[i].setBounds(900,(360 + (75*i)),80,80);
			playerPanel.add(players[i]);
			add(players[i]);			
		}

		for ( int i = 0 ; i < num ; i++ ) {
			playerText[i] = new JTextField(s[i]);
			playerText[i].setBounds(1000,400 + (75*i),200,35);
			playerPanel.add(playerText[i]);
			add(playerText[i]);
			playerText[i].setFont(f2);
			playerText[i].setForeground(c3);
			playerText[i].setOpaque(false);
			playerText[i].setHorizontalAlignment(JTextField.CENTER);
		}
		playerPanel.setBounds(800,325,700,500);
	}

	public void setDice() {
		
		dice = new ImageIcon("Dice\\1.jpg");
		dicel = new JLabel(dice);
		dbutton = new JButton(" ROLL ");
		
		dicel.setBounds(1000,200,116,117);				

		dbutton.setBounds(958,90,200,60);
		dbutton.setFont(f1);
		dbutton.setForeground(Color.RED);
		dbutton.setBackground(Color.BLUE);

		dicePanel.setBounds(800,0,700,325);		

		dicePanel.add(dicel);
		dicePanel.add(dbutton);
		add(dbutton);
		add(dicel);
	}
	public static void main(String []args) {
		String []s = {"A","B"};
		int []no = {3,4};
		new MainGameGUI(2,s,no);
	}
	
}