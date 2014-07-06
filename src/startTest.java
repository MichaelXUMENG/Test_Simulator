import java.io.File;    


import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import java.lang.Boolean;
import java.util.Date; 
import java.text.SimpleDateFormat; 




public class startTest extends JFrame implements ActionListener, ListSelectionListener, MouseListener,KeyListener{
	
	JButton select = new JButton("Select");
	JButton next = new JButton("Next");
	JButton previous = new JButton("Previous");
	JButton cancel = new JButton("Cancel");
	JButton save = new JButton("Save");
	JButton review = new JButton("Review");
	
	textArea questionPanel;
	
	JRadioButton buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonV;
	ButtonGroup answerGroup;
	textArea areaA, areaB, areaC, areaD, areaE, areaF;

	JCheckBox showAnswer, important;
	
	String fileName;
	String saveFile;
	char yours;
	int row;
	
	char yourAnswer;
	
	excel Question, Answer;
	
	Boolean flag;
	Boolean change;
	Boolean show;
	Boolean saveChange;
	
	int rowNumber;
	
	
	char[] tempAnswer;
	char[] importantQuestion;
	
	
	
/****************************************************************************************************************/
	public startTest() throws IOException{
		
		Question = new excel();
		Answer = new excel();

		flag = false;
		change = false;
		show = false;
		saveChange = true;
		
		saveFile = null;
		
		this.pack();
		this.setSize(1200, 600);
		this.setTitle("Record Functions");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
/*********************************************
 * This part is for the buttons
 * ******************************************************************/
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1,2,80,0));
		controlPanel.add(previous);
		controlPanel.add(next);
		
		previous.setEnabled(false);
		next.setEnabled(false);

		
		previous.addMouseListener(this);
		next.addMouseListener(this);
		
		
		JPanel functionPanel = new JPanel();
		functionPanel.setLayout(new GridLayout(4,1,0,50));
		functionPanel.add(select);
		functionPanel.add(save);
		functionPanel.add(review);
		functionPanel.add(cancel);
		select.addMouseListener(this);
		cancel.addMouseListener(this);
		save.addMouseListener(this);
		review.addMouseListener(this);
		
		
		
		
/*******************************************
 * This part is the text area for showing question and the answers
 * ***********************************************************************/
		questionPanel = new textArea();
		questionPanel.setTextArea("Hello!\nWelcome to the Text Simulator from MENG!\n"+
		"Please click SELECT to choose one chapter!");
		
		
		/*The answer area is being created with a text area and a radio button for each option*/
		JPanel checkA = new JPanel();
		checkA.setLayout(new BorderLayout(5,0));
		checkA.add(buttonA = new JRadioButton("A"), BorderLayout.WEST);
		checkA.add(areaA = new textArea(), BorderLayout.CENTER);
		
		JPanel checkB = new JPanel();
		checkB.setLayout(new BorderLayout(5,0));
		checkB.add(buttonB = new JRadioButton("B"), BorderLayout.WEST);
		checkB.add(areaB = new textArea(), BorderLayout.CENTER);
		
		JPanel checkC = new JPanel();
		checkC.setLayout(new BorderLayout(5,0));
		checkC.add(buttonC = new JRadioButton("C"), BorderLayout.WEST);
		checkC.add(areaC = new textArea(), BorderLayout.CENTER);
		
		JPanel checkD = new JPanel();
		checkD.setLayout(new BorderLayout(5,0));
		checkD.add(buttonD = new JRadioButton("D"), BorderLayout.WEST);
		checkD.add(areaD = new textArea(), BorderLayout.CENTER);
		
		JPanel checkE = new JPanel();
		checkE.setLayout(new BorderLayout(5,0));
		checkE.add(buttonE = new JRadioButton("E"), BorderLayout.WEST);
		checkE.add(areaE = new textArea(), BorderLayout.CENTER);
		
		//This panel is for the check boxes and answer showing
		JPanel checkF = new JPanel();
		checkF.setLayout(new GridLayout(1,3));
		areaF = new textArea();
		areaF.setNewSize(10, 10);
		checkF.add(areaF);
		
		showAnswer = new JCheckBox("Show Answer", false);
		showAnswer.addActionListener(this);
		checkF.add(showAnswer);
		
		important = new JCheckBox("Important", false);
		important.addActionListener(this);
		checkF.add(important);
		
		/*add these 6 small parts into the answerPanel*/
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new GridLayout(3,2,5,5));
		answerPanel.add(checkA);
		answerPanel.add(checkB);
		answerPanel.add(checkC);
		answerPanel.add(checkD);
		answerPanel.add(checkE);
		answerPanel.add(checkF);
		
		/*add these 7 radio button into a button group so that only one button can be chosen at one time*/
		answerGroup = new ButtonGroup();
		answerGroup.add(buttonA);
		answerGroup.add(buttonB);
		answerGroup.add(buttonC);
		answerGroup.add(buttonD);
		answerGroup.add(buttonE);
		answerGroup.add(buttonF);
		answerGroup.add(buttonV = new JRadioButton());
		
		buttonA.addActionListener(this);
		buttonB.addActionListener(this);
		buttonC.addActionListener(this);
		buttonD.addActionListener(this);
		buttonE.addActionListener(this);
