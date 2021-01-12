import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setSize(425, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		display.setFont(new Font("Arial", Font.PLAIN, 20));
		display.setBorder(new LineBorder(Color.BLACK));
		

		//Setting the location of all components (should be changed later because hard coding such values is not a good practice)
		display.setBounds(20, 40, 365, 100);
		selectBtn.setBounds(150, 185, 100, 40);
		convertBtn.setBounds(150, 250, 100, 40);

		//Registering the action listeners
		selectBtn.addActionListener(new SelectFileListener());
		
		//Adding to frame
		frame.add(display);
		frame.add(selectBtn);
		frame.add(convertBtn);
		
		frame.setVisible(true);
	}
	
	//Inner class to act as the action listener
	class SelectFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	public static void main (String args[]) {
		new pdfMaker();
	}
}
