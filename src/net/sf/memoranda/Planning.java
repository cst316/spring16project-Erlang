package net.sf.memoranda;

import net.sf.memoranda.date.CurrentDate;

/**
 * PSP Process class for storing and filling out the planning phase
 * of the PSP
 * 
 * @author sgarci33
 *
 */
public class Planning {
	
	private String programmer;
	private String programTitle;
	private CurrentDate date;
	
	public Planning(String theAuthor, String theTitle, CurrentDate theDate){
		programmer = theAuthor;
		date = theDate;
		programTitle = theTitle;
	}
	
	public CurrentDate getDate(){
		return date;
	}
	
	public String programTitle(){
		return programTitle;
	}
	
	public String getProgrammer(){
		return programmer;
	}
	
	public void setDate(CurrentDate newDate){
		date = newDate;
	}
	
	public void setProgramTitle(String newTitle){
		programTitle = newTitle;
	}
	
	public void setProgrammer(String newAuthor){
		programmer = newAuthor;
	}
}
