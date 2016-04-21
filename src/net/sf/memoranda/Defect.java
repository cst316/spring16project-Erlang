package net.sf.memoranda;

import net.sf.memoranda.TimerLog.PspStage;

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
	private PspStage inject;
	private PspStage remove;
	private double fixTime; //in minutes
	private String status;
	private String defectType;
	private String description;
	
	public Defect(){}
	
	public Defect(String projectName,String date, PspStage inject, PspStage remove,
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
	
	public PspStage getInject() {
		return inject;
	}
	
	public void setInject(PspStage inject) {
		this.inject = inject;
	}
	
	public PspStage getRemove() {
		return remove;
	}
	
	public void setRemove(PspStage remove) {
		this.remove = remove;
	}
	
	public double getFixTime() {
		return fixTime;
	}

	public void setFixTime(double fixTime) {
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
