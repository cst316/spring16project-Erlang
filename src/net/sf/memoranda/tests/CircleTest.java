package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import net.st.memoranda.psp.ui.Circle;
import net.st.memoranda.psp.ui.Rectangle;

public class CircleTest {

	Circle circle ;
	@Before
	public void setUp() throws Exception {
		circle = new Circle(40,40,100,20);
		circle.setColor(Color.ORANGE);
		circle.setStroke(10);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.ORANGE, circle.getColor());
	}

	@Test
	public void testSetColor() {
		circle.setColor(Color.BLUE);
		assertEquals(Color.BLUE,circle.getColor());
	}

	@Test
	public void testGetStroke() {
		assertEquals(10,circle.getStroke());
	}

	@Test
	public void testSetStroke() {
		circle.setStroke(5);
		assertEquals(5,circle.getStroke());
	}

	@Test
	public void testSetCoordinates() {
		circle.setCoordinates(50.0, 50.0, 5.0, 5.0);
		assertTrue(50.0 == circle.getX());
		assertTrue(50.0 == circle.getY());
		assertTrue(5.0== circle.getWidth());
		assertTrue(5.0 == circle.getHeight());
	}
}