package net.sf.memoranda;

public class TimeConverter {
	public static String secondsToFormattedString(double aTimeInSec) {
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
}
