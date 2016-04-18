package net.sf.memoranda.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.sf.memoranda.PSPProcess;
import net.sf.memoranda.TimerLog;
import net.sf.memoranda.TimerLog.PspStage;
import net.sf.memoranda.util.Local;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.Rectangle;

/**
 * Creates stopwatch for use in all stages of PSP process
 * @author qbecker
 *
 */
//logic for stopwatch gotten from http://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html
public class StopWatch extends JPanel {
	private Timer myTimer;
	private int clockTick;
	private double clockTime;
	private String timeString;
	public static final int ONE_SEC = 1000;
	public static final int TENTH_SEC = 100;

	private JButton start;
	private JButton stop;
	private JButton reset;
	private JButton select;
	private JButton custom;
	private JPanel autoPanel;
	private JPanel manuPanel;
	
	private JLabel titleLabel;
	private JLabel displayTime;
	private JLabel areaLabel;
	private JLabel areaLabelManu;
	private JLabel autoLabel;
	private JLabel manuLabel;
	private JLabel secLabel;
	private JLabel minLabel;
	private JLabel hrsLabel;
	private JLabel entriesLabel;
	private Rectangle autoRectangle;
	private JTextField customTimeSec;
	private JTextField customTimeMin;
	private JTextField customTimeHrs;
	String chosenTab;
	// A variable to save the time that is captured by the 'Stop' button
	String timeSaved;
	private JTable timesTable = new JTable();
	private JScrollPane timesTableScrollPane;
	private DefaultTableModel tableModel;
	private JButton removeButton = new JButton();
	
	
	 private String[] tabs = { " ","PLANNING", "DESIGN", "CODE",
				"CODEREVIEW", "COMPILE", "TEST","POSTMORTEM"};
	 private JComboBox dropDown = new JComboBox(tabs);
	 private JComboBox dropDownManu = new JComboBox(tabs);
	 
	 SavedTime savedTime = new SavedTime();
	 private String[] columnNames = {"Area","Time"};
	 Object[][] data = {};
	 PSPProcess pspProcess;
	 
