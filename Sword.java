import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Sword extends JFrame implements WindowListener{

	JLabel l1;
	JLabel l2;
	Icon gif;
	AudioPlay ap, apmg;	
	long clipTime;	
	
	Sword(String getter, AudioPlay apmg, long clipTime) {	

		this.apmg = apmg;
		this.clipTime = clipTime;	
	
		Font f = new Font("Old English Text MT",Font.PLAIN,40);
		Font f1 = new Font("Old English Text MT",Font.PLAIN,30);

		l1 = new JLabel(getter);
		l2 = new JLabel("You got the Sword");
		gif = new ImageIcon("Images\\Sword.gif");
		JLabel p1 = new JLabel(gif);
		Color c1 = new Color(255,255,255);
    
		l1.setBounds(160,100,200,40);
		l2.setBounds(110,160,300,30);
				
		l1.setFont(f);
		l2.setFont(f1);
		l2.setOpaque(false);
		l2.setForeground(c1); 
		l1.setForeground(c1);  

		add(l1);
		add(l2);
		add(p1);
	
		ap = new AudioPlay("Audio\\ValarM.wav");

		setSize(480,244);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("You got the Sword");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}

	public void windowClosing ( WindowEvent e ) {
		ap.pause();
		apmg.play(clipTime);
	}

	public void windowDeactivated ( WindowEvent e ) {}

	public void windowActivated ( WindowEvent e ) {}

	public void windowDeiconified ( WindowEvent e ) {}

	public void windowIconified ( WindowEvent e ) {}

	public void windowClosed ( WindowEvent e ) {}

	public void windowOpened ( WindowEvent e ) {}

}