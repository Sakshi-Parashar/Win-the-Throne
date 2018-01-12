import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Dragon extends JFrame implements WindowListener{

	JLabel l1;
	JLabel l2;
	Icon gif;
	AudioPlay ap, apmg;	
	long clipTime;	
	
	Dragon(String eaten, AudioPlay apmg, long clipTime) {		
	
		this.apmg = apmg;
		this.clipTime = clipTime;

		Font f = new Font("Old English Text MT",Font.PLAIN,40);
		Font f1 = new Font("Old English Text MT",Font.PLAIN,30);

		l1 = new JLabel("Dracarys!");
		l2 = new JLabel(eaten+": The Dragon ate you");
		gif = new ImageIcon("Images\\Dragon.gif");
		JLabel p1 = new JLabel(gif);
		Color c1 = new Color(255,255,255);
    
		l1.setBounds(170,100,200,40);
		l2.setBounds(120,160,350,30);
				
		l1.setFont(f);
		l2.setFont(f1);
		l2.setOpaque(false);
		l2.setForeground(c1); 
		l1.setForeground(c1);  

		add(l1);
		add(l2);
		add(p1);

		ap = new AudioPlay("Audio\\Dracarys.wav");

		setSize(500,250);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle("Dracarys!");
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