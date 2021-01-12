import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class pdfMaker {
	//Declarations
	private JFrame frame;
	private JLabel display;
	private JButton selectBtn;
	private JButton convertBtn;
	
	pdfMaker(){
		setUpGUI();
	}
	
	private void setUpGUI() {
		//Initializations
		frame = new JFrame("pdfMaker");
		display = new JLabel("");//Don't know why but if I don't put an empty string the JLabel doesn't show up
		selectBtn = new JButton("Select File");
		convertBtn = new JButton("Convert");
		
		//Setting up
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(425, 425);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		display.setFont(new Font("Arial", Font.PLAIN, 20));
		display.setBorder(new LineBorder(Color.BLACK));
		

		//Setting the location of all components (should be changed later because hard coding such values is not a good practice)
		display.setBounds(20, 40, 365, 100);
		selectBtn.setBounds(150, 185, 100, 40);
		convertBtn.setBounds(150, 250, 100, 40);

		//Adding to frame
		frame.add(display);
		frame.add(selectBtn);
		frame.add(convertBtn);
		
		frame.setVisible(true);
	}
	
	public static void main (String args[]) {
		new pdfMaker();
	}
}
