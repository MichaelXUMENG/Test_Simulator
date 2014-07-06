import java.io.File;    
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;    
import jxl.read.biff.BiffException;
import jxl.write.Label;    
import jxl.write.WritableSheet;    
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;



public class excel {
	private Workbook book;
	private WritableWorkbook wBook;
	private Sheet sheet;
	private WritableSheet wSheet;
	private Cell cell;

	public excel(){
		
	}
	
	public void createAnswerExcel(){
		
	}
	
	
/***************************************************************************************************/
	public void openExcel(String fileName){
		
		String result = null;
		cell = null;
		try {
			book = Workbook.getWorkbook(new File(fileName));
			sheet = book.getSheet(0);
//			cell = sheet.getCell(column, row);
//			cell = sheet.getRow(row);
//			result = cell.getContents();
//			book.close();
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String readCellContent(int column, int row){
		String result = null;
		cell = sheet.getCell(column, row);
		result = cell.getContents();
		return result;
	}
	
	public int getQuestionNumber(){
		int num;
		num = sheet.getRows();
		return num;
	}
	
	public String getCurrectAnswer(int row){
		cell = sheet.getCell(7, row);
		String answer= cell.getContents();
		return answer;
	}
	
	public void closeWorkbook(){
		book.close();
	}

	
/***************************************************************************************************/
	public int openWritableExcel(String fileName,String date, int row, char[] data,char[] importantQ, String dataFile){
		int error = 0;
		
		try {
			int i;
			error = 1;
			Label yourCell;
//			String cell = null;
			wBook = Workbook.createWorkbook(new File(fileName+".xls"));
			wSheet = wBook.createSheet(date, 0);
			yourCell = new Label(0,0, dataFile);
			wSheet.addCell(yourCell);
			for(i=0; i<row; i++){
//				cell = Character.toString(data[i]);
				yourCell = new Label(1, i,Character.toString(data[i]));
				wSheet.addCell(yourCell);
				yourCell = new Label(2, i,Character.toString(importantQ[i]));
				wSheet.addCell(yourCell);
			}
			wBook.write();
			wBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = 2;
		}catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = 3;
		}
		
		return error;
		
	}
	

	public int reviewWritableExcel(String fileName,String date, int row, char[] data,char[] importantQ, String dataFile){
		int error = 0;

		try {
			int i;
			error = 1;
			Label yourCell;
//			String cell = null;
			wBook = Workbook.createWorkbook(new File(fileName));
			wSheet = wBook.createSheet(date, 0);
			yourCell = new Label(0,0, dataFile);
			wSheet.addCell(yourCell);
			for(i=0; i<row; i++){
//				cell = Character.toString(data[i]);
				yourCell = new Label(1, i,Character.toString(data[i]));
				wSheet.addCell(yourCell);
				yourCell = new Label(2, i,Character.toString(importantQ[i]));
				wSheet.addCell(yourCell);
			}
			wBook.write();
			wBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = 2;
		}catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = 3;
		}
		
		return error;
		
	}
	
	
	public String getQuestionFile(){
		String result = sheet.getCell(0,0).getContents();
		return result;
		
	}

	public char getAnswer(int row){
		
		cell = sheet.getCell(1, row);
		String answer= cell.getContents();
		char result = answer.charAt(0);
		return result;
		
	}
	
	public char getImportant(int row){
		cell = sheet.getCell(2, row);
		String answer= cell.getContents();
		char result = answer.charAt(0);
		return result;
	}
	

	
/***************************************************************************************************/
	
	
	
}
