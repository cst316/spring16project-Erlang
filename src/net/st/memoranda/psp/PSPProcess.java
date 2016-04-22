package net.st.memoranda.psp;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import javax.swing.JFrame;

import net.sf.memoranda.ui.ExportedImage;
import net.st.memoranda.psp.ui.SummaryPanel;

public class PSPProcess extends Observable{
	Vector<Estimation> estimation;
	Vector<Defect> defects;
	Vector<TimerLog> timelogs;
	
	SummaryPanel summPanelObs;
	
	double[] timeEstimates;
	Planning planning;
	ExportedImage designImage;
	
	public PSPProcess(){
		estimation = new Vector<Estimation>();
		defects = new Vector<Defect>();
		timelogs = new Vector<TimerLog>();
		timeEstimates = new double[7];
		designImage = new ExportedImage();
//		getExported();
		planning = new Planning();
//		getExported();

		//displayImage("Test");		will display the exported picture when called
	}
	/*
	 * When respected Buttons are pressed on the remote Display Panels
	 * Call methods to fill attributes in this class.
	 */
	/**
	 * Get an Estimation object at specified index.
	 * @param index of Estimation to return
	 * @return Estimation
	 */
	public Estimation getEstimation(int index){
		return estimation.elementAt(index);
	}
	/**
	 * Get all Estimation objects.
	 * @return A Vector of all Estimation objects
	 */
	public Vector<Estimation> getAllEstimations() {
		return estimation;
	}
	/**
	 * Add an Estimation object to the Estimation vector.
	 * @param newEstimation to be added
	 */
	public void addEstimation(Estimation newEstimation){
		estimation.addElement(newEstimation);
		notifySummaryProd();
	}
	/**
	 * Remove an Estimation object from the Estimation vector.
	 * @param index to be removed
	 */
	public void removeEstimation(int index){
		estimation.remove(index);
		notifySummaryProd();
	}
	/**
	 * get size of estimation vector
	 * @param none
	 * @return estimation vector size int
	 */
	public int getEstimationSize(){
		return estimation.size();
	}
	/**
	 * Get an Defect object at specified index.
	 * @param index of Defect to return
	 * @return Defect at 
	 */
	public Defect getDefect(int index){
		return defects.elementAt(index);
	}
	/**
	 * Get all of the Defects.
	 * @return A Vector of all Defects
	 */
	public Vector<Defect> getAllDefects(){
		return defects;
	}
	/**
	 * Add an Defect object to the Defect vector.
	 * @param newDefect to be added
	 */
	public void addDefect(Defect newDefect){	//To be used by CodingPanel
		defects.addElement(newDefect);			//to add a defect
		notifySummaryDefects();
	}
	/**
	 * Remove an Defect object from the Defect vector.
	 * @param index to be removed
	 */
	public void removeDefect(int index){
		defects.remove(index);
		notifySummaryDefects();
	}
	
	/**
	 * get size of defects vector
	 * @param none
	 * @return estimation vector size int
	 */
	public int getDefectsSize(){
		return defects.size();
	}
	
	/**
	 * Get an TimerLog object at specified index.
	 * @param index of TimerLog to return
	 * @return TimerLog
	 */
	public TimerLog getTimerLog(int index){
		return timelogs.elementAt(index);
	}
	
	/**
	 * Get all TimerLog objects.
	 * @return A Vector of all TimerLog objects
	 */
	public Vector<TimerLog> getAllTimerLogs() {
		return timelogs;
	}
	/**
	 * Add a TimerLog object to the timelogs vector.
	 * @param newTimerLog to be added
	 */
	public void addTimerLog(TimerLog newTimerLog){
		timelogs.addElement(newTimerLog);
		notifySummaryAboutTimeLogs();
	}
	/**
	 * Remove a TimerLog object from the timelogs vector.
	 * @param index to be removed
	 */
	public void removeTimerLog(int index){
		timelogs.remove(index);
		notifySummaryAboutTimeLogs();
	}
	/**
	 * get size of timelogs vector
	 * @param none
	 * @return timelogs vector size int
	 */
	public int getTimerLogsSize(){
		return timelogs.size();
	}
	
