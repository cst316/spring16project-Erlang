package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import net.sf.memoranda.Defect;

public class DefectTest extends TestCase {
	
	Defect defect1 = new Defect();
	//Defect defect2 = new Defect();
	//Defect defect3 = new Defect();
	
	@BeforeClass
	protected void setUpBeforeClass() throws Exception {
		//super.setUp();
	}
	@AfterClass
	protected void tearDownAfterClass() throws Exception {
		//super.tearDown();
	}
	@Before
	public void setUp() throws Exception{
		
	}
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testDefectNumber() {
		defect1.setNumber(3);
		assertTrue(defect1.getNumber() == 3);
		defect1.setNumber(-1);
		assertTrue(defect1.getNumber() == -1);
		defect1.setNumber(1952);
		assertTrue(defect1.getNumber() == 1952);
		
	}
	
	@Test
	public void testDate(){
		defect1.setDate("04/05/1994");
		assertTrue(defect1.getDate() == "04/05/1994" );
	}
	
	@Test
	public void testProjectName(){
		defect1.setProjectName("memoranda test");
		assertTrue(defect1.getProjectName() == "memoranda test" );
		assertFalse(defect1.getProjectName() == "hopfully not");
	}
	
	@Test
	public void testInject(){
		defect1.setInject("memoranda test inject");
		assertTrue(defect1.getInject() == "memoranda test inject" );
		assertFalse(defect1.getProjectName() == "hopfully not");
	}
	
	

}