//		buttonF.addActionListener(this);
		buttonV.addActionListener(this);
		
		buttonA.addMouseListener(this);
		buttonB.addMouseListener(this);
		buttonC.addMouseListener(this);
		buttonD.addMouseListener(this);
		buttonE.addMouseListener(this);
//		buttonF.addMouseListener(this);
		
		
/******************************************************
 * Set up the frame of startTest.
 * *********************************************************************************/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,10));
		mainPanel.add(questionPanel, BorderLayout.NORTH);
		mainPanel.add(answerPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout(0,10));
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(functionPanel, BorderLayout.EAST);
		
		
		
		
		Dimension screenSize = 
				Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
				
		int x = (screenWidth - this.getWidth()*1)/2;
		int y = (screenHeight - this.getHeight()*3/2)/2;
		this.setLocation(x, y);
		
		this.setVisible(true);
	}
	
	
	
/****************************************************************************************/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
/****************************************************************************************/
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
/****************************************************************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
/*****************************************************************************************
	 * This is for the mouse action
	 * including "previous", "next", "select", "cancel"
	 * 
	 * ***********************************************/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
/***********************************************************************************************************************/
		/*Go to the previous question*/
		if(e.getSource() == previous){
			/*store your answer into a workbook*/
/*			String label = Character.toString(yourAnswer);
			Answer.continueWritableExcel("Chapter 1.xls", 0, label, row);
*/			

			if(change){
				tempAnswer[row] = yourAnswer;
				change = false;
			}

			saveChange = false;

			/*clear all the answer area if it is the first question*/
			row-=1;
			buttonV.setSelected(true);
			if(row<=0){
				questionPanel.setTextArea("There is FIRST ONE!!\nPlease click NEXT to continue!");
				this.setTextFiledBlank();
				row = 0;
			}
			else{
				try{
					/*show the question and answer in the text area*/
					Question.openExcel(fileName);  
					questionPanel.setTextArea("QUESTION "+row+" / "+(rowNumber-1)+":    "+Question.readCellContent(1, row));
					areaF.setTextArea("");

					this.setAnswerFiledFilled(row);
					this.showImportant();

			       }   catch (Exception ex)   {    
			           System.out.println(ex);    
			       }  
				/*show your Answers on the radio button*/

				yours = tempAnswer[row];
				this.setRadioButtonSelected(yours);
				
			}
		}
		
		
/***********************************************************************************************************************/
		/*Go to the next question*/
		if(e.getSource() == next){			
			saveChange = false;
			
			if(change){
				tempAnswer[row] = yourAnswer;
				change = false;
			}
			
			row+=1;	
			buttonV.setSelected(true);
			if(row>(rowNumber-1)){
				questionPanel.setTextArea("There is Last ONE!!\nPlease click PREVIOUS to continue!");
				this.setTextFiledBlank();
				row = rowNumber;
			}
			
			else{
				try{  
					Question.openExcel(fileName);  
					questionPanel.setTextArea("QUESTION "+row+" / "+(rowNumber-1)+":    "+Question.readCellContent(1, row));
					areaF.setTextArea("");

					this.setAnswerFiledFilled(row);
					this.showImportant();

			       
				}   catch (Exception ex){       
					System.out.println(ex);   
				}  
				
				/*show your Answers on the radio button*/

				yours = tempAnswer[row];
				this.setRadioButtonSelected(yours);
			}
			
		}
		
		
/***********************************************************************************************************************/
		
       /*Select the chapters*/
		
		if(e.getSource() == select){
			
			buttonV.setSelected(true);
			row = 1;
			
			/*choose the excel file in a file chooser and copy the address into a string*/
			//store the path of chosen file in filename
			fileName = this.getFilePath();
			
			try{
				if(flag){
					Question.closeWorkbook();
				}
				
				Question.openExcel(fileName);  
				rowNumber = Question.getQuestionNumber();
				questionPanel.setTextArea("QUESTION "+row+" / "+(rowNumber-1)+":    "+Question.readCellContent(1, row));
				tempAnswer = new char[rowNumber];
				importantQuestion = new char[rowNumber];
				this.setTextFiledBlank();
				this.setAnswerFiledFilled(row);
				this.showImportant();

			
			}catch (Exception ex){    
				System.out.println(ex);    
			}     
			
			previous.setEnabled(true);
			next.setEnabled(true);
			flag = true;
		}

		
/***********************************************************************************************************************/
		/*Show the reviews*/
		if(e.getSource() == review){
//			char[] currectAnswer = Question.getCurrectAnswer();
			new reviewPanel(tempAnswer, Question, rowNumber, this, importantQuestion);
		}
		
/***********************************************************************************************************************/
		
		
/***************************************************************************************************************************/
		if(e.getSource() == save){
			this.saveFile();
		}
		
