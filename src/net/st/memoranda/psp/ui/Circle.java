package net.st.memoranda.psp.ui;

import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;

import net.sf.memoranda.ui.IMemorandaShape;
/**
 * This Class is used to draw shapes. It contains an assigned Color and Stroke size
 * @author Carlos
 *
 */
public class Circle extends Double implements IMemorandaShape{
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
