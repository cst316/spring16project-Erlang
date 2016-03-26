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
	private JButton start, stop, reset, select;
	private JLabel displayTime;
	
	 private String[] tabs = { "Planning", "Design", "estimation", "Coding"};
	 private JComboBox dropDown = new JComboBox(tabs);
	 
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
		displayTime.setBounds(113, 55, 100, 15);
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
		
<<<<<<< HEAD
		//Select button for selcting tabs 
		select = new JButton("Select");
		select.setBounds(25, 150, 80, 25);
		add(select);
		
		dropDown.setBounds(25, 170, 80, 25);
		add(dropDown);
		
		
=======
>>>>>>> 7d00de4b8a50f3719ad4acd3bd247806a6dc1ea3
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
			}
		});

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
	
<<<<<<< HEAD
	
=======
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
>>>>>>> 7d00de4b8a50f3719ad4acd3bd247806a6dc1ea3
}
