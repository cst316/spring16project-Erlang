package net.sf.memoranda;

/**
 * 
 * @author qbecker
 *@version 1.0
 *This class stores a defect object for use in filling out the defect log
 */

public class Defect {
	private String className;
	private String date;
	private int number;
	private String inject;
	private String remove;
	private int fixTime; //in minutes
	private String status;
	private String defectType;
	private String description;
	
	public Defect(){}
	
	public Defect(String projectName,String date, String inject, String remove,
			int fixTime, String status, String defectType, int number, String description){
			this.date = date;
			this.defectType = defectType;
			this.number = number;
			this.inject = inject;
			this.remove = remove;
			this.className = projectName;
			this.status = status;
			this.fixTime = fixTime;
			this.description = description;
		}


	public String getClassName() {
		
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
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
	
	public int getFixTime() {
		return fixTime;
	}

	public void setFixTime(int fixTime) {
		this.fixTime = fixTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDefectType() {
		return defectType;
	}
	
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
