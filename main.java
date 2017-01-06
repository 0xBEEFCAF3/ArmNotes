
import java.awt.Dimension;

import javax.swing.JFrame;

public class main {
	static frame f;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame f = new frame();
		f.setVisible(true);
		f.setSize(new Dimension(900,900));
		f.setLocationRelativeTo(null);
		f.setTitle("ArmNotes v.1.2.0");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
