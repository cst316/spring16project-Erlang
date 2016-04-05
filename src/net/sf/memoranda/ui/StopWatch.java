package net.sf.memoranda.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.sf.memoranda.TimerLog;
import net.sf.memoranda.TimerLog.PspStage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
	
	private JLabel displayTime;
	private JTextField customTime;
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
	TimerLog timerLog;
	Vector<TimerLog> TimelogArray = new Vector<TimerLog>(10);
	 
	 SavedTime savedTime = new SavedTime();
	 private String[] columnNames = {"Area","Time(mins)"};
	 
	/**
	 * Create the panel.
	 */
	public StopWatch() {
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		updateTimeString();
		
		setLayout(null);
		displayTime = new JLabel("New label");
		displayTime.setText(timeString);
		displayTime.setBounds(113, 55, 70, 15);
		add(displayTime);
		
		start = new JButton("Start");
		start.setBounds(25, 12, 80, 25);
		add(start);
		
		stop = new JButton("Stop");
		stop.setBounds(25, 50, 80, 25);
		add(stop);
		
		reset = new JButton("Reset");
		reset.setBounds(25, 87, 80, 25);
		add(reset);
		
		
		//enter custom time button
		custom = new JButton("Custom");
		custom.setBounds(150, 195, 100, 25);
		add(custom);
		
		//enter custom time combo box
		customTime = new JTextField();
		customTime.setBounds(25, 195, 100, 25);
		add(customTime);
		
		//Select button for selecting tabs 
		select = new JButton("Save");
		select.setBounds(150, 150, 80, 25);
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TimelogArray.add(timerLog);
				
				System.out.println("Select button was being clicked");
				// TODO Auto-generated method stub
			}
			
		});
		add(select);
		
		//captures custom time and stores it
		custom.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	customButtonClick();
		    	
		    }
		});
		
		
		
		//////////////Captures the the drop down pick 
		dropDown.setBounds(25, 150, 100, 25);
		add(dropDown);
		dropDown.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	PspStage chosenTab = PspStage.values()[(dropDown.getSelectedIndex()-1)];
		         timerLog = new TimerLog();
		         timerLog.setcStage(chosenTab);
		         
		       //  savedTime.setTabs(chosenTab);
		    }
		});
		////////////////
		
		timesTableScrollPane = new JScrollPane(timesTable);
		timesTableScrollPane.setBounds(25, 200, 205, 400);
		add(timesTableScrollPane);
		
		removeButton.setText("Remove Selected Log");
		removeButton.setBounds(65, 600, 165, 25);
		add(removeButton);
		
		removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removeButtonClicked();
            }
        });
		
		populateTable();
		
		myTimer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockTick++;
				clockTime = ((double)clockTick)/10.0;
				updateTimeString();
				displayTime.setText(timeString);
			    }
			});
		
		
		//start button action listener
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myTimer.start();
			}
		});
		
		
		//stop button action listener
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myTimer.stop();
				double timeSaved = Double.parseDouble(timeString);
				timerLog.setTimeValue(timeSaved);
				
				//	savedTime.setTimewatch(timeSaved);
				
			}
		});

		System.out.println(timeSaved);
		//reset button action listener
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				clockTick = 0;
				clockTime = ((double)clockTick)/10.0;
				updateTimeString();
				displayTime.setText(timeString);
			}
		});
		
		
		
	}
	
	private void customButtonClick() {
		if(customTime.getText() != null){
		String tempCustomTime = customTime.getText();
    	System.out.println("Custom Time Button clicked with value " + tempCustomTime);
    	TimerLog time = new TimerLog(PspStage.values()[(dropDown.getSelectedIndex()-1)], Double.parseDouble(customTime.getText()));
         
		}
		
	}

	//convert time to properly formatted string
	private void updateTimeString(){
		int theMins = (int)clockTime / 60;
		int theHours = theMins / 60;
			
		String theMinsString = Integer.toString( theMins % 60 );
		String theHoursString = Integer.toString( theHours );
		String theSecondString = String.format ( "%.1f",( clockTime % 60.0 ) );
			
		if( theMinsString.length() < 2 ) {
			theMinsString = "0" + theMinsString;
		}
			
	    if( theHoursString.length() < 2 ) {
	        theHoursString = "0" + theHoursString;
	    }
	        
	    if( theSecondString.length() < 4 ) {
	        theSecondString = "0" + theSecondString;
	    }
			
		timeString = theHoursString + ":" + theMinsString + ":" + theSecondString;
	}
	
	private void populateTable() {
		/*
		 * once we have the PSPProcess class we will need to get saved estimations
		 * and populate the table with those values
		 */
		this.tableModel = new DefaultTableModel(this.columnNames, 0);
		this.timesTable.setModel(this.tableModel);
	}
	

	private void removeButtonClicked() {
		int theRowIndex = timesTable.getSelectedRow();
		if ( theRowIndex >= 0 ) {
//			estimations.remove(theRowIndex); need to change for time implementation
		    tableModel.removeRow(theRowIndex);
		}
	}
}
