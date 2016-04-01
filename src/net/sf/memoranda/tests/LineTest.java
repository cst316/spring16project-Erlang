package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import net.sf.memoranda.ui.Line;

public class LineTest {
	Line line;
	@Before
	public void setUp() throws Exception {
		line = new Line(40,40,100,20);
		line.setColor(Color.ORANGE);
		line.setStroke(10);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.ORANGE, line.getColor());
	}

	@Test
	public void testSetColor() {
		line.setColor(Color.BLUE);
		assertEquals(Color.BLUE,line.getColor());
	}

	@Test
	public void testGetStroke() {
		assertEquals(10,line.getStroke());
	}

	@Test
	public void testSetStroke() {
		line.setStroke(5);
		assertEquals(5,line.getStroke());
	}

	@Test
	public void testSetCoordinates() {
		line.setCoordinates(50.0, 50.0, 5.0, 5.0);
		assertTrue(50.0 == line.getX1());
		assertTrue(50.0 == line.getY1());
		assertTrue(5.0== line.getX2());
		assertTrue(5.0 == line.getY2());
	}

}