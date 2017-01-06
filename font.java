import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class font{
	public JFrame fontFrame;
	//static  frame frameObj = new frame();
	public JButton arial;
	public JComboBox combo;
	public frame frameObj;
	private String[] options = {"Arial","Times new Roman","Veranda","Georgia","Papyrus"};
	public JSlider slider;
	public JComboBox styleCombo;
	private String[] styleOptions = {"Normal","Italic","Bold","Both"};
	public JButton exit;
	public font(){
		frameObj = new frame();
		//MAKING THE FRAME
		fontFrame = new JFrame();
		fontFrame.setVisible(true);
		fontFrame.setSize(new Dimension(400,400));
		fontFrame.setLocationRelativeTo(null);
		fontFrame.setTitle("Font Selection");
		fontFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fontFrame.setLayout(new GridLayout(3,3));
		fontFrame.setBackground(Color.WHITE);
		//Designing the drop down menu
		fontFrame.add(new JLabel("Change the font:"));
		combo = new JComboBox(options);
		fontFrame.add(combo);
	    //font style
		fontFrame.add(new JLabel("Change the style:"));
		styleCombo = new JComboBox(styleOptions);
		fontFrame.add(styleCombo);
		//slider
		fontFrame.add(new JLabel("Change the size:"));
	    slider= new JSlider(JSlider.HORIZONTAL,0,40,5);
	    fontFrame.add(slider);
	    //Close Button
	    exit = new JButton("Close");
	    exit.addActionListener(new exitAction());
	    fontFrame.add(exit);
	}
	class exitAction implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {

			fontFrame.setVisible(false);
			fontFrame.dispose();
		}
		
	}

//END OF FONT CLASS
}
