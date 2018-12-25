package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame {
	
	public static JFrame frame;
	
	public static void main(String[] args) {
		
		frame = new JFrame("Solace");
		frame.setIconImage(new ImageIcon("Solace.ico").getImage());
		frame.setSize(662, 558);	//662, 525 just covers lines
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
