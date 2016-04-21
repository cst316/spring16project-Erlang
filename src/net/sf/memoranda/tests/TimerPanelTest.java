package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.PSPProcess;
import net.sf.memoranda.Report;
import net.sf.memoranda.TimerLog;
import net.sf.memoranda.TimerLog.PspStage;
import net.sf.memoranda.ui.StopWatch;

/**
 * 
 * @author Roy Sofiov (rsofiov)
 *
 */

public class TimerPanelTest {

	PspStage pspTesting;
	PSPProcess pspProcess = new PSPProcess();

	double timeValueTest = 2.5;
	double temp;
	TimerLog testing = new TimerLog( );
	 private String[] tabsTest = { " ","PLANNING", "DESIGN", "CODE",
				"CODEREVIEW", "COMPILE", "TEST","POSTMORTEM"};

	 
	 Vector<TimerLog> TimelogArray = new Vector<TimerLog>();
	 private String timeString; 
	StopWatch stopWatchTest = new StopWatch(pspProcess);
	
	
	@Before
	public void setUpTimeLog() throws Exception {
		testing.setTimeValue(2.5);
		testing.setcStage(pspTesting.valueOf("PLANNING"));
	}

	
	@Before
	public void setUpStopWatch() throws Exception {
	
	}
	
	@Test
	public void timerLogTest() {
		temp = testing.getTimeValue();
		assertNotNull(testing);
		assertEquals(temp, 2.5, 0.01);
		testing.setTimeValue(-2.5);
		assertEquals(testing.getTimeValue(), 0, 0.1);
		
		assertNotNull(pspTesting.valueOf("PLANNING"));
		testing.setcStage(pspTesting.valueOf("COMPILE"));
		
		
	}
	

	@Test
	public void stopWatchTest() {
		assertNotNull(stopWatchTest);
	//	Dimension d = null;
		//stopWatchTest.setSize(d);
	
	}
	
	
}
