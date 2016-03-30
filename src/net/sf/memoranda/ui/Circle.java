package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;
/**
 * This Class is used to draw shapes. It contains an assigned Color and Stroke size
 * @author Carlos
 *
 */
public class Circle extends Double implements MemorandaShape{
	private Color color;
	private int stroke;

	public Circle(double x,double y,double w,double h){
		super(x,y,w,h);
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
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
	}
	
}
