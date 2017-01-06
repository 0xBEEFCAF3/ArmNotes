import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*; // for Dimension
import java.awt.event.*; // for action events
public class frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean saveStatus;
	private int indentNum = 0;
	public JTextArea text;
	public JMenuBar menubar;
	public JMenu file;
	public JMenu Edit;
	public JMenu Help;
	public JMenuItem newFile;
	public JMenuItem exit;
	public JMenuItem save;
	public JMenuItem saveAs;
	public JMenuItem open;
	public JMenuItem copy;
	public JMenuItem paste;
	public JMenuItem font;
	public font fontObj;
	public JPanel footer;
	public boolean controlAct;
	public JLabel wordCountLabel;
	public JLabel fileLenghtLabel;

	public frame(){
		saveStatus = true;		
		//TEXT FIELD
		text = new JTextArea(new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "\n");
		text.setFont(new Font("Garamond	",Font.PLAIN,18));
		text.getDocument().addDocumentListener(new textChanged());
		text.setBackground(Color.decode("#003366"));
		text.setForeground(Color.decode("#FF6600"));
		text.setCaretColor(Color.WHITE);

		add(text,BorderLayout.CENTER);
		final JScrollPane sp = new JScrollPane(text);
		add(sp);// add Scroll functionality
		//MAKING THE MENU BAR 
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		file = new JMenu("File");
			menubar.add(file);
				newFile = new JMenuItem("New File");//New file functionality
				file.add(newFile);
				newFile.addActionListener(new newFileAction());
			
			file.addSeparator();
			
			exit = new JMenuItem("Exit");
				exit.addActionListener(new exitAction());
				file.add(exit);
			
			save = new JMenuItem("Save");
				file.add(save);
				
			saveAs = new JMenuItem("Saved as...");
				file.add(saveAs);
				saveAs.addActionListener(new saveAsAction());
				
			open = new JMenuItem("open");
				file.add(open);
				open.addActionListener(new openAction());

		Edit = new JMenu("Edit");//FOR EDITING TAB
			menubar.add(Edit);
			copy = new JMenuItem("Copy");
			Edit.add(copy);
			
			paste = new JMenuItem("Paste");
			Edit.add(paste);
			
			font = new JMenuItem("Font Selection Menu");
			font.addActionListener(new fontAction());
			Edit.add(font);
			
		//For Help tab
		Help = new JMenu("Help");
			menubar.add(Help);
			
		//Making the footer
		footer= new JPanel();
		footer.setBorder(new BevelBorder(BevelBorder.LOWERED));
		footer.setPreferredSize(new Dimension(getWidth(), 16));
		footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
		wordCountLabel = new JLabel("Word Count: " + wordCount(text));
		footer.add(wordCountLabel);
		//footer.add(fileLenghtLabel);
		add(footer,BorderLayout.SOUTH);//Finally add the footer
		
		//indent functionality
		text.append("I.");
		text.addKeyListener(new keyBoardAction());
		
		
		
	}//END OF CONSTRUCTOR
	
	public JTextArea getTextArea()
	{
	     return text;
	}
	public void fontex(String s){
		if(text.getSelectedText() != null) System.out.println(text.getSelectedText());;
		
		switch (s)
		{
		case "Arial":getTextArea().setFont(new Font("Arial",Font.PLAIN,18));
		break;
		case "Times new Roman":getTextArea().setFont(new Font("Times new Roman",Font.PLAIN,18));
		break;
		case "Verdana":getTextArea().setFont(new Font("Verdana",Font.PLAIN,18));
		break;
		case "Georgia":getTextArea().setFont(new Font("Georgia",Font.PLAIN,18));
		break;
		case "Papyrus":getTextArea().setFont(new Font("Papyrus",Font.PLAIN,18));
		break;
		}
		
	}
	public void fontex(int i){
		Font font = text.getFont();
		float size = font.getSize();

		if(size > i){
			size -= 1.0f;
		}else size+=1.0f;
		text.setFont( font.deriveFont(size) );
	}
	public void fontstyle(String s)
	{		
		Font font = text.getFont();
		int size = font.getSize();
		String fontName = font.getName();

		switch (s)
		{
		case "Normal":Font plain = new Font(fontName,Font.PLAIN,size);
					  text.setFont(plain);
		break;
		case "Bold": Font bold = new Font(fontName,Font.BOLD,size);
		  			 text.setFont(bold);
		break;
		case "Italic":Font italic = new Font(fontName,Font.ITALIC,size);
		  			  text.setFont(italic);;
		break;
		case "Both":Font newFont = new Font(fontName,Font.BOLD|Font.ITALIC,size);
		  			text.setFont(newFont);;
		break;
		}
	}

	public String wordCount(JTextArea txt){
		int temp = txt.getText().trim().split(" ").length;

		return Integer.toString(temp);
	}
	
	public void displayWordCount()
	{
		wordCountLabel.setText("Word Count: " + wordCount(text));
	}

	public void indent(int i)
	{
		 switch(i)
	        {
	        case 0:text.append("\n" + "I.");
	        break;
	        case 1:text.append("\n" + "\t-");
	        break;
	        case 2:text.append("\n" + "\t\t*");
	        break;
	        case 3:text.append("\n" + "\t\t\t>>");
	        break;
	        case 4:text.append("\n" + "\t\t\t\t~");
	        break;
	        }
	}


	//For getting file size 
	@SuppressWarnings("null")
	public long getFileSize() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		File file = null;
        text.write(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        file = new File(file.getAbsolutePath());
		return file.length();
	}
	
	public void displayFileLenght() throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		fileLenghtLabel.setText("File lenght: " + getFileSize());
	}

	//ALL EVENT HANDLERS
	
	class exitAction implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);}
			
			
		}
		
	}
	class newFileAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(saveStatus){		
				text.setText("");
			}else{
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					text.setText("");}			
				}//End of else
		}
		
	}
	class textChanged implements DocumentListener{

		
		public void insertUpdate(DocumentEvent e) {
			saveStatus = false;
			displayWordCount();
			
			//For displaying file lenght
			
			
			
		}

		
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			saveStatus = false;

		}

		
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			saveStatus = false;
			displayWordCount();


		}
		
	}
	class openAction implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			//FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			//c.setFileFilter(filter);

		      // Demonstrate "Open" dialog:
		      int rVal = c.showOpenDialog(frame.this);
		      if(rVal == JFileChooser.APPROVE_OPTION) 
		      {	try
		      {
		    	  File file;
		    	  file = c.getSelectedFile();    
		    	  BufferedReader in = new BufferedReader(new FileReader(file));
		    	  String line = in.readLine();
		    	  text.setText("");
		    	  while(line != null){
		    	    text.setText( text.getText() + line + "\n");
		    	    line = in.readLine();
		    	  }
		      }catch(Exception e1){}
		      }    
			}
		}
	class saveAsAction implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser("");
			c.setDialogTitle("Specify a file to save");
			
			int rVal = c.showSaveDialog(frame.this);
			if(rVal == JFileChooser.APPROVE_OPTION)
			{
				try
				{	
					File file = c.getSelectedFile();
                    text.write(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
					if (!file.getName().endsWith(".txt")) {
				         file = new File(file.getAbsolutePath() + ".txt");
				      }
		            saveStatus = true;
		            JOptionPane.showMessageDialog(null, "The Message was Saved Successfully!",
	                        "Success!", JOptionPane.INFORMATION_MESSAGE);
		            
				}catch(IOException e1){JOptionPane.showMessageDialog(null, "The Text could not be Saved!",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);}
			}

		}
		
	}
	class fontAction implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
			fontObj = new font();
			fontObj.combo.addActionListener(new comboAction());
			fontObj.slider.addChangeListener(new sliderAction());
			fontObj.styleCombo.addActionListener(new styleAction());

		}
		
	}
	class comboAction implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
            String s = (String) fontObj.combo.getSelectedItem();//get the selected item
            fontex(s);
		}
		
	}
	class sliderAction implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			int i = (int) fontObj.slider.getValue();
			fontex(i);
		}
		
	}
	class styleAction implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			 String s = (String) fontObj.styleCombo.getSelectedItem();//get the selected item
	         fontstyle(s);
			
		}
		
	}
	class keyBoardAction implements KeyListener{
		
		
		public void keyTyped(KeyEvent e) {

			// TODO Auto-generated method stub
			 
		}

		
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			 
			 if(e.getKeyCode()==KeyEvent.VK_CONTROL)
			 {
				 indent(indentNum);
			 }
			 
			 if (e.getKeyCode()==KeyEvent.VK_ENTER)
			 {				 	
				 	if(indentNum<4)indentNum++;indent(indentNum);
			       
			 }else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			 {
				 if(indentNum == 0)indent(indentNum);
				 else indentNum--;indent(indentNum);
			 }
		}

		
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}//end of  Action class
	
//End of class
}
