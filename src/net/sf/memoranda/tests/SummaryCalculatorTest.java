package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.SummaryCalculator;

public class SummaryCalculatorTest {

	@Test
	public void timeTotalTest() {
		double[] testArray1 = {1.0};
		double result1 = SummaryCalculator.timeTotal(testArray1);
		assertEquals(1.0, result1, 0.1);
		
		double[] testArray2 = {};
		double result2 = SummaryCalculator.timeTotal(testArray2);
		assertEquals(0.0, result2, 0.1);
		
		double[] testArray3 = {1.0, 1, 0, 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1, 1};
		double result3 = SummaryCalculator.timeTotal(testArray3);
		assertEquals(11.0, result3, 0.1);
		
		double[] testArray4 = {10000, 20131212.6464637};
		double result4 = SummaryCalculator.timeTotal(testArray4);
		assertEquals(20141212.6464637, result4, 0.1);
	}
	
	@Test
	public void lOCPerHourTest() {
		double result1 = SummaryCalculator.lOCPerHour(0, 0.0);
		assertEquals(0.0, result1, 0.1);
		double result2 = SummaryCalculator.lOCPerHour(1, 0);
		assertEquals(0.0, result2, 0.1);
		double result3 = SummaryCalculator.lOCPerHour(0, 1);
		assertEquals(0.0, result3, 0.1);
		double result4 = SummaryCalculator.lOCPerHour(1, -1);
		assertEquals(0.0, result4, 0.1);
		double result5 = SummaryCalculator.lOCPerHour(-1, -1);
		assertEquals(0.0, result5, 0.1);
		double result6 = SummaryCalculator.lOCPerHour(-1, 1);
		assertEquals(0.0, result6, 0.1);
		double result7 = SummaryCalculator.lOCPerHour(100, 3600.0);
		assertEquals(100.0, result7, 0.1);
		double result8 = SummaryCalculator.lOCPerHour(500, 5703.0);
		assertEquals(315.6, result8, 0.1);
	}
	
	@Test
	public void defectsPerKLOCTest() {
		double result1 = SummaryCalculator.defectsPerKLOC(0, 0);
		assertEquals(0.0, result1, 0.1);
		double result2 = SummaryCalculator.defectsPerKLOC(1, 0);
		assertEquals(0.0, result2, 0.1);
		double result3 = SummaryCalculator.defectsPerKLOC(0, 1);
		assertEquals(0.0, result3, 0.1);
		double result4 = SummaryCalculator.defectsPerKLOC(1, -1);
		assertEquals(0.0, result4, 0.1);
		double result5 = SummaryCalculator.defectsPerKLOC(-1, -1);
		assertEquals(0.0, result5, 0.1);
		double result6 = SummaryCalculator.defectsPerKLOC(-1, 1);
		assertEquals(0.0, result6, 0.1);
		double result7 = SummaryCalculator.defectsPerKLOC(100, 50000);
		assertEquals(2.0, result7, 0.1);
		double result8 = SummaryCalculator.defectsPerKLOC(500, 435435);
		assertEquals(1.15, result8, 0.01);
	}

}
