package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D.Double;
/**
 * This Class is used to draw shapes. It contains an assigned Color and Stroke size
 * @author Carlos
 *
 */
public class Rectangle extends Double implements MemorandaShape{
	private Color color;
	private int stroke;
	
	
	public Rectangle(double x,double y,double w,double h, double arcw, double arch){
		super(x,y,w,h, arcw, arch);
		color = null;
		stroke = 0;	
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
	public void setCoordinates(double x,double y,double w,double h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.arcwidth = 10;
		this.archeight = 10;
	}
}
