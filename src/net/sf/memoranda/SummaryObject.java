package net.sf.memoranda;

/**
 * SummaryObject class stores information taken from the summary table in postmortem
 * @author qbecker
 *
 */

public class SummaryObject {
	
	private String[] summarySummary = new String[4];
	private String[] summaryMinLoc = new String[4];
	private String[] summaryKloc = new String[4];
	private String[] summaryYield = new String[4];
	private String[] summaryArf = new String[4];
	private String[] summaryProgramSize = new String[4];
	private String[]summaryTotalNewChange = new String[4];
	private String[] summaryMaximumSize = new String[4];
	private String[] summaryMinimumSize = new String[4];
	private String[] timeinPlanning = new String[4];
	private String[] timeinDesign = new String[4];
	private String[] timeinCode = new String[4];
	private String[] timeinCodeReview = new String[4];
	private String[] timeinCompile = new String[4];
	private String[] timeinTest = new String[4];
	private String[] timeinPostmortem = new String[4];
	private String[] timeinTotal = new String[4];
	private String[] timeinMaximumSize = new String[4];
	private String[] timeinMinimumsize = new String[4];
	private String[] defectInjectedPlanning = new String[4];
	private String[] defectInjectedDesign = new String[4];
	private String[] defectInjectedCode = new String[4];
	private String[] defectInjectedCodeReview = new String[4];
	private String[] defectInjectedCompile = new String[4];
	private String[] defectInjectedTest = new String[4];
	private String[] defectInjectedTotal = new String[4];
	private String[] defectRemovedPlanning = new String[4];
	private String[] defectRemovedDesign = new String[4];
	private String[] defectRemovedCode = new String[4];
	private String[] defectRemovedCodeReview = new String[4];
	private String[] defectRemovedCompile = new String[4];
	private String[] defectRemovedTest = new String[4];
	private String[] defectRemovedTotal = new String[4];
	
	
	
	public SummaryObject(){}
	
	

	public String[] getSummarySummary() {
		return summarySummary;
	}

	public void setSummarySummary(String[] summarySummary) {
		this.summarySummary = summarySummary;
	}

	public String[] getSummaryMinLoc() {
		return summaryMinLoc;
	}

	public void setSummaryMinLoc(String[] summaryMinLoc) {
		this.summaryMinLoc = summaryMinLoc;
	}

	public String[] getSummaryKloc() {
		return summaryKloc;
	}

	public void setSummaryKloc(String[] summaryKloc) {
		this.summaryKloc = summaryKloc;
	}

	public String[] getSummaryYield() {
		return summaryYield;
	}

	public void setSummaryYield(String[] summaryYield) {
		this.summaryYield = summaryYield;
	}

	public String[] getSummaryArf() {
		return summaryArf;
	}

	public void setSummaryArf(String[] summaryArf) {
		this.summaryArf = summaryArf;
	}

	public String[] getSummaryProgramSize() {
		return summaryProgramSize;
	}

	public void setSummaryProgramSize(String[] summaryProgramSize) {
		this.summaryProgramSize = summaryProgramSize;
	}

	public String[] getSummaryTotalNewChange() {
		return summaryTotalNewChange;
	}

	public void setSummaryTotalNewChange(String[] summaryTotalNewChange) {
		this.summaryTotalNewChange = summaryTotalNewChange;
	}

	public String[] getSummaryMaximumSize() {
		return summaryMaximumSize;
	}

	public void setSummaryMaximumSize(String[] summaryMaximumSize) {
		this.summaryMaximumSize = summaryMaximumSize;
	}

	public String[] getSummaryMinimumSize() {
		return summaryMinimumSize;
	}

	public void setSummaryMinimumSize(String[] summaryMinimumSize) {
		this.summaryMinimumSize = summaryMinimumSize;
	}

	public String[] getTimeinPlanning() {
		return timeinPlanning;
	}

	public void setTimeinPlanning(String[] timeinPlanning) {
		this.timeinPlanning = timeinPlanning;
	}

	public String[] getTimeinDesign() {
		return timeinDesign;
	}

	public void setTimeinDesign(String[] timeinDesign) {
		this.timeinDesign = timeinDesign;
	}

	public String[] getTimeinCode() {
		return timeinCode;
	}

	public void setTimeinCode(String[] timeinCode) {
		this.timeinCode = timeinCode;
	}

	public String[] getTimeinCodeReview() {
		return timeinCodeReview;
	}

	public void setTimeinCodeReview(String[] timeinCodeReview) {
		this.timeinCodeReview = timeinCodeReview;
	}

