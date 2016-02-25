package net.sf.memoranda.ui;


/**
 * Tables class extends JTable and create a table that is used in PSPPanel
 */
import javax.swing.JTable;

public class Tables extends JTable{
	
	
	
	public Tables(Object rowData[][], Object columnNames[] ){
		super(rowData, columnNames);		
	}
}