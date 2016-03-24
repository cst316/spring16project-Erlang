package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import net.sf.memoranda.Report;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReportTest {

	Report testReport;
	
	@Before
	public void setUp() throws Exception {
		testReport = new Report();
	}

	@Test
	public void projectStringTest() {
		testReport.setProjectString("Test Project String");
		assertNull(testReport.getTasksString());
		assertNull(testReport.getNotesString());
		assertNull(testReport.getResourcesString());
		assertEquals(testReport.getProjectString(), "Test Project String");
	}
	
	@Test
	public void tasksStringTest() {
		testReport.setTasksString("Test Tasks String");
		assertNull(testReport.getProjectString());
		assertNull(testReport.getNotesString());
		assertNull(testReport.getResourcesString());
		assertEquals(testReport.getTasksString(), "Test Tasks String");
	}
	
	@Test
	public void notesStringTest() {
		testReport.setNotesString("Test Notes String");
		assertNull(testReport.getTasksString());
		assertNull(testReport.getProjectString());
		assertNull(testReport.getResourcesString());
		assertEquals(testReport.getNotesString(), "Test Notes String");
	}
	
	@Test
	public void resourcesStringTest() {
		testReport.setResourcesString("Test Resources String");
		assertNull(testReport.getTasksString());
		assertNull(testReport.getProjectString());
		assertNull(testReport.getNotesString());
		assertEquals(testReport.getResourcesString(), "Test Resources String");
	}
	
	@Test
	public void comboStringTest1() {
		testReport.setResourcesString("Test Resources String");
		testReport.setNotesString("Test Notes String");
		assertNull(testReport.getTasksString());
		assertNull(testReport.getProjectString());
		assertEquals(testReport.getResourcesString(), "Test Resources String");
		assertEquals(testReport.getNotesString(), "Test Notes String");
	}
	
	@Test
	public void comboStringTest2() {
		testReport.setResourcesString("Test Resources String");
		testReport.setProjectString("Test Project String");
		assertNull(testReport.getTasksString());
		assertNull(testReport.getNotesString());
		assertEquals(testReport.getResourcesString(), "Test Resources String");
		assertEquals(testReport.getProjectString(), "Test Project String");
	}
	
	@Test
	public void comboStringTest3() {
		testReport.setResourcesString("Test Resources String");
		testReport.setTasksString("Test Tasks String");
		testReport.setProjectString("Test Project String");
		assertNull(testReport.getNotesString());
		assertEquals(testReport.getResourcesString(), "Test Resources String");
		assertEquals(testReport.getProjectString(), "Test Project String");
		assertEquals(testReport.getTasksString(), "Test Tasks String");
	}
	
	@Test
	public void comboStringTest4() {
		testReport.setNotesString("Test Notes String");
		testReport.setTasksString("Test Tasks String");
		testReport.setProjectString("Test Project String");
		assertNull(testReport.getResourcesString());
		assertEquals(testReport.getNotesString(), "Test Notes String");
		assertEquals(testReport.getProjectString(), "Test Project String");
		assertEquals(testReport.getTasksString(), "Test Tasks String");
	}

	@Test
	public void allStringsTest() {
		testReport.setNotesString("Test Notes String");
		testReport.setTasksString("Test Tasks String");
		testReport.setProjectString("Test Project String");
		testReport.setResourcesString("Test Resources String");
		assertEquals(testReport.getResourcesString(), "Test Resources String");
		assertEquals(testReport.getNotesString(), "Test Notes String");
		assertEquals(testReport.getProjectString(), "Test Project String");
		assertEquals(testReport.getTasksString(), "Test Tasks String");
	}
}
