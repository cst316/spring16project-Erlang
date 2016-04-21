package net.sf.memoranda;

public class SummaryCalculator {
	
	/**
	 * Get total time in from input array, should be in same time unit.
	 * 
	 * @param  array of doubles to be summed, time measurements
	 * @return double of total time in seconds from array
	 */
	public static double timeTotal(double[] aTimeArray) {
		double theTotal = 0;
		for(int i = 0; i < aTimeArray.length; i++) {
			theTotal = theTotal + aTimeArray[i];
		}
		return theTotal;
	}
	
	/**
	 * Generate LOC per hour value.
	 * 
	 * @param  long of the total lines of code 
	 * @param  double of the total time in seconds
	 * @return double of the LOC per hour value
	 */
	public static double lOCPerHour(long aLOC, double aTimeInSec) {
		double theValue = 0.0;
		if(aTimeInSec > 0) {
			double theTimeInHrs = aTimeInSec/(60.0*60.0);
			theValue = aLOC/theTimeInHrs;
		}
		return theValue;
	}
	
	/**
	 * Generate Defects per KLOC value.	 
	 *  
	 * @param  long of the total defect count
	 * @param  long of the total lines of code 
	 * @return double of the Defects per KLOC value
	 */
	public static double defectsPerKLOC(long aDefectCount, long aLOC) {
		double theValue = 0.0;
		if(aLOC > 0){
			theValue = (double)aDefectCount/((double)aLOC/1000.0);
		}
		return theValue;
	}
}
