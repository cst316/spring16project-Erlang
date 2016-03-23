package net.sf.memoranda;
import java.util.Vector;
import com.sun.javafx.geom.Shape;
import net.sf.memoranda.ui.DesignPanel;


/**
 * The Design class is used to store the objects created from the design panel.
 * @author qbecker
 *
 */
public class Design {
	private Vector<Shape> shapes;
	
	//default empty parameters
	public Design(){
		
	}
	private void addShape(Shape shape){
		shapes.add(shape);
	}
	private void removeShape(Shape shape){
		shapes.remove(shape);
	}
	
}
