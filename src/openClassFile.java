/************************************************************************************************************
	 * This is to open a exist type of file which in this project is ".xls"
	 * This is the function of file Filter;
	 * 
	 * ***********************************************************/
	public class openClassFile extends javax.swing.filechooser.FileFilter{
	    public boolean accept(java.io.File f) {
	      if (f.isDirectory())return true;
	      return f.getName().endsWith(".xls");  //设置为选择以.class为后缀的文件
	    } 
	    public String getDescription(){
	      return ".xls";
	    }
	}