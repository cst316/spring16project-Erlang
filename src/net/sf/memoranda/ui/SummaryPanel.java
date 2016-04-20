package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;////////Delete
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.sf.memoranda.PSPProcess;
import net.sf.memoranda.SummaryObject;
import net.sf.memoranda.TimeConverter;
import net.sf.memoranda.TimerLog;


/**
 * Creates a Summary tab that is used in the PSP icon
 *
 */

public class SummaryPanel extends JPanel{

	Object timeTableData[][] = { 
            {"Planning", " ", " ", " "," "},
            {"Design", " ", " ", " "," "},
            {"Code", " ", " ", " "," "},
            {"Code Review", " ", " ", " "," "},
            {"Compile", " ", " ", " "," "},
            {"Test", " ", " ", " "," "},
            {"Postmortem", " ", " ", " "," "},
            {"Total", " ", " ", " "," "}};
	Object defectTableData[][] = {
            {"Planning", " ", " "},
            {"Design", " ", " "},
            {"Code", " ", " "},
            {"Code Review", " ", " "},
            {"Compile", " ", " "},
            {"Test", " ", " "},
            {"Postmortem", " ", " "},
            {"Total", " ", " "} };
	Object prodTableData[][] = {
            {"Lines of Code", " ", " ", " "," "}, 
            {"LOC/Hour", " ", " ", " "," "},
            {"Defect/KLOC", " ", " ", " "," "} };
	
	Object timeColumns[] = { " ", "Estimated", "Actual", "% Error", "To Date %"};
	Object defectColumns[] = { " ", "Count", "To Date %"};
	Object prodColumns[] = { " ", "Estimated", "Actual"};
	DefaultTableModel timeTableModel;
	DefaultTableModel defectTableModel;
	DefaultTableModel prodTableModel;
	JTable timeTable;
	JTable defectTable;
	JTable prodTable;
	
	JLabel timeLabel;
	JLabel defectLabel;
	JLabel prodLabel;
	JLabel locLabel;
	JTextField locTextField;
	
	protected PSPProcess pspProcess;
	
	public SummaryPanel(PSPProcess pspProcess){

      this.setPreferredSize(new Dimension(1000, 1000));
      this.setLayout(null);
      this.pspProcess = pspProcess;
      this.pspProcess.attachSummaryObserver(this);
  
  	  timeTable = new JTable();	
  	  initializeTimeTable();
  	  updateTimeEstimates();
  	  updateTimeLogs();
  	  updatePercentErrors();
  	  updateToDatePercentages();
  	  JScrollPane timeScrollPane = new JScrollPane(timeTable);
  	  add(timeScrollPane);
  	  timeScrollPane.setBounds(250,50,500, 160);
  	  
  	  defectTable = new JTable();	
	  JScrollPane defectScrollPane = new JScrollPane(defectTable);
	  initializeDefectTable();
	  add(defectScrollPane);
	  defectScrollPane.setBounds(250, 250, 300, 160);
	  
	  prodTable = new JTable();	
	  JScrollPane prodScrollPane = new JScrollPane(prodTable);
	  initializeProdTable();
	  add(prodScrollPane);
	  prodScrollPane.setBounds(250, 450, 300, 80);
	  
	  timeLabel = new JLabel("Time in Phase (HH:MM:SS)");
	  timeLabel.setBounds(250, 30, 180, 20);
	  add(timeLabel);
	  defectLabel = new JLabel("Defects Injected");
	  defectLabel.setBounds(250, 230, 180, 20);
	  add(defectLabel);
	  prodLabel = new JLabel("Productivity");
	  prodLabel.setBounds(250, 430, 180, 20);
	  add(prodLabel);
	  
	  locLabel = new JLabel("<html>Enter Final Total<br>Lines of Code:</html>",
			                SwingConstants.LEFT);
	  locLabel.setBounds(560, 450, 120, 40);
	  add(locLabel);
	  
	  locTextField = new JTextField();
	  locTextField.setBounds(560, 490, 100, 25);
	  add(locTextField);
	  
  	  setVisible(true);	
	}
	
	private void initializeTimeTable() {
		this.timeTableModel = new DefaultTableModel(timeColumns, 0);
	  	this.timeTable.setModel(this.timeTableModel);
	  	for(Object[] theRow : timeTableData) {
	  		this.timeTableModel.addRow(theRow);
	  	}
	}
	
	private void initializeDefectTable() {
		this.defectTableModel = new DefaultTableModel(defectColumns, 0);
	  	this.defectTable.setModel(this.defectTableModel);
	  	for(Object[] theRow : defectTableData) {
	  		this.defectTableModel.addRow(theRow);
	  	}
	}
	
	private void initializeProdTable() {
		this.prodTableModel = new DefaultTableModel(prodColumns, 0);
	  	this.prodTable.setModel(this.prodTableModel);
	  	for(Object[] theRow : prodTableData) {
	  		this.prodTableModel.addRow(theRow);
	  	}
	}
	
	public void updateTimeEstimates() {
		double[] theTimes = pspProcess.getTimeEstimations();
		for(int i = 0; i < theTimes.length; i++){
			 String theTimeString = TimeConverter.secondsToFormattedString(theTimes[i]);
			 this.timeTableModel.setValueAt(theTimeString, i, 1);
		}
		String theTotalString = TimeConverter.secondsToFormattedString(generateTimeTotal(theTimes));
		this.timeTableModel.setValueAt(theTotalString, 7, 1);
	}
	
	public void updateTimeLogs() {
		Vector<TimerLog> theTimerLogs = pspProcess.getAllTimerLogs();
		double[] theTimes = pspProcess.getTimerLogSectTotals();
		for(int i = 0; i < theTimes.length; i++){
			 String theTimeString = TimeConverter.secondsToFormattedString(theTimes[i]);
			 this.timeTableModel.setValueAt(theTimeString, i, 2);
		}
		String theTotalString = TimeConverter.secondsToFormattedString(generateTimeTotal(theTimes));
		this.timeTableModel.setValueAt(theTotalString, 7, 2);
	}
	
	public void updateToDatePercentages() {
		double[] thePercentages = pspProcess.getToDatePercentages();
		for(int i = 0; i < thePercentages.length; i++){
			 String theTimeString = String.format ( "%.1f",( thePercentages[i] * 100.0 ) );
			 this.timeTableModel.setValueAt(theTimeString, i, 4);
		}
		this.timeTableModel.setValueAt("-----", 7, 3);
	}
	
	public void updatePercentErrors() {
		double[] thePercentages = pspProcess.getTimeLogPercentError();
		for(int i = 0; i < thePercentages.length; i++){
			 String theTimeString = String.format ( "%.1f",( thePercentages[i] * 100.0 ) );
			 this.timeTableModel.setValueAt(theTimeString, i, 3);
		}
		this.timeTableModel.setValueAt("-----", 7, 4);
	}

	public void update() {
		System.out.println("testing, testing, 123");
	}
		
	private double generateTimeTotal(double[] aTimeArray) {
		double theTotal = 0;
		for(int i = 0; i < aTimeArray.length; i++) {
			theTotal = theTotal + aTimeArray[i];
		}
		return theTotal;
	}

		

}