	/**
	 * get timerlog section totals
	 * @param none
	 * @return array of timelog section totals as doubles
	 */
	public double[] getTimerLogSectTotals() {
		double[] theTimes = new double[7];
		for(int i = 0; i < timelogs.size(); i++) {
			TimerLog theTimerLog = timelogs.get(i);
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
		return theTimes;
	}
	
	public double[] getTimeLogPercentError() {
		double[] thePercentages = new double[7];
		double[] theTimes = getTimerLogSectTotals();
		for(int i = 0; i < theTimes.length; i++) {
			if (theTimes[i] > 0.0 && this.timeEstimates[i] > 0.0) {
				thePercentages[i] = Math.abs(this.timeEstimates[i] - theTimes[i]) / theTimes[i];
			}
			else {
				thePercentages[i] = 0.0;
			}
		}
		return thePercentages;
	}
	
	/**
	 * get to date percentages of timerlogs 
	 * @param none
	 * @return timelog to date percentages as an array of doubles 
	 */
	public double[] getToDatePercentages() {
		double[] thePercentages = new double[7];
		double[] theTimes = getTimerLogSectTotals();
		double theTotal = 0.0;
		for(double theTime : theTimes) {
			theTotal = theTotal + theTime;
		}
		for(int i = 0; i < theTimes.length; i++) {
			if (theTotal > 0.0) {
			    thePercentages[i] = theTimes[i]/theTotal;
			}
			else {
				thePercentages[i] = 0.0;
			}
		}
		return thePercentages;
	}
	
	/**
	 * Get the planning object.
	 * @return the planning to be returned
	 */
	public Planning getPlanning() {
		return planning;
	}
	/**
	 * Set the planning object.
	 * @param planning to be set
	 */
	public void setPlannning(Planning planning) {
		this.planning = planning;
	}
	/**
	 * This method will call the ExportedImage class to pull the buffered image saved
	 */
	public void getExported(){
		designImage.checkExported();
	}
	/*
	 * Source: https://docs.oracle.com/javase/tutorial/2d/images/examples/LoadImageApp.java
	 */
	/**
	 * This method will display the image exported from design panel
	 * @param imageName the image to be displayed
	 */
	public void displayImage(String imageName){
		 JFrame f = new JFrame(imageName);
         
	        f.addWindowListener(new WindowAdapter(){
	                public void windowClosing(WindowEvent e) {
	                    System.exit(0);
	                }
	            });
	        
	        f.add(designImage);
	        f.pack();
	        f.setVisible(true);
	}
	
	
	public void setTimeEstimationValue(int aIndex, double aValue) {
		if (aIndex >= 0 && aIndex < timeEstimates.length) {
			timeEstimates[aIndex] = aValue;
		}
		notifySummaryAboutTimeEstimations();
	}
	
	public double[] getTimeEstimations() {
		return timeEstimates;
	}
	
	public void attachSummaryObserver(SummaryPanel observer){
		summPanelObs = observer;		
	}

	public void notifySummaryAboutTimeEstimations(){
	    if(summPanelObs != null) {
	    	summPanelObs.updateTimeEstimates();
	    	summPanelObs.updatePercentErrors();
	    	summPanelObs.updateToDatePercentages();
	    	notifySummaryProd();
	    }
	} 
	
	public void notifySummaryAboutTimeLogs(){
	    if(summPanelObs != null) {
	    	summPanelObs.updateTimeLogs();
	    	summPanelObs.updatePercentErrors();
	    	summPanelObs.updateToDatePercentages();
	    	notifySummaryProd();
	    }
	} 
	
	public void notifySummaryDefects(){
	    if(summPanelObs != null) {
	    	summPanelObs.updateDefectsTable();
	    	notifySummaryProd();
	    }
	} 
	
	public void notifySummaryProd(){
	    if(summPanelObs != null) {
	    	summPanelObs.updateProdTable();
	    }
	} 
	
	
	
}