	/**
	 * Create the panel.
	 */
	public StopWatch(PSPProcess aPSPProcess) {
		pspProcess = aPSPProcess;
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		timeString = timeToFormattedString(clockTime);
		
		setLayout(null);
		
		titleLabel = new JLabel("Self Timer");
		titleLabel.setBounds(25, 10, 80, 25);
		add(titleLabel);
		
		autoLabel = new JLabel("Automatic Entry:");
		autoLabel.setBounds(127, 17, 110, 25);
		add(autoLabel);
		
		manuLabel = new JLabel("Manual Entry:");
		manuLabel.setBounds(147, 162, 90, 25);
		add(manuLabel);
		
		entriesLabel = new JLabel("Entries:");
		entriesLabel.setBounds(179, 307, 65, 25);
		add(entriesLabel);
		
		autoPanel = new JPanel();
		autoPanel.setLayout(null);
		autoPanel.setBounds(20, 40, 220, 120);
		autoPanel.setBackground(Color.LIGHT_GRAY);
		add(autoPanel);
		
		displayTime = new JLabel();
		displayTime.setText(timeString);
		displayTime.setBounds(70, 5, 75, 15);
		autoPanel.add(displayTime);
		
		start = new JButton("Start");
		start.setBounds(5, 30, 70, 25);
		autoPanel.add(start);
		
		stop = new JButton("Stop");
		stop.setBounds(75, 30, 70, 25);
		autoPanel.add(stop);
		
		reset = new JButton("Reset");
		reset.setBounds(145, 30, 70, 25);
		autoPanel.add(reset);
		
		areaLabel = new JLabel("Worked on:");
		areaLabel.setBounds(5, 65, 70, 25);
		autoPanel.add(areaLabel);
		
		dropDown.setBounds(5, 90, 100, 25);
		autoPanel.add(dropDown);
		
		//Select button for selecting tabs 
		select = new JButton("Save");
		select.setBounds(135, 90, 80, 25);
		autoPanel.add(select);
		
		manuPanel = new JPanel();
		manuPanel.setLayout(null);
		manuPanel.setBounds(20, 185, 220, 120);
		manuPanel.setBackground(Color.LIGHT_GRAY);
		add(manuPanel);
		
		areaLabelManu = new JLabel("Worked on:");
		areaLabelManu.setBounds(5, 65, 70, 25);
		manuPanel.add(areaLabelManu);
		
		dropDownManu.setBounds(5, 90, 100, 25);
		manuPanel.add(dropDownManu);
		
		//enter custom time button
		custom = new JButton("Save");
		custom.setBounds(135, 90, 80, 25);
		manuPanel.add(custom);
		
		secLabel = new JLabel("Sec(s):");
		secLabel.setBounds(145, 5, 70, 25);
		manuPanel.add(secLabel);
		
		minLabel = new JLabel("Min(s):");
		minLabel.setBounds(75, 5, 70, 25);
		manuPanel.add(minLabel);
		
		hrsLabel = new JLabel("Hour(s):");
		hrsLabel.setBounds(5, 5, 70, 25);
		manuPanel.add(hrsLabel);
		
		customTimeHrs = new JTextField();
		customTimeHrs.setBounds(5, 30, 70, 25);
		manuPanel.add(customTimeHrs);
		
		customTimeMin = new JTextField();
		customTimeMin.setBounds(75, 30, 70, 25);
		manuPanel.add(customTimeMin);
		
		customTimeSec = new JTextField();
		customTimeSec.setBounds(145, 30, 70, 25);
		manuPanel.add(customTimeSec);
		
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TimerLog theTimerLog = new TimerLog();
				PspStage theSelectedStage;
				try {
		    		theSelectedStage = PspStage.values()[(dropDown.getSelectedIndex()-1)];
		    	} catch (ArrayIndexOutOfBoundsException exc){
		    		JOptionPane.showMessageDialog(null,Local.getString("Please select a \"Worked On:\" area"));
			        return;
		    	}
				theTimerLog.setcStage(theSelectedStage);
				theTimerLog.setTimeValue(clockTime);
				addNewTimerLog(theTimerLog);
				resetTime();
				dropDown.setSelectedIndex(0);
			}
			
		});
		
		//captures custom time and stores it
		custom.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(customButtonClick()) {
		    		clearTime();
			    	dropDownManu.setSelectedIndex(0);
		    	}
		    }
		});
		
		timesTableScrollPane = new JScrollPane(timesTable);
		timesTableScrollPane.setBounds(25, 330, 210, 270);
		add(timesTableScrollPane);
		
		removeButton.setText("Remove Selected Log");
		removeButton.setBounds(65, 600, 165, 25);
		add(removeButton);
		
		removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removeTimerLog(timesTable.getSelectedRow());
            }
        });
		
		populateTable();
		
		myTimer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockTick++;
				clockTime = ((double)clockTick)/10.0;
				timeString = timeToFormattedString(clockTime);
				displayTime.setText(timeString);
			    }
			});
		
		
		//start button action listener
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myTimer.start();
				toggleAutoSaveButton();
			}
		});
		
		
		//stop button action listener
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myTimer.stop();	
				toggleAutoSaveButton();
			}
		});

		System.out.println(timeSaved);
		//reset button action listener
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				resetTime();
				toggleAutoSaveButton();
			}
		});
		
	}
	
	private boolean customButtonClick() {
		double theSecs = 0.0;
		double theMins = 0.0;
		double theHrs = 0.0;
		PspStage theSelectedStage;
		try {
			System.out.println("WHAT'S IN THE BOX: "+customTimeSec.getText());
			if(!customTimeSec.getText().isEmpty()) {
			    theSecs = Double.parseDouble(customTimeSec.getText());
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,Local.getString("Inappropriate value for Sec(s), try again"));
	        return false;
		}
		try {
			if(!customTimeMin.getText().isEmpty()) {
				theMins = Double.parseDouble(customTimeMin.getText());
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,Local.getString("Inappropriate value for Min(s), try again"));
	        return false;
		}
		try {
			if(!customTimeHrs.getText().isEmpty()) {
				theHrs = Double.parseDouble(customTimeHrs.getText());
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,Local.getString("Inappropriate value for Hour(s), try again"));
	        return false;
		}
    	try {
    		theSelectedStage = PspStage.values()[(dropDownManu.getSelectedIndex()-1)];
    	} catch (ArrayIndexOutOfBoundsException e){
    		JOptionPane.showMessageDialog(null,Local.getString("Please select a \"Worked On:\" area"));
	        return false;
    	}
    	TimerLog theTimerLog = new TimerLog();
    	double theTotalSecs = theSecs +  (theMins*60.0) + (theHrs*60.0*60.0);
    	theTimerLog.setTimeValue(theTotalSecs);
    	theTimerLog.setcStage(theSelectedStage);
    	addNewTimerLog(theTimerLog);
    	return true;
	}
	
	private void addNewTimerLog(TimerLog aTimerLog) {
		pspProcess.addTimerLog(aTimerLog);
		String theStage = stageEnumToString(aTimerLog.getcStage());
		String theFormattedTime = timeToFormattedString(aTimerLog.getTimeValue());
		Object[] theRow = { theStage, theFormattedTime };
		tableModel.addRow(theRow);
	}
	
	private void removeTimerLog(int aIndex) {
		if(aIndex < 0 || aIndex >= pspProcess.getTimerLogsSize()) {
			System.out.println("Trying to remove from ouside of the TimeLog table bounds");
		}
		int theRowIndex = timesTable.getSelectedRow();
		pspProcess.removeTimerLog(theRowIndex); 
		tableModel.removeRow(theRowIndex);
	}
	
	private String stageEnumToString(PspStage aPspStage) {
		String theStringValue = "unknown";
		switch (aPspStage) {
		    case PLANNING:
		    	theStringValue = "Planning";
		    	break;
		    case DESIGN:
		    	theStringValue = "Design";
		    	break;
		    case CODE:
		    	theStringValue = "Code";
		    	break;
		    case CODEREVIEW:
		    	theStringValue = "Code Review";
		    	break;
		    case COMPILE:
		    	theStringValue = "Compile";
		    	break;
		    case TEST:
		    	theStringValue = "Test";
		    	break;
		    case POSTMORTEM:
		    	theStringValue = "Postmortem";
		    	break;
		}
		return theStringValue;
	}

	private void resetTime() {
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		timeString = timeToFormattedString(clockTime);
		displayTime.setText(timeString);
	}
	
	private void clearTime() {
		customTimeSec.setText("");
		customTimeMin.setText("");
		customTimeHrs.setText("");
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
	
	private void populateTable() {
		/*
		 * once we have the PSPProcess class we will need to get saved estimations
		 * and populate the table with those values
		 */
		this.tableModel = new DefaultTableModel(this.columnNames, 0);
		this.timesTable.setModel(this.tableModel);
	}
	
	private void toggleAutoSaveButton() {
		select.setEnabled(!myTimer.isRunning());
	}
}
