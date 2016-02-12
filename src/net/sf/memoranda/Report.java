/**
 * 
 */
package net.sf.memoranda;

/**
 * @author ty124
 *
 */
public class Report {
	private String projectString;
	private String tasksString;
	private String resourcesString;
	private String notesString;
	
	public Report(){
		this.projectString = null;
		this.tasksString = null;
		this.resourcesString = null;
		this.notesString = null;
	}
	
	public Report(String projectString, String tasksString, String resourcesString, String notesString) {
		this.projectString = projectString;
		this.tasksString = tasksString;
		this.resourcesString = resourcesString;
		this.notesString = notesString;
	}
	
	public String getProjectString() {
		return projectString;
	}
	public void setProjectString(String projectString) {
		this.projectString = projectString;
	}
	public String getTasksString() {
		return tasksString;
	}
	public void setTasksString(String tasksString) {
		this.tasksString = tasksString;
	}
	public String getResourcesString() {
		return resourcesString;
	}
	public void setResourcesString(String resourcesString) {
		this.resourcesString = resourcesString;
	}
	public String getNotesString() {
		return notesString;
	}
	public void setNotesString(String notesString) {
		this.notesString = notesString;
	}
	
	
}
