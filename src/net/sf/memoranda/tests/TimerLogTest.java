package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.TimerLog;
import net.sf.memoranda.TimerLog.PspStage;
/**
 * TimerLogTest class is a unit test for the TimeLog class.
 * @author qbecker
 *@version 1.0
 */
public class TimerLogTest {

	@Test
	public void testTimer() {
		
		TimerLog time = new TimerLog();
		int test1 = -4;
		time.setTimeValue(test1);
		assertTrue(time.getTimeValue() == 0);
		time.setcStage(PspStage.DESIGN);
		System.out.println(time.toString());
		
		TimerLog time2 = new TimerLog(PspStage.COMPILE, 45.3);
		
		assertTrue(time2.getTimeValue() == 45.3);
		assertFalse(time2.getcStage() == PspStage.DESIGN);
		
		
	}

}
