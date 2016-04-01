package net.sf.memoranda.tests;
import net.sf.memoranda.Planning;
import static org.junit.Assert.*;

import org.junit.Test;
import net.sf.memoranda.date.*;

/**
 * PlanningTest class is a unit test for Planning class
 * @author qbecker
 *@version 1.0
 */
public class PlanningTest {

	Planning plan1 = new Planning();
	CurrentDate testDate = new CurrentDate();

	@Test
	public void testSetters() {
		plan1.setDate(testDate);
		plan1.setProgrammer("Quinten Becker");
		plan1.setProgramTitle("setterTest1");
		assertTrue(plan1.getProgrammer() == "Quinten Becker");
		assertTrue(plan1.getProgramTitle()=="setterTest1");
	}
	
	@Test
	public void testGetters(){
		plan1.setProgrammer("Quinten Becker");
		plan1.setProgramTitle("setterTest1");
		assertTrue(plan1.getProgrammer() == "Quinten Becker");
		assertTrue(plan1.getProgramTitle() =="setterTest1");
	}

}
