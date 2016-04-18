package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;////////Delete
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.sf.memoranda.PSPProcess;
import net.sf.memoranda.SummaryObject;
import net.sf.memoranda.TimerLog;


/**
 * Creates a Summary tab that is used in the PSP icon
 *
 */

public class SummaryPanel extends JPanel{

	
	
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
            {"Timein Phase (min.)","Estimation", "Actual", "To Date", "To Date %"},
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
	DefaultTableModel tableModel;
	JTable tableSummary;
	
	protected PSPProcess pspProcess;
	

	public SummaryPanel(PSPProcess pspProcess){

        this.setPreferredSize(new Dimension(1000, 1000));
        new JPanel();
        this.pspProcess = pspProcess;
        this.pspProcess.attachSummaryObserver(this);
    	
  		  //Future button to import data
  		JButton b = new JButton("Click to import info");
  			add(b);
  		   b.setSize(50,250);
  		    b.setVisible(true);
  
  	  tableSummary = new JTable();	
  	  initializeTable();
  	  updateTimeEstimates();
  	  updateTimeLogs();
  	  JScrollPane scrollPane = new JScrollPane(tableSummary);
  	  JPanel panel = new JPanel();
  	  panel.add(scrollPane);
  	  add(new JScrollPane(panel));
  	  scrollPane.setSize(500,1500);

  	  setVisible(true);	
	}
	
	private void initializeTable() {
		this.tableModel = new DefaultTableModel(columnSummary, 0);
	  	this.tableSummary.setModel(this.tableModel);
	  	for(Object[] theRow : rowSummary) {
	  		this.tableModel.addRow(theRow);
	  	}
	}
	
	public void updateTimeEstimates() {
		double[] theTimes = pspProcess.getTimeEstimations();
		for(int i = 0; i < theTimes.length; i++){
			 String theTimeString = timeToFormattedString(theTimes[i]);
			 this.tableModel.setValueAt(theTimeString, (13+i), 1);
		}
		String theTotalString = timeToFormattedString(generateTimeTotal(theTimes));
		this.tableModel.setValueAt(theTotalString, 20, 1);
	}
	
	public void updateTimeLogs() {
		Vector<TimerLog> theTimerLogs = pspProcess.getAllTimerLogs();
		double[] theTimes = new double[7];
		for(int i = 0; i < theTimerLogs.size(); i++) {
			TimerLog theTimerLog = theTimerLogs.get(i);
			int theIndex = 0;
			switch(theTimerLog.getcStage())
			{
			case PLANNING:
				theIndex = 0;
				break;
			case DESIGN:
				theIndex = 1;
				break;
			case CODE:
				theIndex = 2;
				break;
			case CODEREVIEW:
				theIndex = 3;
				break;
			case COMPILE:
				theIndex = 4;
				break;
			case TEST:
				theIndex = 5;
				break;
			case POSTMORTEM:
				theIndex = 6;
				break;
			}
			theTimes[theIndex] = theTimes[theIndex] + theTimerLog.getTimeValue();
		}
		for(int i = 0; i < theTimes.length; i++){
			 String theTimeString = timeToFormattedString(theTimes[i]);
			 this.tableModel.setValueAt(theTimeString, (13+i), 2);
		}
		String theTotalString = timeToFormattedString(generateTimeTotal(theTimes));
		this.tableModel.setValueAt(theTotalString, 20, 2);
	}


	public void update() {
		System.out.println("testing, testing, 123");
	}
	
	//convert time to properly formatted string
	private String timeToFormattedString(double aTimeInSec) {
		int theMins = (int)aTimeInSec / 60;
		int theHours = theMins / 60;
					
		String theMinsString = Integer.toString( theMins % 60 );
		String theHoursString = Integer.toString( theHours );
		String theSecondString = String.format ( "%.1f",( aTimeInSec % 60.0 ) );
					
		if( theMinsString.length() < 2 ) {
			theMinsString = "0" + theMinsString;
		}
					
		if( theHoursString.length() < 2 ) {
			theHoursString = "0" + theHoursString;
	    }
			        
		if( theSecondString.length() < 4 ) {
			theSecondString = "0" + theSecondString;
		}
					
		return (theHoursString + ":" + theMinsString + ":" + theSecondString);
	}
	
	private double generateTimeTotal(double[] aTimeArray) {
		double theTotal = 0;
		for(int i = 0; i < aTimeArray.length; i++) {
			theTotal = theTotal + aTimeArray[i];
		}
		return theTotal;
	}

		

}


