import javax.swing.*;
public class pdfMaker {
	//Declarations
	private JFrame frame;
	
	pdfMaker(){
		setUpGUI();
	}
	
	private void setUpGUI() {
		//Initializations
		frame = new JFrame("pdfMaker");
		
		//Setting up
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(425, 425);
		frame.setLocationRelativeTo(null);
		
	}
	
	public static void main (String args[]) {
		new pdfMaker();
	}
}
