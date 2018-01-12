
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class MainGame extends JFrame implements ActionListener {
	private int dragonHead[] = {32,62,83,96};
	private int dragonTail[] = {8,43,4,54};
	private	int swordHand[] = {10,16,26,45,66};
	private	int swordTip[] = {49,38,70,81,90};
	private	static int dic;
	private	static int cnt = 0;
	private	static int chance = 0;
	private	int pos[] = {0,0,0,0};
	private	int oldpos[] = {0,0,0,0};

	private	JLabel[][] board = new JLabel[10][10];
	private	JLabel[] players = new JLabel[4];
	private	Icon[] bpic = new ImageIcon[100];
	private	Icon[] plpic = new ImageIcon[4];
	private	JPanel dicePanel = new JPanel();
	private	JPanel boardPanel = new JPanel();
	private	JPanel playerPanel = new JPanel();
	private	JTextField playerText[] = new JTextField[4];
	private	Icon dice;
	private	JLabel dicel;
	private	JButton dbutton; 

	private	static int gotNo[] = new int[4];
	private	static int num;
	private	static int ply = 0;
	private	static int oldply = 0;
	private	static int prevpos;
	private	String s[] = new String[4];

	private	Color c1 = new Color(1,1,1);
	private	Color c2 = new Color(6,39,161);
	private	Color c3 = new Color(135,135,135);
	private	Font f1 = new Font("Old English Text MT",Font.BOLD,40);
	private	Font f2 = new Font("Old English Text MT",Font.PLAIN,30);

	private	AudioPlay ap;
	private	long clipTime;

	MainGame(int num, String s[], int prev[]) {
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
		dbutton.addActionListener(this);

		dicePanel.setBounds(800,0,700,325);		

		dicePanel.add(dicel);
		dicePanel.add(dbutton);
		add(dbutton);
		add(dicel);
	}
	public void findingChar(int oldpos , int pos) {

		if (oldpos!=0) {
			for ( int i = 0 ; i < 10 ; i++ ) {
				for ( int j = 0 ; j < 10 ; j++ ) {

					String oldBoardIcons = "Board\\" + (oldpos) + ".jpeg";
					String chkBoard = board[i][j].getIcon().toString(); 
					String gotCharacter = "Icons\\GOT"+ (gotNo[ply]+1) + ".jpg";

					if ( chkBoard.equals(gotCharacter) ) {
						board[i][j].setIcon( new ImageIcon(oldBoardIcons));		
					}
				}
			}
		}

		if (pos!=0) {
			for ( int i = 0 ; i < 10 ; i++ ) {
				for ( int j = 0 ; j < 10 ; j++ ) {
				
					String boardIcons = "Board\\" + (pos) + ".jpeg";
					String chkBoard = board[i][j].getIcon().toString();
					String gotCharacter = "Icons\\GOT" + (gotNo[ply]+1) + ".jpg";
				
					if ( boardIcons.equals(chkBoard) ) {							
						board[i][j].setIcon( new ImageIcon(gotCharacter));			 
					}												 
				}
			}
		}

		if ( pos == 100 ) {
			clipTime = ap.pause();
			new Winner(s[ply],ap,clipTime);
			dbutton.setEnabled(false);
			playerText[ply].setText("Throne King");
		}
	}

	public void replacingChar ( int k ) {

		for ( int i = 0 ; i < 10 ; i++ ) {
				for ( int j = 0 ; j < 10 ; j++ ) {

					String chkBoard = board[i][j].getIcon().toString(); 
					String prevgotCharacter = "Icons\\GOT"+ (gotNo[k]+1) + ".jpg";
					String thisgotCharacter = "Icons\\GOT"+ (gotNo[ply]+1) + ".jpg";
					

					if ( chkBoard.equals(prevgotCharacter) ) {
						board[i][j].setIcon( new ImageIcon(thisgotCharacter));		
					}
				}
		}
	}


	public void Position(int ply, int dic) {
		oldpos[ply] = pos[ply];

		if ( ( pos[ply] + dic ) <= 100 ) {
			pos[ply] = pos[ply] + dic;
			findingChar(oldpos[ply], pos[ply]);
		}
		else
			return;  
		for ( int i = 0 ; i < dragonHead.length ; i++ ) {
			if ( pos[ply] == dragonHead[i] ){
				oldpos[ply] = pos[ply];
				clipTime = ap.pause();
				new Dragon(s[ply],ap,clipTime);
				pos[ply]=dragonTail[i];
				findingChar(oldpos[ply], pos[ply]);
			}
		}

		for ( int i = 0 ; i < swordHand.length ; i++ ) {
			if ( pos[ply] == swordHand[i] ) {
				oldpos[ply] = pos[ply];
				clipTime = ap.pause();
				new Sword(s[ply],ap,clipTime);
				pos[ply]=swordTip[i];
				findingChar(oldpos[ply], pos[ply]);
			}
		}

		
		for ( int k = 0 ; k < num ; k++ ) {
			if ( k != ply ) {
				if ( pos[ply] == pos[k] ) {
					pos[k] = 0;
					replacingChar(k);
					clipTime = ap.pause();
					new ValarMorghulis(s[k],ap,clipTime);			
				}
			}
		}
		
		
	}


	public void actionPerformed(ActionEvent e) {
		
		Random rn = new Random();
        	int dic = 1 + rn.nextInt(6);
        	Icon icon = new ImageIcon("Dice\\"+dic+".jpg");
 	        dicel.setIcon(icon);
        	dicel.setBounds(1000,200,116,117);		
		int tempply = ply;
		playerText[oldply].setText(s[oldply]);
		playerText[ply].setText("Your Chance");
		if (chance == 1 ) {
			Position(ply, dic);
			if ( dic == 6 ) {		
				cnt++;
				if (cnt == 3) {
					Position(ply, prevpos - pos[ply]);
					if (pos[ply] == 0 ) {
						chance = 0;
					}
					cnt = 0;
				}
				
			}
			else {			
				chance = 0;
				oldply = ply;
				ply = (ply + 1) % num;
				cnt = 0;
			}			
		}		
		
		else if (pos[ply] == 0){ 
			if ( dic != 1 && dic!=6 ) {
				oldply = ply;
				ply = (ply + 1 ) % num;						
			}
			else {
				if (dic == 6 ) {
					cnt++;
					prevpos = 0;
				}
				Position(ply, dic);
				chance = 1;
			}		
		}

		else if ( dic == 6 ) {
			prevpos = pos[ply];
			Position(ply, dic);
			if ( prevpos == pos[ply] ) {
				oldply = ply;
				ply = ( ply + 1 ) % num;
			}
			else {
				cnt++;
				chance = 1;
			}
		}

		else {
			
			Position(ply, dic);
			oldply = ply;
			ply = ( ply + 1 ) % num;
		}
		
		
		for ( int i = 0 ; i < swordHand.length ; i++ ) { 
			if ( oldpos[tempply] == swordHand[i] && oldpos[tempply] != 0) { 
				chance = 1;
				ply = tempply;
			}
		}
	}
}