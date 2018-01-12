import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Players extends JFrame implements ActionListener, MouseListener {
	
	JLabel lbl1, lbl2;
	JLabel playerLbl[] = new JLabel[4];
	JLabel avatarLbl[] = new JLabel[4];
	JTextField tf[] = new JTextField[4];
	Icon img;
	JButton	b;
	JToggleButton bgot[][] = new JToggleButton[4][8];
	ButtonGroup bg1;
	ButtonGroup bg2;
	ButtonGroup bg3;
	ButtonGroup bg4;
	Icon got[] = new ImageIcon[8];
	static int num;
	AudioPlay ap;
	static int prev[] = {-1,-1,-1,-1};
	static int a = 0;
	String str = "Enter Your Name";
	
	Players(int num, AudioPlay ap) {
	
		this.num = num;
		this.ap = ap;
	
		Color c1 = new Color(6,39,161);
		Color c2 = new Color(135,135,135);
		Font f = new Font("Old English Text MT",Font.PLAIN,50);
		Font f1 = new Font("Old English Text MT",Font.PLAIN,25);

		bg1 = new ButtonGroup();
		bg2 = new ButtonGroup();
		bg3 = new ButtonGroup();
		bg4 = new ButtonGroup();

		img = new ImageIcon("Images\\Page2.jpg");
		a = 0;
		while ( a < num ) {
			for ( int j = 1 ; j < 8 ; j++ ) {
				got[j] = new ImageIcon("Icons\\GOT"+(j+1)+".jpg");
				bgot[a][j] = new JToggleButton(got[j]);
				bgot[a][j].setBounds((490 + (90*j)), (185 + (100*a)), 75, 75);
				if ( a == 0 )		
					bg1.add(bgot[a][j]);
				else if ( a == 1 )		
					bg2.add(bgot[a][j]);
				else if ( a == 2 )		
					bg3.add(bgot[a][j]);
				else if ( a == 3 )		
					bg4.add(bgot[a][j]);
				add(bgot[a][j]);
				bgot[a][j].addActionListener(this);
			}			
			a++;
		}
		
		lbl1 = new JLabel("<html> <u> Please Enter Your Details </u> </html>");
		lbl2 = new JLabel(img);
		b = new JButton("<html> <u> Continue... </u> </html>");
		b.setFont(f);
		b.setForeground(c1);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorder(new LineBorder((c2), 2));

		for ( int i = 0 ; i < num ; i++ ) {
			playerLbl[i] = new JLabel("Player " + (i+1) + ": ");
			avatarLbl[i] = new JLabel("Choose Avatar: ");
			tf[i] = new JTextField("Enter Your Name");
			
			playerLbl[i].setBounds(180,(180 + (100*i) ), 150, 25);
			avatarLbl[i].setBounds(420,(185 + (100*i) ), 180, 50);
			tf[i].setBounds(180,(220 + (100*i) ), 200, 25);

			tf[i].setFont(f1);
			tf[i].setForeground(c2);
			playerLbl[i].setFont(f1);
			playerLbl[i].setForeground(c1);
			avatarLbl[i].setFont(f1);
			avatarLbl[i].setForeground(c1);

			tf[i].setOpaque(false);

			add(playerLbl[i]);
			add(avatarLbl[i]);
			add(tf[i]);
			tf[i].addMouseListener(this);
		}
		
		lbl1.setBounds(400,110,600,50);
		b.setBounds(560,580,240,50);

		
		lbl1.setFont(f);
		lbl1.setForeground(c1);

		add(b);
		add(lbl1);
		add(lbl2);
		
		b.addActionListener(this);
	
		setLocation(0,0);
		setTitle("Win the Throne");
		setSize(1500,800);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void mouseClicked(MouseEvent e){
		for ( int i = 0 ; i < num ; i++ ) {
			if (e.getSource() == tf[i] )
		                tf[i].setText("");
				tf[i].setHorizontalAlignment(JTextField.CENTER);
		}
        }

	
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}


	public void actionPerformed ( ActionEvent e ) {
		String s[] = new String[num];

		for ( int i = 0 ; i < num ; i++ ) {
			s[i] = tf[i].getText();
		}
		
		for ( int j = 0 ; j < a ; j++ ) {
			for ( int i = 1 ; i < 8 ; i++ ) {
				if (bgot[j][i].isSelected()) {
					
					for ( int k = 0 ; k < a ; k++ ) {
						bgot[k][i].setEnabled(false); 
					}
					bgot[j][i].setEnabled(true);
				
					if ((prev[j] != -1) && ( bgot[j][prev[j]].isEnabled()) && (bgot[j][prev[j]].isSelected() == false)) {
						for ( int k = 0 ; k < a ; k++ ) {
							bgot[k][prev[j]].setEnabled(true); 
						}					
					}										
				}

				if ( e.getSource() == bgot[j][i] ) {
					prev[j] = i; 
				}
				
			}
		}

		if (e.getSource() == b) {
		
			int flag = 0;		

			for ( int i = 0  ; i < num ; i++ ) {
				String name = tf[i].getText();
				if ( name.equals(str) || name.equals("") ) {
					JOptionPane.showMessageDialog ( this, ("Player " + ( i+1 ) + ": Please enter your name") ); 
					flag = 1;
				}

				if ( prev[i] == -1 ) {
					JOptionPane.showMessageDialog ( this, ("Player " + ( i+1 ) + ": Please choose your avatar") ); 
					flag = 1;
				}
			}

			if ( flag == 0 ) { 
				new MainGame(num,s,prev);
				ap.pause();
				setVisible(false);
				
			}

			else {
				new Players(num,ap);
				setVisible(false);
			}
		}
	}

	/*public static void main ( String []args ) {
		new Players(4);
	}*/

	
}