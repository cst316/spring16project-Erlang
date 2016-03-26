package net.sf.memoranda;

/**
 * 
 * @author qbecker
 *@version 1.0
 *This class stores a defect object for use in filling out the defect log
 */

public class Defect {
	private String projectName;
	private String date;
	private int number;
	private String inject;
	private String remove;
	private String fixTime;
	private String fexRef;
	private String defectType;
	
	public Defect(){}
	
	public Defect(String projectName,String date, String inject, String remove,
			String fixTime, String fexRef, String defectType, int number){
			this.date = date;
			this.defectType = defectType;
			this.number = number;
			this.inject = inject;
			this.remove = remove;
			this.projectName = projectName;
			this.fexRef = fexRef;
			this.fixTime = fixTime;
		}


	public String getProjectName() {
		
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
		
	}
	
	public String getInject() {
		return inject;
	}
	
	public void setInject(String inject) {
		this.inject = inject;
	}
	
	public String getRemove() {
		return remove;
	}
	
	public void setRemove(String remove) {
		this.remove = remove;
	}
	
	public String getFixTime() {
		return fixTime;
	}
	
	public void setFixTime(String fixTime) {
		this.fixTime = fixTime;
	}
	
	public String getFexRef() {
		return fexRef;
	}
	
	public void setFexRef(String fexRef) {
		this.fexRef = fexRef;
	}
	
	public String getDefectType() {
		return defectType;
	}
	
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}


}
