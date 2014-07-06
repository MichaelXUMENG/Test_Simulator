import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class reviewPanel  extends JFrame implements MouseListener{
	
	private Object[] title = {"Question#", "Right / Wrong", "Important"};
	private Object[][] data = {};
	private DefaultTableModel tableModel;
	private JTable reviewTable;
	private startTest question;
	
	public reviewPanel(char[] yourAnswer, excel book, int rowNumber, startTest father, char[] importantQ){
		
		int i;
		this.pack();
		this.setSize(400, 600);
		this.setTitle("Review Your Answers");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/***************************Table Panel***********************************/
		tableModel = new DefaultTableModel(data, title);
		reviewTable = new JTable(tableModel){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		reviewTable.setEnabled(true);
		reviewTable.setPreferredScrollableViewportSize(new Dimension(400,600));
		reviewTable.setFillsViewportHeight(true);
		reviewTable.setShowGrid(true);
		reviewTable.setShowHorizontalLines(true);
		reviewTable.setShowVerticalLines(true);
		reviewTable.setGridColor(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(reviewTable);
		
//		System.out.println(rowNumber);
//		System.out.println(book.getCurrectAnswer(3));
//		System.out.println(yourAnswer[2]);
		
		for(i=1;i< rowNumber;i++){
			if(book.getCurrectAnswer(i).equals(Character.toString(yourAnswer[i]))){
				if(importantQ[i] == 'T'){
					String[] rowValue = {"Question "+i, "RIGHT", "Check"};
					tableModel.addRow(rowValue);
				}
				else{
					String[] rowValue = {"Question "+i, "RIGHT"};
					tableModel.addRow(rowValue);
				}
			}
			else{
				if(yourAnswer[i] == 0){
					if(importantQ[i] == 'T'){
						String[] rowValue = {"Question "+i, " ", "Check"};
						tableModel.addRow(rowValue);
					}	
					else{
						String[] rowValue = {"Question "+i, ""};
						tableModel.addRow(rowValue);
					}
				}
				else{
					if(importantQ[i] == 'T'){
						String[] rowValue = {"Question "+i, "WRONG", "Check"};
						tableModel.addRow(rowValue);
					}
					else{
						String[] rowValue = {"Question "+i, "WRONG"};
						tableModel.addRow(rowValue);
					}
				}
				
			}
		
		}
		reviewTable.addMouseListener(this);
		
/*		DefaultTableCellRenderer trc = new DefaultTableCellRenderer(){
			public Component getTavleCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column){
				Component cell = super.getTableCellRendererComponent(reviewTable, value, isSelected, hasFocus, row, column);
				if(row == 1 && column == 2 && cell.isBackgroundSet())
					cell.setBackground(Color.RED);
				else
					cell.setBackground(Color.GREEN);
				return cell;
			}
		};
		*/
		//This is for change color of the WRONG row
		
		question = father;
		
		this.setLayout(new BorderLayout(15,15));
		this.add(scrollPane,BorderLayout.CENTER);		
		
		
		Dimension screenSize = 
				Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
				
		int x = (screenWidth - this.getWidth()*1/2)/2;
		int y = (screenHeight - this.getHeight()*3/2)/2;
		this.setLocation(x, y);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectRow;
		
		if(e.getSource() == reviewTable){
			selectRow = reviewTable.getSelectedRow()+1;
			question.setRowValue(selectRow);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
