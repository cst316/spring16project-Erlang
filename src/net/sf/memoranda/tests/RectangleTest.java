package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.Rectangle;

public class RectangleTest {
	Rectangle rect ;
	@Before
	public void setUp() throws Exception {
		rect = new Rectangle(40,40,100,20, 10, 10);
		rect.setColor(Color.ORANGE);
		rect.setStroke(10);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.ORANGE, rect.getColor());
	}

	@Test
	public void testSetColor() {
		rect.setColor(Color.BLUE);
		assertEquals(Color.BLUE,rect.getColor());
	}

	@Test
	public void testGetStroke() {
		assertEquals(10,rect.getStroke());
	}

	@Test
	public void testSetStroke() {
		rect.setStroke(5);
		assertEquals(5,rect.getStroke());
	}

	@Test
	public void testSetCoordinates() {
		rect.setCoordinates(50.0, 50.0, 5.0, 5.0);
		assertTrue(50.0 == rect.getX());
		assertTrue(50.0 == rect.getY());
		assertTrue(5.0== rect.getWidth());
		assertTrue(5.0 == rect.getHeight());
	}

}
