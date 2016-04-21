package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import net.sf.memoranda.ReportSettings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReportSettingsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void everythingIncludedTest() {
		ReportSettings testSettings = new ReportSettings();
		testSettings.setWithProjectID(true);
		testSettings.setWithProjectDates(true);
		testSettings.setWithProjectStatus(true);
		testSettings.setWithProjectDescription(true);
		testSettings.setWithTasks(true);
		testSettings.setWithTaskIDs(true);
		testSettings.setWithTaskDates(true);
		testSettings.setWithTaskText(true);
		testSettings.setWithTaskStatus(true);
		testSettings.setWithTaskPriority(true);
		testSettings.setWithTaskProgress(true);
		testSettings.setWithTaskSubTasks(true);
		testSettings.setWithNotes(true);
		testSettings.setWithResources(true);
		assertTrue(testSettings.isWithProjectID());
		assertTrue(testSettings.isWithProjectDates());
		assertTrue(testSettings.isWithProjectStatus());
		assertTrue(testSettings.isWithProjectDescription());
		assertTrue(testSettings.isWithTasks());
		assertTrue(testSettings.isWithTaskIDs());
		assertTrue(testSettings.isWithTaskDates());
		assertTrue(testSettings.isWithTaskText());
		assertTrue(testSettings.isWithTaskPriority());
		assertTrue(testSettings.isWithTaskProgress());
		assertTrue(testSettings.isWithTaskStatus());
		assertTrue(testSettings.isWithTaskSubTasks());
		assertTrue(testSettings.isWithNotes());
		assertTrue(testSettings.isWithResources());
	}
	
	@Test
	public void nothingIncludedTest() {
		ReportSettings testSettings = new ReportSettings();
		assertFalse(testSettings.isWithProjectID());
		assertFalse(testSettings.isWithProjectDates());
		assertFalse(testSettings.isWithProjectStatus());
		assertFalse(testSettings.isWithProjectDescription());
		assertFalse(testSettings.isWithTasks());
		assertFalse(testSettings.isWithTaskIDs());
		assertFalse(testSettings.isWithTaskDates());
		assertFalse(testSettings.isWithTaskText());
		assertFalse(testSettings.isWithTaskPriority());
		assertFalse(testSettings.isWithTaskProgress());
		assertFalse(testSettings.isWithTaskStatus());
		assertFalse(testSettings.isWithTaskSubTasks());
		assertFalse(testSettings.isWithNotes());
		assertFalse(testSettings.isWithResources());
	}
	
	@Test
	public void onlyProjectInfoTest() {
		ReportSettings testSettings = new ReportSettings();
		testSettings.setWithProjectID(true);
		testSettings.setWithProjectDates(true);
		testSettings.setWithProjectStatus(true);
		testSettings.setWithProjectDescription(true);
		assertTrue(testSettings.isWithProjectID());
		assertTrue(testSettings.isWithProjectDates());
		assertTrue(testSettings.isWithProjectStatus());
		assertTrue(testSettings.isWithProjectDescription());
		assertFalse(testSettings.isWithTasks());
		assertFalse(testSettings.isWithTaskIDs());
		assertFalse(testSettings.isWithTaskDates());
		assertFalse(testSettings.isWithTaskText());
		assertFalse(testSettings.isWithTaskPriority());
		assertFalse(testSettings.isWithTaskProgress());
		assertFalse(testSettings.isWithTaskStatus());
		assertFalse(testSettings.isWithTaskSubTasks());
		assertFalse(testSettings.isWithNotes());
		assertFalse(testSettings.isWithResources());
	}
	
	@Test
	public void onlyTasksTest() {
		ReportSettings testSettings = new ReportSettings();
		testSettings.setWithTasks(true);
		testSettings.setWithTaskIDs(true);
		testSettings.setWithTaskDates(true);
		testSettings.setWithTaskText(true);
		testSettings.setWithTaskStatus(true);
		testSettings.setWithTaskPriority(true);
		testSettings.setWithTaskProgress(true);
		testSettings.setWithTaskSubTasks(true);
		assertFalse(testSettings.isWithProjectID());
		assertFalse(testSettings.isWithProjectDates());
		assertFalse(testSettings.isWithProjectStatus());
		assertFalse(testSettings.isWithProjectDescription());
		assertTrue(testSettings.isWithTasks());
		assertTrue(testSettings.isWithTaskIDs());
		assertTrue(testSettings.isWithTaskDates());
		assertTrue(testSettings.isWithTaskText());
		assertTrue(testSettings.isWithTaskPriority());
		assertTrue(testSettings.isWithTaskProgress());
		assertTrue(testSettings.isWithTaskStatus());
		assertTrue(testSettings.isWithTaskSubTasks());
		assertFalse(testSettings.isWithNotes());
		assertFalse(testSettings.isWithResources());
	}
	
	@Test
	public void onlyNotesAndResources() {
		ReportSettings testSettings = new ReportSettings();
		testSettings.setWithNotes(true);
		testSettings.setWithResources(true);
		assertFalse(testSettings.isWithProjectID());
		assertFalse(testSettings.isWithProjectDates());
		assertFalse(testSettings.isWithProjectStatus());
		assertFalse(testSettings.isWithProjectDescription());
		assertFalse(testSettings.isWithTasks());
		assertFalse(testSettings.isWithTaskIDs()); 
		assertFalse(testSettings.isWithTaskDates());
		assertFalse(testSettings.isWithTaskText());
		assertFalse(testSettings.isWithTaskPriority());
		assertFalse(testSettings.isWithTaskProgress());
		assertFalse(testSettings.isWithTaskStatus());
		assertFalse(testSettings.isWithTaskSubTasks());
		assertTrue(testSettings.isWithNotes());
		assertTrue(testSettings.isWithResources());
	}
	

}
