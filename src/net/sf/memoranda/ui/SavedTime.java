package net.sf.memoranda.ui;

public class SavedTime {
	
	String timewatch; 
	String tabs; 
	
	public SavedTime(){
		
	}	
	
	public SavedTime(String timeString, String chosenTab){
		timewatch = timeString;
		tabs = chosenTab;
	}	
	
	public String getTimewatch() {
		return timewatch;
	}

	public void setTimewatch(String timewatch) {
		this.timewatch = timewatch;
	}

	public String getTabs() {
		return tabs;
	}

	public void setTabs(String tabs) {
		this.tabs = tabs;
	}

	@Override
	public String toString() {
		return "SavedTime [timewatch=" + timewatch + ", tabs=" + tabs + "]";
	}
	

}