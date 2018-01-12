import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Versus extends JFrame implements ActionListener {

	JLabel names[] = new JLabel[4];
	Icon img[] = new ImageIcon[4];
	JLabel gotChar = new JLabel[4];
	JButton b;

	static int num;
	String s[] = new String[4];
	static int gotNo[] = new int[4];

	Versus(String s[], int prev[], int num) {

		this.num = num;
		for ( int i = 0 ; i < num ; i++ ) {
			this.s[i] = s[i];
			gotNo[i] = prev[i];
		}

		for ( int i = 0 ; i < num ; i++ ) {
			names[i] = new JLabel(s[i]);	
			names[i].setBounds((100 + (150*i)), 600, 100, 100);
			add(names[i]);
			
		
			img[i] = new ImageIcon("Images\\GOT"+(gotNo[i]+1)+".jpg");
			gotChar[i] = new JLabel(img[i]);
			gotChar[i].setBounds((150*i),0, 80, 80);
			add(gotChar[i]);
		}

		b = new JButton("Next");
		b.setBounds(725,650,50,50);
		add(b);
		b.addActionListener(this);

		setVisible(true);
		setLayout(flowLayout);
		setSize(1500,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == b ) {
			new MainGame(num, s, gotNo );
		}
	}
	public static void main(String []args) {
		String s[] = {"A","B","C","D"};
		int num[] = {2,3,4,5}; 
		new Versus(s,num,4);
	}
}