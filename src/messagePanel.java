import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class messagePanel extends JPanel{
	/**The message to be displayed*/
	private String message="";

	
	
	/**The x coordinate where the message is displayed*/
	private int xCoordinate = 0;
	
	/**The y coordinate where the message is displayed*/
	private int yCoordinate = 0;
	
	/**Indicate whether the message is displayed in the center*/
	private boolean centered;
	
	/**The interval for moving the message horizontally and vertically*/
	private int interval = 10;
	
	/**Construct with default properties*/
	public messagePanel(){
	}
	
	/**Construct a message panel with a specifies message*/
	public messagePanel(String message){
		this.message = message;
	}
	
	public messagePanel(String message, int x, int y){
		this.message = message;
		this.setSize(x, y/3);
		xCoordinate = x/2;
		yCoordinate = y/2;
	}
	
	
	
	/**Return message*/
	public String getMessage(){
		return message;
	}
	
	public void refresh(){
		message = "";
			repaint();
		}
	
	public void setMessage(String message){
		this.message = message;
//		this.t = t;
//		repaint();
	}
	

	
	/**Paint the message*/
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
//		if(centered){
			//Get font metrics for the current font
			FontMetrics fm = g.getFontMetrics();
			
			//Find the center location to display
			int stringWidth = fm.stringWidth(message);
			int stringAscent = fm.getAscent();
			//Get the position of the leftmost character in the baseline
//			xCoordinate = this.getWidth()/2 - stringWidth/2;
//			yCoordinate = this.getHeight()/2 - stringAscent/2;
//		}
		
		g.drawString(message, xCoordinate, yCoordinate);
		if(message!=null){
			g.drawString(message,xCoordinate, yCoordinate);
		}
/*		for(int j=0;j<16;j++){
				g.drawString("Hello World", xCoordinate+70, yCoordinate+25*j);
		}  */
	}



}
