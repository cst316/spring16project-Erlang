package net.sf.memoranda;

import java.util.Vector;

public class PSPProcess {
	Vector<Estimation> estimation;
	Vector<Defect> defects;
	Planning planning;
	//Image designPanel; // get the design panel, or the export PNG files stored

	public PSPProcess(){
		estimation = new Vector<Estimation>();
		defects = new Vector<Defect>();
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
	/*
	 *
	 ******* No Current Methods for ****
	 * DesignPanel designPanel
	 * 
	 * 
	 */
	
	
	
	
}