	public String[] getTimeinCompile() {
		return timeinCompile;
	}

	public void setTimeinCompile(String[] timeinCompile) {
		this.timeinCompile = timeinCompile;
	}

	public String[] getTimeinTest() {
		return timeinTest;
	}

	public void setTimeinTest(String[] timeinTest) {
		this.timeinTest = timeinTest;
	}

	public String[] getTimeinPostmortem() {
		return timeinPostmortem;
	}

	public void setTimeinPostmortem(String[] timeinPostmortem) {
		this.timeinPostmortem = timeinPostmortem;
	}

	public String[] getTimeinTotal() {
		return timeinTotal;
	}

	public void setTimeinTotal(String[] timeinTotal) {
		this.timeinTotal = timeinTotal;
	}

	public String[] getTimeinMaximumSize() {
		return timeinMaximumSize;
	}

	public void setTimeinMaximumSize(String[] timeinMaximumSize) {
		this.timeinMaximumSize = timeinMaximumSize;
	}

	public String[] getTimeinMinimumsize() {
		return timeinMinimumsize;
	}

	public void setTimeinMinimumsize(String[] timeinMinimumsize) {
		this.timeinMinimumsize = timeinMinimumsize;
	}

	public String[] getDefectInjectedPlanning() {
		return defectInjectedPlanning;
	}

	public void setDefectInjectedPlanning(String[] defectInjectedPlanning) {
		this.defectInjectedPlanning = defectInjectedPlanning;
	}

	public String[] getDefectInjectedDesign() {
		return defectInjectedDesign;
	}

	public void setDefectInjectedDesign(String[] defectInjectedDesign) {
		this.defectInjectedDesign = defectInjectedDesign;
	}

	public String[] getDefectInjectedCode() {
		return defectInjectedCode;
	}

	public void setDefectInjectedCode(String[] defectInjectedCode) {
		this.defectInjectedCode = defectInjectedCode;
	}

	public String[] getDefectInjectedCodeReview() {
		return defectInjectedCodeReview;
	}

	public void setDefectInjectedCodeReview(String[] defectInjectedCodeReview) {
		this.defectInjectedCodeReview = defectInjectedCodeReview;
	}

	public String[] getDefectInjectedCompile() {
		return defectInjectedCompile;
	}

	public void setDefectInjectedCompile(String[] defectInjectedCompile) {
		this.defectInjectedCompile = defectInjectedCompile;
	}

	public String[] getDefectInjectedTest() {
		return defectInjectedTest;
	}

	public void setDefectInjectedTest(String[] defectInjectedTest) {
		this.defectInjectedTest = defectInjectedTest;
	}

	public String[] getDefectInjectedTotal() {
		return defectInjectedTotal;
	}

	public void setDefectInjectedTotal(String[] defectInjectedTotal) {
		this.defectInjectedTotal = defectInjectedTotal;
	}

	public String[] getDefectRemovedPlanning() {
		return defectRemovedPlanning;
	}

	public void setDefectRemovedPlanning(String[] defectRemovedPlanning) {
		this.defectRemovedPlanning = defectRemovedPlanning;
	}

	public String[] getDefectRemovedDesign() {
		return defectRemovedDesign;
	}

	public void setDefectRemovedDesign(String[] defectRemovedDesign) {
		this.defectRemovedDesign = defectRemovedDesign;
	}

	public String[] getDefectRemovedCode() {
		return defectRemovedCode;
	}

	public void setDefectRemovedCode(String[] defectRemovedCode) {
		this.defectRemovedCode = defectRemovedCode;
	}

	public String[] getDefectRemovedCodeReview() {
		return defectRemovedCodeReview;
	}

	public void setDefectRemovedCodeReview(String[] defectRemovedCodeReview) {
		this.defectRemovedCodeReview = defectRemovedCodeReview;
	}

	public String[] getDefectRemovedCompile() {
		return defectRemovedCompile;
	}

	public void setDefectRemovedCompile(String[] defectRemovedCompile) {
		this.defectRemovedCompile = defectRemovedCompile;
	}

	public String[] getDefectRemovedTest() {
		return defectRemovedTest;
	}

	public void setDefectRemovedTest(String[] defectRemovedTest) {
		this.defectRemovedTest = defectRemovedTest;
	}

	public String[] getDefectRemovedTotal() {
		return defectRemovedTotal;
	}

	public void setDefectRemovedTotal(String[] defectRemovedTotal) {
		this.defectRemovedTotal = defectRemovedTotal;
	}
	
	
	

}