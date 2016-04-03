package net.sf.memoranda.ui;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton start, stop, reset, select, click;
	private JLabel displayTime;
	String chosenTab;
	// A variable to save the time that is captured by the 'Stop' button
	String timeSaved;
	
	
	 private String[] tabs = { "Planning", "Design", "Coding", "estimation"};
	 private JComboBox dropDown = new JComboBox(tabs);
	 SavedTime savedTime = new SavedTime();
	 
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
		
		//Select button for selecting tabs 
		select = new JButton("Select");
		select.setBounds(150, 150, 80, 25);
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Select button was being clicked");
				// TODO Auto-generated method stub
			}
			
		});
		add(select);
		
		
		//////////////Captures the the drop down pick 
		dropDown.setBounds(25, 150, 100, 25);
		add(dropDown);
		dropDown.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		         chosenTab = (String) dropDown.getSelectedItem();
		         savedTime.setTabs(chosenTab);
		    }
		});
		////////////////
		
		
		click = new JButton("Click to import info");
		add(click);
		click.setBounds(25, 250, 200, 25);
		click.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click button was being clicked");
				// TODO Auto-generated method stub
				
			}
			
		});
		click.setVisible(true);
		
		
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
				timeSaved = timeString;
				savedTime.setTimewatch(timeSaved);
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
	
	
	
}
