/**
 * 
 */
package net.sf.memoranda;

/**
 * @author ty124
 *
 */
public class ReportSettings {
	private boolean withProjectID;
	private boolean withProjectDates;
	private boolean withProjectStatus;
	private boolean withProjectDescription;
	private boolean withTasks;
	private boolean withTaskIDs;
	private boolean withTaskText;
	private boolean withTaskProgress;
	private boolean withTaskStatus;
	private boolean withTaskDates;
	private boolean withTaskSubTasks;
	private boolean withTaskPriority;
	private boolean withResources;
	private boolean withNotes;
	
	public ReportSettings() {
		this.withProjectID = false;
		this.withProjectDates = false;
		this.withProjectStatus = false;
		this.withProjectDescription = false;
		this.withTasks = false;
		this.withTaskIDs = false;
		this.withTaskText = false;
		this.withTaskProgress = false;
		this.withTaskStatus = false;
		this.withTaskDates = false;
		this.withTaskSubTasks = false;
		this.withTaskPriority = false;
		this.withResources = false;
		this.withNotes = false;
	}
	
	public boolean isWithProjectID() {
		return withProjectID;
	}
	public void setWithProjectID(boolean withProjectID) {
		this.withProjectID = withProjectID;
	}
	public boolean isWithProjectDates() {
		return withProjectDates;
	}
	public void setWithProjectDates(boolean withProjectDates) {
		this.withProjectDates = withProjectDates;
	}
	public boolean isWithProjectStatus() {
		return withProjectStatus;
	}
	public void setWithProjectStatus(boolean withProjectStatus) {
		this.withProjectStatus = withProjectStatus;
	}
	public boolean isWithProjectDescription() {
		return withProjectDescription;
	}
	public void setWithProjectDescription(boolean withProjectDescription) {
		this.withProjectDescription = withProjectDescription;
	}
	public boolean isWithTasks() {
		return withTasks;
	}
	public void setWithTasks(boolean withTasks) {
		this.withTasks = withTasks;
	}
	public boolean isWithTaskIDs() {
		return withTaskIDs;
	}
	public void setWithTaskIDs(boolean withTaskIDs) {
		this.withTaskIDs = withTaskIDs;
	}
	public boolean isWithTaskText() {
		return withTaskText;
	}
	public void setWithTaskText(boolean withTaskText) {
		this.withTaskText = withTaskText;
	}
	public boolean isWithTaskProgress() {
		return withTaskProgress;
	}
	public void setWithTaskProgress(boolean withTaskProgress) {
		this.withTaskProgress = withTaskProgress;
	}
	public boolean isWithTaskStatus() {
		return withTaskStatus;
	}
	public void setWithTaskStatus(boolean withTaskStatus) {
		this.withTaskStatus = withTaskStatus;
	}
	public boolean isWithTaskDates() {
		return withTaskDates;
	}
	public void setWithTaskDates(boolean withTaskDates) {
		this.withTaskDates = withTaskDates;
	}
	public boolean isWithTaskSubTasks() {
		return withTaskSubTasks;
	}
	public void setWithTaskSubTasks(boolean withTaskSubTasks) {
		this.withTaskSubTasks = withTaskSubTasks;
	}
	public boolean isWithTaskPriority() {
		return withTaskPriority;
	}
	public void setWithTaskPriority(boolean withTaskPriority) {
		this.withTaskPriority = withTaskPriority;
	}
	public boolean isWithResources() {
		return withResources;
	}
	public void setWithResources(boolean withResources) {
		this.withResources = withResources;
	}
	public boolean isWithNotes() {
		return withNotes;
	}
	public void setWithNotes(boolean withNotes) {
		this.withNotes = withNotes;
	}
	
	

}
