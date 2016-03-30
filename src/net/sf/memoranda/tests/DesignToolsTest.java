package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.treetable.DesignTools;

public class DesignToolsTest {
	JButton testButton;
	@Before
	public void setUp(){
	testButton = new JButton();
	}

	@Test
	public void testGetInUse() {
		DesignTools.deleteSelected();
			assertEquals("Should be the same", DesignTools.DELETE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.TEXT, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.RECTANGLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.CIRCLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.LINE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.SELECT, DesignTools.getInUse());
		
		DesignTools.textSelected();
			assertEquals("Should be the same", DesignTools.TEXT, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.DELETE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.RECTANGLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.CIRCLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.LINE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.SELECT, DesignTools.getInUse());
			
			DesignTools.textSelected();
			assertEquals("Should be the same", DesignTools.TEXT, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.DELETE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.RECTANGLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.CIRCLE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.LINE, DesignTools.getInUse());
			assertNotEquals("Should not be the same", DesignTools.SELECT, DesignTools.getInUse());
	
	}

	@Test
	public void testIsActive() {
		DesignTools.textSelected();
		assertTrue("Should be True",DesignTools.TEXT.isActive());	//check if TEXT is active
	
		DesignTools.circleSelected();
		assertTrue("Should be True",DesignTools.CIRCLE.isActive());	//check if CIRCLE is active
		
		DesignTools.rectangleSelected();
		assertTrue("Should be True",DesignTools.RECTANGLE.isActive());	//check if RECTANGLE is active

		DesignTools.lineSelected();
		assertTrue("Should be True",DesignTools.LINE.isActive());	//check if LINE is active
		
		DesignTools.selectSelected();
		assertTrue("Should be True",DesignTools.SELECT.isActive());	//check if CIRCLE is active
		
		DesignTools.deleteSelected();
		assertTrue("Should be True",DesignTools.DELETE.isActive());	//check if DELETE is active
	}

	@Test
	public void testGetTitle() {
		assertEquals("Should be equal","Delete", DesignTools.DELETE.getTitle());
		assertEquals("Should be equal","Text",DesignTools.TEXT.getTitle());
		assertEquals("Should be equal","Rectangle",DesignTools.RECTANGLE.getTitle());
		assertEquals("Should be equal","Circle",DesignTools.CIRCLE.getTitle());
		assertEquals("Should be equal","Line",DesignTools.LINE.getTitle());
		assertEquals("Should be equal","Select",DesignTools.SELECT.getTitle());
	}

	@Test
	public void testSelectSelected() {
		DesignTools.selectSelected();
		assertTrue(DesignTools.SELECT.isActive());
	}

	@Test
	public void testTextSelected() {
		DesignTools.textSelected();
		assertTrue(DesignTools.TEXT.isActive());
	}

	@Test
	public void testCircleSelected() {
		DesignTools.circleSelected();
		assertTrue(DesignTools.CIRCLE.isActive());
	}

	@Test
	public void testRectangleSelected() {
		DesignTools.rectangleSelected();
		assertTrue(DesignTools.RECTANGLE.isActive());
	}

	@Test
	public void testLineSelected() {
		DesignTools.lineSelected();
		assertTrue(DesignTools.LINE.isActive());
	}

	@Test
	public void testDeleteSelected() {
		DesignTools.deleteSelected();
		assertTrue(DesignTools.DELETE.isActive());
	}

	@Test
	public void testGetButton() {
		System.out.println(testButton.getClass());
		assertEquals(testButton.getClass(),DesignTools.DELETE.getButton().getClass());
	}

}
