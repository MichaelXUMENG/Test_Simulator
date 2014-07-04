import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class textArea extends JPanel{
	private JTextArea text;
	private JScrollPane scrollPane;
	
	public textArea(){
		scrollPane = new JScrollPane(text = new JTextArea());
		text.setFont(new Font("Serif", Font.BOLD, 24));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		
		scrollPane.setPreferredSize(new Dimension(300, 200));
		
		setLayout(new BorderLayout(5,5));
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void setTextArea(String message){
		text.setText(message);
	}
	
	public void setTextColor(Color c){
		text.setForeground(c);
	}
	
	public void setNewSize(int longth, int heigth){
		scrollPane.setPreferredSize(new Dimension(longth, heigth));
	}
}