package net.sf.memoranda.ui;

import java.awt.Color;
/**
 * This interface contains methods for MemorandaShapes
 * @author Carlos
 *
 */
interface IMemorandaShape{
		/**
		 * This method changes the Color
		 * @return Color attribute
		 */
	 abstract Color getColor();
	 /**
	  * This method returns the assigned Color 
	  * @param the Color
	  */
	 abstract void setColor(Color color);
	 /**
	  * This methods returns the BasicStroke size
	  * @return stroke size
	  */
	 abstract int getStroke();
	 /**
	  * This method sets the BasicStroke size for this MemorandaShape
	  * @param i The new size
	  */
	 abstract void setStroke(int i);
	 /**
	  * This method
	  * @param x initial x position
	  * @param y initial y position
	  * @param w final x position
	  * @param h final y position 
	  */
	 abstract void setCoordinates(double x,double y,double w,double h);
}
