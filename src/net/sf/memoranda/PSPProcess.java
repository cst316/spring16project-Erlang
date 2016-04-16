package net.sf.memoranda;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import javax.swing.JFrame;

import net.sf.memoranda.ui.ExportedImage;
import net.sf.memoranda.ui.SummaryObserver;
import net.sf.memoranda.ui.SummaryPanel;

public class PSPProcess extends Observable{
	Vector<Estimation> estimation;
	Vector<Defect> defects;
	
	SummaryPanel summPanelObs;
	
	double[] timeEstimates;
	Planning planning;
	ExportedImage designImage;
	
	public PSPProcess(){
		estimation = new Vector<Estimation>();
		defects = new Vector<Defect>();
		timeEstimates = new double[7];
		designImage = new ExportedImage();
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
	}
	/**
	 * Remove an Estimation object from the Estimation vector.
	 * @param index to be removed
	 */
	public void removeEstimation(int index){
		estimation.remove(index);
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
	}
	/**
	 * Remove an Defect object from the Defect vector.
	 * @param index to be removed
	 */
	public void removeDefect(int index){
		defects.remove(index);
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
		notifySummaryObserver();
	}
	
	public double[] getTimeEstimations() {
		return timeEstimates;
	}
	
	public void attachSummaryObserver(SummaryPanel observer){
		summPanelObs = observer;		
	}

	public void notifySummaryObserver(){
	    if(summPanelObs != null) {
	    	summPanelObs.updateTimeEstimates();
	    }
	} 	
	
	
	
	
}
