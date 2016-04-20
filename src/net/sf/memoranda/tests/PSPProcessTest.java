package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import net.sf.memoranda.Defect;
import net.sf.memoranda.Estimation;
import net.sf.memoranda.PSPProcess;
import net.sf.memoranda.Planning;
import net.sf.memoranda.Report;
import net.sf.memoranda.TimerLog;
import net.sf.memoranda.ui.ExportedImage;
import net.sf.memoranda.ui.SummaryPanel;

import org.junit.Before;
import org.junit.Test;

public class PSPProcessTest {
	
	Vector<Estimation> estimation;
	Vector<Defect> defects;
	Vector<TimerLog> timelogs;
	
	SummaryPanel summPanelObs;
	
	double[] timeEstimates;
	Planning planning;
	ExportedImage designImage;
	PSPProcess pspTest= new PSPProcess();
	Estimation newEstimation = new Estimation("Test", 5);
	
	
	@Test
	public void PSPProcessMethods() {
		
		pspTest.addEstimation(newEstimation);
		System.out.println(estimation);
		assertNotNull(pspTest.getAllEstimations());
		System.out.println(estimation);
		pspTest.addEstimation(newEstimation);
		System.out.println(newEstimation);
		assertEquals(pspTest.getEstimationSize(), 2);
		
	}
	
	@Test
	public void TimerTest() {

	
	}
	
	@Test
	public void EstimationTest() {

	}
	
}
