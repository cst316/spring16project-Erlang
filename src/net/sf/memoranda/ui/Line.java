package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.geom.Line2D.Double;
/**
 * This Class is used to draw shapes. It contains an assigned Color and Stroke size
 * @author Carlos
 *
 */
public class Line extends Double implements MemorandaShape{
	private Color color;
	private int stroke;
	public Line(double x1, double y1, double x2, double y2){
		super(x1,y1,x2,y2);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getStroke() {
		return stroke;
	}
	public void setStroke(int i) {
		this.stroke = i;
	}
	public void setCoordinates(double x, double y, double w, double h) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = w;
		this.y2 = h;		
	}
}
