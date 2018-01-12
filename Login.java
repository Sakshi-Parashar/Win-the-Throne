import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener{

	JLabel l1;
	JTextField t1;
	Icon pic;	
	JButton b;
	JPanel p;
	AudioPlay ap;
	
	Login() {		
	
		Font f = new Font("Algerian",Font.PLAIN,35);
		Font f1 = new Font("Old English Text MT",Font.PLAIN,30);

		l1 = new JLabel("Enter no. of players ..!!");
		pic = new ImageIcon("Images\\LoginPic.jpg");
		JLabel p1 = new JLabel(pic);
		t1 = new JTextField();
		Color c1 = new Color(1,1,1);
		Color c2 = new Color(54,13,16);
		Color c3 = new Color(135,135,135);
		b = new JButton("P L A Y");
		p = new JPanel();

		p.add(l1);
		p.add(t1);
		p.add(b);
    
		l1.setBounds(700,625,300,30);
		t1.setBounds(800,660,50,40);
		p.setBounds(1000,650,500,150);
		b.setBounds(1025,650,170,30);
				
		l1.setFont(f1);
		t1.setFont(f);
		t1.setOpaque(false);
		t1.setForeground(c3);
		t1.setHorizontalAlignment(JTextField.CENTER);
		p.setBackground(c1);  
		l1.setForeground(c2);  
		b.setFont(f1);
		b.setForeground(c3);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);

		add(b);
		add(l1);
		add(t1);
		add(p);
		add(p1);

		ap = new AudioPlay("Audio\\GOT_Main_Theme.wav"); 

		setSize(1500,800);
		setVisible(true);
		setLocation(0,0);
		setLayout(null);
		setTitle("Win the Throne");
		setForeground(Color.BLACK);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b.addActionListener(this);
	}

	public static void main(String[] args){
		Login login = new Login();
	}

	public void actionPerformed(ActionEvent e){
		
		String text = t1.getText(); 
		if (text.equals("2") || text.equals("3") || text.equals("4") ) {
			int num = Integer.parseInt(t1.getText());
			new Players(num, ap);
			setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(this, "Oops! Try Again :(\nNo. of Players Allowed 2-4!!!");
			t1.setText("");
		}
		
	}	

}