/*************************************
 * This is for the mouse action of Radio buttons
 * 
 * ***********************************************************************************************************************/
		
		if(e.getSource() == buttonA){
			yourAnswer = 'A';
			change = true;
		}
		if(e.getSource() == buttonB){
			yourAnswer = 'B';
			change = true;
		}
		if(e.getSource() == buttonC){
			yourAnswer = 'C';
			change = true;
		}
		if(e.getSource() == buttonD){
			yourAnswer = 'D';
			change = true;
		}
		if(e.getSource() == buttonE){
			yourAnswer = 'E';
			change = true;
		}
		if(e.getSource() == buttonF){
			yourAnswer = 'F';
			change = true;
		}

		
		
	}

	
/****************************************************************************************/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	
	}

	
/****************************************************************************************/
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/*Go back to the mainFrame*/
		if(e.getSource() == cancel){
			if(!saveChange){
				int decide = JOptionPane.showConfirmDialog(this, "Do you want to save your Answer?", "Confirm Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
				if(decide == JOptionPane.YES_OPTION){
					this.saveFile();
					this.dispose();
				}
				else if(decide == JOptionPane.NO_OPTION){
					this.dispose();
				}
			}  
			else
				this.dispose();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
/****************************************************************************************/
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
/****************************************************************************************/
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
/****************************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showAnswer){
			if(showAnswer.isSelected()){
				show = true;
				areaF.setTextArea(Question.readCellContent(7, row));
			}
			else{
				show = false;
				areaF.setTextArea("");
			}
		}
		
		if(e.getSource() == important){
			if(important.isSelected()){
				importantQuestion[row] = 'T';
			}
			else{
				importantQuestion[row] = 0;
			}
		}
	}

	
/*****************************************************************************************************************************/
	//This part is for the method!!
	
	public void saveFile(){
		
		if(saveFile == null){
//			openClassFile fileFilter=new openClassFile ();
			JFileChooser savejfc = new JFileChooser();
			savejfc.setCurrentDirectory(new File("."));
//			savejfc.setFileFilter(fileFilter);
			savejfc.showSaveDialog(this);
			
			//store the path of chosen file in filename
			saveFile = savejfc.getSelectedFile().getPath();
		}
		
		/*to get the date of today*/
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");  //可以方便地修改日期格式
		String date = dateFormat.format(now); 
		
		if(saveFile != null){
			//saveFile is the path of AnswerBook, and fileName is the path of the Question
			int error = Answer.openWritableExcel(saveFile, date, tempAnswer.length, tempAnswer,importantQuestion, fileName);
			if(error == 1){
				JOptionPane.showMessageDialog(null, "Your Answer has been Saved Successfully!!", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
				saveChange = true;
			}
		}
	}
	
	/**********************************************************************/
	public void setTextFiledBlank(){
		areaA.setTextArea("");
		areaB.setTextArea("");
		areaC.setTextArea("");
		areaD.setTextArea("");
		areaE.setTextArea("");
		areaF.setTextArea("");
	}
	/**********************************************************************/
	public void setRowValue(int newRow){
		row = newRow;
		buttonV.setSelected(true);
		try{  
			Question.openExcel(fileName);  
			questionPanel.setTextArea("QUESTION "+row+" / "+(rowNumber-1)+":    "+Question.readCellContent(1, row));
			areaF.setTextArea("");

			this.setAnswerFiledFilled(row);
			this.showImportant();
	       
		}   catch (Exception ex){       
			System.out.println(ex);   
		}  
		
		yours = tempAnswer[row];
		this.setRadioButtonSelected(yours);
	}
	
	/**********************************************************************/
	public void setAnswerFiledFilled(int rowNum){
		int i;
		for(i=1; i<7; i++){		        	        	
			switch (i){		        	
			case 1: areaA.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			case 2: areaB.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			case 3: areaC.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			case 4: areaD.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			case 5: areaE.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			case 6: if(show) areaF.setTextArea(Question.readCellContent(i+1, rowNum));
			break;
			}		        
		}  
	}
	/**********************************************************************/
	public void setRadioButtonSelected(char situation){
		
		switch(situation){
		case 'A': buttonA.setSelected(true);
		break;
		case 'B': buttonB.setSelected(true);
		break;
		case 'C': buttonC.setSelected(true);
		break;
		case 'D': buttonD.setSelected(true);
		break;
		case 'E': buttonE.setSelected(true);
		break;
		case 'F': buttonF.setSelected(true);
		break;
		}
	}
	/**********************************************************************/
	public String getFilePath(){

		openClassFile fileFilter=new openClassFile ();
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("."));
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setFileFilter(fileFilter);
		jfc.showOpenDialog(this);

		String path = jfc.getSelectedFile().getPath();
		return path;
		
	}
	/**********************************************************************/
	public void showImportant(){
		important.setSelected(false);
		if(importantQuestion[row] == 'T'){
			important.setSelected(true);
		}
	}
	/**********************************************************************/

	
}
