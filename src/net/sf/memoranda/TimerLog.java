package net.sf.memoranda;

/**
 * TimerLog class hold the time value from the Stopwatch class for use in other parts of the PSP process
 * @author qbecker
 *@version  1.0
 */
public class TimerLog {
	
	PspStage cstage;
	public enum PspStage{
		PLANNING, DESIGN, CODE,
		CODEREVIEW, COMPILE, TEST,
		POSTMORTEM;
	}
	
	
		private double timeValue;
		
		
		public TimerLog(PspStage cstage, double timeValue){
			this.cstage = cstage;
			this.timeValue = timeValue;
		}
		
		
		//accessor and mutators
		public PspStage getcStage() {
			return cstage;
		}


		public void setcStage(PspStage cstage){
			this.cstage = cstage;
		}


		public double getTimeValue() {
			return timeValue;
		}


		public void setTimeValue(double timeValue) {
			this.timeValue = timeValue;
		}


}
