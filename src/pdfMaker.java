import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Font;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;

import com.aspose.words.*;

public class pdfMaker {
	//Declarations
	private JFrame frame;
	private JLabel display;
	private JButton selectBtn;
	private JButton convertBtn;
	private File file;
	
	pdfMaker(){
		setUpGUI();
	}
	
	private void setUpGUI() {
		//Set the look and feel to the system's default look and feel
		//This makes the 'open' dialog box look better
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Initializations
		frame = new JFrame("pdfMaker");
		display = new JLabel("");//Don't know why but if I don't put an empty string the JLabel doesn't show up
		selectBtn = new CustomBtn("Select File");
		convertBtn = new CustomBtn("Convert");
		
		//Setting up
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(425, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		
		display.setFont(new Font("Arial", Font.PLAIN, 20));
		display.setHorizontalAlignment(SwingConstants.CENTER);
		display.setOpaque(true);//Make the display opaque so that the background color can be set
		display.setBackground(Color.WHITE);//Setting the background color of the display to white
		
		selectBtn.setFocusPainted(false);//Don't allow the rectangle which indicates focus to display when the button is clicked
		convertBtn.setFocusPainted(false);
		
		//Setting the location of all components (should be changed later because hard coding such values is not a good practice)
		display.setBounds(20, 40, 365, 100);
		selectBtn.setBounds(150, 185, 100, 40);
		convertBtn.setBounds(150, 250, 100, 40);

		//Registering the action listeners
		selectBtn.addActionListener(new SelectFileListener());
		convertBtn.addActionListener(new ConvertFileListener());
		
		//Adding to frame
		frame.add(display);
		frame.add(selectBtn);
		frame.add(convertBtn);
		
		frame.setVisible(true);
	}
	
	//Custom buttons - code for this is taken from https://harryjoy.me/2011/08/21/different-button-shapes-in-swing/
	class CustomBtn extends JButton {
		
		CustomBtn(String label){
			super(label);
			Dimension size = getPreferredSize();
			size.width = size.height = Math.max(size.width, size.height);
			setPreferredSize(size);
			setContentAreaFilled(false);
		}
		
		protected void paintComponent(Graphics g) {
		     if (getModel().isArmed()) {
		          g.setColor(Color.lightGray);
		     } else {
		          g.setColor(getBackground());
		     }
		     g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
		     super.paintComponent(g);
		}
		protected void paintBorder(Graphics g) {
		     g.setColor(getForeground());
		     g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
		}
		
		Shape shape;
		public boolean contains(int x, int y) {
		     if (shape == null || !shape.getBounds().equals(getBounds())) {
		          shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
		     }
		     return shape.contains(x, y);
		}
	}
	
	//Inner class to act as the action listener
	class SelectFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new FileNameExtensionFilter("MS Word file(.docx)", "docx"));//Only allow .docx files to be selected
			int result = fileChooser.showOpenDialog(frame);//Display the 'open' dialog box for the viewer to select the file
			
			if (result == JFileChooser.APPROVE_OPTION) {//Check whether the user has chosen a valid file
				file = fileChooser.getSelectedFile();
				display.setText(file.getName());//Display the selected file name
			}
		}
	}
	
	class ConvertFileListener implements ActionListener{//Converts the chosen file when 'convert' btn is pressed
		
		public void actionPerformed(ActionEvent e) {
			
			FileOutputStream outputStream = null;
			try {//The conversion can throw an exception
				Document doc = new Document(file.getAbsolutePath());
				
				//Let user choose the save location
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("Save");//Change the default 'open' label on the button in the dialog box to something meaningful
				fileChooser.setCurrentDirectory(null);
				fileChooser.setFileFilter(new DirFilter());//Filter to only display folders
				int result = fileChooser.showOpenDialog(frame);
				File chosenDir = null;
				
				//If a valid option is chosen
				if (result == JFileChooser.APPROVE_OPTION) {
					chosenDir = fileChooser.getSelectedFile();//Get the selected directory
					outputStream = new FileOutputStream(chosenDir);//Get a outputStream which has to be given to the save()
				}
				
				//Saving the document to the chosen location as pdf (the user has to write "fileName.pdf" instead of only "fileName"
				//while saving for it to work. I don't know why it has to be this way...lol XD
				doc.save(outputStream, SaveFormat.PDF);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		private class DirFilter extends javax.swing.filechooser.FileFilter {
			public boolean accept(File file) {
				return file.isDirectory();
			}

			//This method is abstract in FileFilter library class and thus, we have to implement it
			@Override
			public String getDescription() {
				return null;
			}
		}
		
	}
	
	public static void main (String args[]) {
		new pdfMaker();
	}
}
