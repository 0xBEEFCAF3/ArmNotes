import java.awt.*;
import javax.swing.*;
public class Help{
	public JFrame helpFrame;
	
	public Help()
	{
		helpFrame = new JFrame();
		helpFrame.setVisible(true);
		helpFrame.setSize(new Dimension(400,400));
		helpFrame.setLocationRelativeTo(null);
		helpFrame.setTitle("Help");
		helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//helpFrame.setLayout(new GridLayout(3,2));
		helpFrame.setBackground(Color.WHITE);
		
	}
}
