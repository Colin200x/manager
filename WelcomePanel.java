package manager;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7620455673732943408L;

	public WelcomePanel() {
		setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 20));
		setBackground(Color.WHITE);
		ImageIcon image = new ImageIcon("img.png");
		
		ImageIcon i1 = new ImageIcon("i1.jpg");
		ImageIcon i2 = new ImageIcon("i2.jpg");
		ImageIcon i3 = new ImageIcon("i3.jpg");
		ImageIcon i4 = new ImageIcon("i4.jpg");
		
		JLabel welcome = new JLabel(image);
		
		JLabel img1 = new JLabel(i1);
		JLabel img2 = new JLabel(i2);
		JLabel img3 = new JLabel(i3);
		JLabel img4 = new JLabel(i4);
		
		add(welcome);
		add(img1);
		add(img2);
		add(img3);
		add(img4);
		
	}
	 
}
