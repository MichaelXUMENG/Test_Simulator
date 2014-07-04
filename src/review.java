import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class review extends startTest implements MouseListener{

	private String questionFile;
	
	public review() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		
		questionPanel.setTextArea("Hello!\nWelcome to the Text Simulator from MENG!\n"+
				"Please click SELECT to choose Your Answer!\n\n\n"+
				"Please Note that you have to choose the file which contains your answer from the past time!!!");	
	}
	
	public void setRowValue(int newRow){
		row = newRow;
		buttonV.setSelected(true);
		try{  
			Question.openExcel(questionFile);  
			questionPanel.setTextArea("QUESTION "+row+":    "+Question.readCellContent(1, row));
			areaF.setTextArea("");
			this.setAnswerFiledFilled(row); 
		}   catch (Exception ex){       
			System.out.println(ex);   
		}  
		
		yours = tempAnswer[row];
		this.setRadioButtonSelected(yours);

		if(!Question.readCellContent(7, row).equals(Character.toString(yours))){
			if(yours == 0){
				questionPanel.setTextColor(Color.BLACK);
			}
			else{
				questionPanel.setTextColor(Color.RED);
			}
		}
		else{
			questionPanel.setTextColor(Color.BLACK);
		}
		
	}
	
	
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
					Question.openExcel(questionFile);  
					questionPanel.setTextArea("QUESTION "+row+" / "+rowNumber+":    "+Question.readCellContent(1, row));
					areaF.setTextArea("");

					this.setAnswerFiledFilled(row);

			       }   catch (Exception ex)   {    
			           System.out.println(ex);    
			       }  
				/*show your Answers on the radio button*/
/*				Answer.readAnswerSheet("Chapter 1.xls");
				yours = Answer.getAnswer(row);
				Answer.closeAnswerSheet();
				*/
				yours = tempAnswer[row];
				this.setRadioButtonSelected(yours);
				
				if(!Question.readCellContent(7, row).equals(Character.toString(yours))){
					if(yours == 0){
						questionPanel.setTextColor(Color.BLACK);
					}
					else{
						questionPanel.setTextColor(Color.RED);
					}
				}
				else{
					questionPanel.setTextColor(Color.BLACK);
				}
			}
		}
		
		
/***********************************************************************************************************************/
		/*Go to the next question*/
		if(e.getSource() == next){
			int i;
			saveChange = false;
			
/*			String label = Character.toString(yourAnswer);
			Answer.continueWritableExcel("Chapter 1.xls", 0, label, row);
*/			
			if(change){
				tempAnswer[row] = yourAnswer;
				change = false;
			}
			
			row+=1;	
			buttonV.setSelected(true);
			try{  
				Question.openExcel(questionFile);  
				questionPanel.setTextArea("QUESTION "+row+" / "+rowNumber+":    "+Question.readCellContent(1, row));
				areaF.setTextArea("");

				this.setAnswerFiledFilled(row);
		       
			}   catch (Exception ex){       
				System.out.println(ex);   
			}  
			
			/*show your Answers on the radio button*/
/*			Answer.readAnswerSheet("Chapter 1.xls");
			yours = Answer.getAnswer(row);
			Answer.closeAnswerSheet();
			*/
			yours = tempAnswer[row];
			this.setRadioButtonSelected(yours);
			
			if(!Question.readCellContent(7, row).equals(Character.toString(yours))){
				if(yours == 0){
					questionPanel.setTextColor(Color.BLACK);
				}
				else{
					questionPanel.setTextColor(Color.RED);
				}
			}
			else{
				questionPanel.setTextColor(Color.BLACK);
			}
			
		}
		
		
/***********************************************************************************************************************/
		
       /*Select the chapters*/
		
		if(e.getSource() == select){
			
			buttonV.setSelected(true);
			int i;
			row = 1;
			
			/*choose the excel file in a file chooser and copy the address into a string*/
			//store the path of chosen file in filename
			fileName = this.getFilePath();
			
			try{
				if(flag){
					Question.closeWorkbook();
				}
//				Answer.openWritableExcel("Chapter 1.xls", date, 0);
				
				Answer.openExcel(fileName);   //get the answer book's path 
				questionFile = Answer.getQuestionFile();   //get the question book's path from answer book
				
				
				Question.openExcel(questionFile);
				rowNumber = Question.getQuestionNumber();
				questionPanel.setTextArea("QUESTION "+row+" / "+rowNumber+":    "+Question.readCellContent(1, row));
				tempAnswer = new char[rowNumber];
				for(i=1;i<rowNumber;i++){
					tempAnswer[i] = Answer.getAnswer(i);
				}
				
				this.setTextFiledBlank();
				
        
				this.setAnswerFiledFilled(row);

				
				yours = tempAnswer[row];
				this.setRadioButtonSelected(yours);
				
				if(!Question.readCellContent(7, row).equals(Character.toString(yours))){
					if(yours == 0){
						questionPanel.setTextColor(Color.BLACK);
					}
					else{
						questionPanel.setTextColor(Color.RED);
					}
				}
				else{
					questionPanel.setTextColor(Color.BLACK);
				}
				
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
			new reviewPanel(tempAnswer, Question, rowNumber, this);
		}
		
/***********************************************************************************************************************/
		/*Go back to the mainFrame*/
		if(e.getSource() == cancel){
			if(saveChange){
				JOptionPane.showConfirmDialog(null, "Do you want to Save your Answer?", "Confirm Dialog", JOptionPane.INFORMATION_MESSAGE);

			}  
			this.dispose();
		}
		
		
/***************************************************************************************************************************/
		if(e.getSource() == save){
			
			
			/*to get the date of today*/
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");  //可以方便地修改日期格式
			String date = dateFormat.format(now); 
	
			//fileName is the path of AnswerBook, and questionFile is the path of the Question
			int error = Answer.reviewWritableExcel(fileName, date, tempAnswer.length, tempAnswer, questionFile);
			if(error == 1){
				JOptionPane.showMessageDialog(null, "Your Answer has been Saved Successfully!!",
						"Message Dialog", JOptionPane.INFORMATION_MESSAGE);
				saveChange = true;
			}
			
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


}
