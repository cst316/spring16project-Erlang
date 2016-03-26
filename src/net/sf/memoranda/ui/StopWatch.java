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
	
	 private String[] tabs = { "Planning", "Design", "Coding", "estimation"};
	 private JComboBox dropDown = new JComboBox(tabs);
	 
	/**
	 * Create the panel.
	 */
	public StopWatch() {

		
		
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		timeString = new Double(clockTime).toString();
		
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
		add(select);
		
		dropDown.setBounds(25, 150, 100, 25);
		add(dropDown);
		
		click = new JButton("Click to import info");
		add(click);
		click.setBounds(25, 250, 200, 25);
		click.setVisible(true);
		
		
		myTimer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockTick++;
				clockTime = ((double)clockTick)/10.0;
				timeString = new Double(clockTime).toString();
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
				timeString = new Double(clockTime).toString();
				displayTime.setText(timeString);
			}
		});
		
		
	}
	
	
}
