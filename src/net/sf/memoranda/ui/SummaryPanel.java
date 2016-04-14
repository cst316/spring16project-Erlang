package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;////////Delete
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.memoranda.SummaryObject;


/**
 * Creates a Summary tab that is used in the PSP icon
 *
 */
public class SummaryPanel extends JPanel implements Observer{
	
	Object rowSummary[][] = { { " "," ", " ", " "," "},
            {"Summary ", " ", " ", " "," "},
            {"Minute/LOC ", " ", " ", " "," "}, 
            {"LOC/Hour ", " ", " ", " "," "},
            {"Defect/KLOC ", " ", " ", " "," "},
            {"Yield ", " ", " ", " "," "},
            {"A/FR ", " ", " ", " "," "},
            {"Program size (LOC) ", " ", " ", " "," "},
            {"Total New & Change ", " ", " ", " "," "},
            {"Maximum Size ", " ", " ", " "," "},
            {"Minimum Size ", " ", " ", " "," "},
            {" ", " ", " ", " "," "},
            {"Timein Phase (min.)","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"Postmortem ", " ", " ", " "," "},
            {"  Total ", " ", " ", " "," "},
            {"Maximum Size ", " ", " ", " "," "},
            {"Minimum Size ", " ", " ", " "," "},
            {" ", " ", " ", " "," "},
            {"Defect Injected ","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"  Total", " ", " ", " "," "}, 
            {" ", " ", " ", " "," "},
            {"Defect Removed ","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"  Total ", " ", " ", " "," "}};
	Object columnSummary[] = { " ", "Plan", "Actual", "To Date", "To Date %"};
	
	
	public SummaryPanel(){
        this.setPreferredSize(new Dimension(1000, 1000));
        new JPanel();
    	
  		  //Future button to import data
  		JButton b = new JButton("Click to import info");
  			add(b);
  		   b.setSize(50,250);
  		    b.setVisible(true);
  
  	  Tables tableSummary = new Tables(rowSummary, columnSummary);	
  	  JScrollPane scrollPane = new JScrollPane(tableSummary);
  	  JPanel panel = new JPanel();
  	  panel.add(scrollPane);
  	  add(new JScrollPane(panel));
  	  scrollPane.setSize(500,1500);
  	  setVisible(true);	

  	
        
        
	}

	/**
	 * Method creates a table on the summary tab 
	 */

	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	
		
	}
}


