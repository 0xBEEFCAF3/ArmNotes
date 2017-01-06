import javax.swing.*;
import java.io.*;
import java.awt.*; // for Dimension
import java.awt.event.*; // for action events


public class index  {
	private boolean saveStatus;
	private int indentNum = 0;
	
	
	public index(){
		saveStatus = true;
		JFrame frame = new frame();
		frame.setVisible(true);
		frame.setSize(new Dimension(500,500));
		frame.setTitle("ArmNotes v.1.2.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TEXT FIELD
		//JTextField text;

		//MAKING THE MENU BAR 
		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		index obj = new index();
	}
	//ALL EVENT HANDLERS

	
	//END OF MAIN CLASS
}
