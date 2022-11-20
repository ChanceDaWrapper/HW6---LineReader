package hw6;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class lineReader {

	private JFrame frame;
	private JTextArea outputText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lineReader window = new lineReader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public lineReader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		outputText = new JTextArea();
		outputText.setBounds(94, 77, 251, 87);
		frame.getContentPane().add(outputText);
		
		JLabel lblNewLabel = new JLabel("The Lines of Code and Such");
		lblNewLabel.setBounds(116, 11, 191, 46);
		frame.getContentPane().add(lblNewLabel);
		
		grabFile();
	}
	
	public void grabFile() {

		//Creating our JFileChooser
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(frame);
        
        //Calling a separate function so this is cleaner
        loadFile(file.getSelectedFile());

	}
	public void loadFile(File file) {
		//Creating the linked list
		//ArrayList<String> arr = new ArrayList<String>();
		
		//Creating the integer counts for, if, and while loops
		int forLoop = 0;
		int whileLoop = 0;
		int ifState = 0;
		
		//Integer for the lines of code
		int count = 0;
		
		//Getting the total count
		int totcount = 0;
		//try catch exception with the BufferedReader 
        try {
        	//Creating the BufferedReader
            BufferedReader reader = new BufferedReader(new FileReader(file));
            //Creating a string to read the line from the BufferedReader
            String line;
            String str = "";
            //Reading the line, and adding each line to an arraylist
            while((line = reader.readLine()) != null) {
            	//arr.add(line);
            	//Total count will count all the lines, including comments and such
            	totcount++;
            	
            	//Checking to see if the line is blank, or contains a comment
            	if(line.trim().isEmpty() || line.trim().startsWith("//") || line.trim().startsWith("*") || line.trim().startsWith("/*") ||line.trim().startsWith("*/"))
            		continue;
            	//If there isn't a comment, then it'll check the line, and add to the increments of the for or while loop
            	else {
        			count++;
            		if(line.trim().startsWith("for(") || line.trim().startsWith("for (")) {
            			forLoop++;
            		} else if(line.trim().startsWith("while(") || line.trim().startsWith("while (")) {
            			whileLoop++;
            		} else if(line.trim().startsWith("if(") || line.trim().startsWith("} else if(") || line.trim().startsWith("else if(") || line.trim().startsWith("if (") || line.trim().startsWith("} else if (") || line.trim().startsWith("else if (")) {
            			ifState++;
            		}
            	}
            }
            reader.close();
        //If it's unable to read the file
        } catch (Exception e) {
            System.out.println("Unable to read the file");
            e.printStackTrace();
        }
        
        //System.out.println("Lines of code: " + count + "\nFor loops: " + forLoop + "\nWhile Loops: " + whileLoop + "\nIf Statements: " + ifState);
        outputText.setText("Lines of code: " + count + "\nFor loops: " + forLoop + "\nWhile Loops: " + whileLoop + "\nIf Statements: " + ifState);
	}
}
