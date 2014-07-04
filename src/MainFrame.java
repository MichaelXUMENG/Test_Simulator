
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainFrame extends JFrame implements ActionListener{

	//create four functional buttons
		private JButton start = new JButton("Start");
		private JButton review = new JButton("Review");
		private JButton showGrade = new JButton("Show Grades");
		private JButton showAttempts = new JButton("Show Attemps");
		private JButton quit = new JButton("QUIT");		
		
		
		public MainFrame(){
			//prepare the Main Frame 
			this.setSize(300, 500);
			this.setTitle("Test Simulator System");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//add buttons in the panel
			JPanel LButton = new JPanel();
			LButton.setLayout(new GridLayout(5,1));
			LButton.add(start);
			LButton.add(review);
			LButton.add(showGrade);
			LButton.add(showAttempts);
			LButton.add(quit);
			
			//add panel into the frame
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(LButton, BorderLayout.CENTER);
			
			//add tool tips text for the buttons
			start.setToolTipText("You can add a new course, Or modify an existed course, Or delete a corse");
			review.setToolTipText("You can add a new student for a particular class, Or delete a student for whole classes or one particular class");
			showGrade.setToolTipText("Record attendance on a specific day for a particular course");
			showAttempts.setToolTipText("You can choose to show detail record of a course or a line graph");
			quit.setToolTipText("Quit the System");
			
			//register the listener to the buttons
			start.addActionListener(this);
			review.addActionListener(this);
			showGrade.addActionListener(this);
			showAttempts.addActionListener(this);
			quit.addActionListener(this);
			
			//set location for the frame
			Dimension screenSize = 
					Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			
			int x = (screenWidth - this.getWidth()*4)/2;
			int y = (screenHeight - this.getHeight()*3/2)/2;
			this.setLocation(x, y);
			
			//set visible
			this.setVisible(true);
			
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new MainFrame();
			
			//connect to library database
			
		}

		@Override
		public void actionPerformed(ActionEvent e){
			// TODO Auto-generated method stub
			
			//action of button search
			if(e.getSource()==start){
				try {
					new startTest();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
			}
			
			//action of button checkOut
			if(e.getSource()==review){
				try {
					new review();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    	
			}
			
			//action of button checkIn
			if(e.getSource()==showGrade){
/*				try {
					new record();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    */
				
			}
			
			//action of button Borrower
			if(e.getSource()==showAttempts){
/*				try {
					new show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    */
				
			}
			
			//action of button quit
			if(e.getSource()==quit){
				System.exit(0);
			}
			
		}

}
