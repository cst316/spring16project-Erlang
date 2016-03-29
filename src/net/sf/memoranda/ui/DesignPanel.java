package net.sf.memoranda.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import net.sf.memoranda.ui.treetable.DesignTools;
/**
 * The DesignPanel class' purpose is to represent a scratch sheet of paper to
 * sketch or "Design" ideas digitally.
 * @author Carlos
 *
 */
public class DesignPanel extends JPanel{
	protected SketchToolsPanel getToolsPanel() {
		return toolsPanel;
	}

	protected Sketch getSketch() {
		return sketch;
	}

	private Point iPoint, fPoint;				// initial and final points, used for mouse position
	private DesignListener listener;
	private SketchToolsPanel  toolsPanel;	
	private Sketch sketch;
	private int w,h,x,y;
	private double xOffset, yOffset;
	boolean pressedSwitch;
	public DesignPanel(){
		
		this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 1000));
		this.setBackground(Color.WHITE);
        this.setOpaque(true);

		w=h=x=y=0;
        this.iPoint = null;
        this.fPoint = null;
        this.listener = new DesignListener();

        this.toolsPanel = new SketchToolsPanel();		//contains the design/drawing tools
        this.sketch = new Sketch();						//where the user will draw
        this.sketch.addMouseListener(listener);
        this.add(toolsPanel,BorderLayout.NORTH);
        this.add(sketch,BorderLayout.CENTER); 
        
        this.pressedSwitch = false;
	}

	
	/**
	 * This inner class will be used to stage the sketch tools for drawing the design
	 * @author Carlos
	 * @version 1.0
	 */
	private class SketchToolsPanel extends JPanel implements ActionListener{
		
		private JComboBox penSizes, penColors;									// Drop Down Menu for drawing size and colors
		private String[] colors = {"Black","Green", "Red","Blue"};				// contains color options
		private String[] sizes = {"1","2","3","4","5","6","7", "8", "9", "10"};	// contains pen size options
		
		SketchToolsPanel(){
			//setLayout(gridbag);
			 penSizes =new JComboBox(sizes);
		     penColors = new JComboBox(colors);

	        penSizes.addActionListener(this);
	        penColors.addActionListener(this);	        
	        
	        add(penSizes);
	        add(penColors);
	        
	        // Iterate through the enum Jbuttons, add them to this component and a listener to the button
	        for(DesignTools tool : DesignTools.values()){
	        	this.add(tool.getButton());
	        	tool.getButton().addMouseListener(listener);
	        }
		}

		public JComboBox getPenSizes() {
			return penSizes;
		}
		public JComboBox getPenColors() {
			return penColors;
		}
		public Color getColor(){
			int index = penColors.getSelectedIndex();
			Color color = null;
			switch(index){
			case 0:
				color = Color.black;
				break;
			case 1:
				color = Color.green;
				break;
			case 2:
				color = Color.red;
				break;
			case 3:
				color = Color.blue;
				break;
			}
			return color;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//if pensize jcombo box is selected
			if(toolsPanel.getPenSizes() == e.getSource()){
				 JComboBox cb = (JComboBox)e.getSource();
				 String size = (String)cb.getSelectedItem();
				 //System.out.println(size);						//Test Verify Print
			}//if pencolors jcombo box is selected
			else if(e.getSource() == toolsPanel.getPenColors()){
				JComboBox cb = (JComboBox)e.getSource();
				String color = (String)cb.getSelectedItem();
				// System.out.println(color);						//Test Verify Print
			}	
		}

	}
	/**
	 * This inner class panel will be used to sketch the user's design
	 * @author Carlos
	 * @version 1.0
	 */
	private class Sketch extends JPanel{
		private Vector <Shape> shapes;			// All shapes drawn on the pane.
		private Sketch(){
			super();
			setBackground(Color.white);
			setOpaque(true);
			shapes = new Vector<Shape>(10);
			addMouseMotionListener(listener);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			drawShape(); 		// want to draw only when rectangle, ellipse, or line is selected
			
			//This for each loop will iterate through an Vector and redraw each shape.
			for(Shape shape : shapes){
				if(shape.getClass() == Rectangle.class){
					  Rectangle rect = (Rectangle)shape;
					  g2.setColor(rect.getColor());
					  g2.setStroke(new BasicStroke(rect.getStroke()));
					  g2.draw(shape);
				}else if(shape.getClass() == Circle.class){
					  Circle circle = (Circle)shape;
					  g2.setColor(circle.getColor());
					  g2.setStroke(new BasicStroke(circle.getStroke()));
					  g2.draw(shape);
				}else if(shape.getClass() == Line.class){
					  Line line = (Line)shape;
					  g2.setColor(line.getColor());
					  g2.setStroke(new BasicStroke(line.getStroke()));
					  g2.draw(shape);
				}
			}
		}
		
		private Vector<Shape> getShapes() {
			return shapes;
		}

		/**
		 * This method will take the inputs of each user mouse click and release then create the 
		 * relative shapes at to its location and add them to an ArrayList.
		 */
		private void drawShape(){
			if(iPoint != null){
				int check = 0;
				switch(DesignTools.getInUse()){
				case RECTANGLE:
					check = checkCondition(); 	// checks initial and final point axis'
					call(check);			 	//arranges points based on condition drawn
					Rectangle rect = new Rectangle(x,y,w,h,10,10);
					rect.setColor((Color)toolsPanel.getColor());						//set color
					rect.setStroke((int)toolsPanel.getPenSizes().getSelectedIndex()+1);	//set pen thickness
					addShape(rect);					
					break;
				case CIRCLE:
					check = checkCondition(); 	// checks initial and final point axis'
					sketch.call(check);			 	//arranges points based on condition
					Circle ell = new Circle(x,y,w,h);
					ell.setColor((Color)toolsPanel.getColor());						//set color
					ell.setStroke((int)toolsPanel.getPenSizes().getSelectedIndex()+1);	//set pen thickness
					addShape(ell);
					break;
				case LINE:
					Line line = new Line(iPoint.x, iPoint.y, fPoint.x, fPoint.y);
					line.setColor((Color)toolsPanel.getColor());						//set color
					line.setStroke((int)toolsPanel.getPenSizes().getSelectedIndex()+1);	//set pen thickness
					sketch.addShape(line);
					break;
				case SELECT:
					//System.out.println("SELECT CASE");
					break;
				}
			}
		}
		

		/**
		 * This method will delete the shape that was drawn onto the Sketch, into the ArrayList
		 * @param shape to be deleted from the ArrayList of shapes
		 */
		private void removeShape(Shape shape){
			shapes.remove(shape);
		}
		
		/**
		 * This method will add the newly drawn Shape to the shapes ArrayList
		 * @param shape the newly drawn shape to add to an ArrayList
		 */
		private void addShape(Shape shape){
			shapes.add(shape);
		}
		
		/**
		 * This method is used to check the conditions of the iPoint and fPoints of user click on scrren
		 * @return the case condition of iPoint and fPoint values
		 */
		private int checkCondition(){
			int condition = 0;
		//situation1(Quadrant2 -> Quadrant 4)	
            if(iPoint.x < fPoint.x && iPoint.y < fPoint.y)
            	condition = 1;
        //situation2 (Quadrant3 -> Quadrant 1)
            else if(iPoint.x < fPoint.x && iPoint.y > fPoint.y)
            	condition = 2;
        //situation3 (Quadrant4 -> Quadrant 2)
            else if(iPoint.x > fPoint.x && iPoint.y > fPoint.y)
            	condition = 3;
        //situation4 (Quadrant 1 -> Quadrant 3)
            else if(iPoint.x > fPoint.x && iPoint.y < fPoint.y)
                condition = 4;
            
			return condition;
		}
		/**
		 * This method is used to arrange the initial and final point values (primarily for rectangle and ellipse)
		 * to draw the correct shape regardless of direction drawn
		 * @param selection the case used to arrange the values of iPoint and fPoint
		 * @return void
		 */
		private void call(int selection){
			
			switch(selection){
			case 1:
				 x = iPoint.x;
				 y = iPoint.y;
				 w = fPoint.x-iPoint.x; 
				 h = fPoint.y-iPoint.y; 

				break;
			case 2:
				x = iPoint.x;
				y = fPoint.y;
				w = fPoint.x-iPoint.x; 
				h = iPoint.y-fPoint.y;
				break;
			case 3:
				x = fPoint.x;
				y = fPoint.y;
				w = iPoint.x-fPoint.x;
				h = iPoint.y-fPoint.y;
				break;
			case 4:
				x = fPoint.x;
				y = iPoint.y;
				w = iPoint.x-fPoint.x;
				h = fPoint.y-iPoint.y;
				break;
			}
		}	
		
		/**
		 * This method will be used to relocate the selected shape after clicked and dragged
		 * it is only called when the mouse cursor is within a qualified shapes boundaries
		 * @param the shape that is being moved
		 * @param e MouseDragged
		 */
		private void updateLocation(Shape shape, MouseEvent e){
			//get last point, erase last position
			Point tmp = e.getPoint();
			x = tmp.x;
			y = tmp.y;
			removeShape(shape);				//attempt to remove last shape(selected)
			//GET THIS SHAPE, (ITS FEATURES) PLUS THE NEW POSIITONS
			if(shape.getClass() == Circle.class){
				Circle circle = (Circle)shape;
				circle.setCoordinates((x - xOffset),  (y - yOffset), circle.getWidth(), circle.getHeight());
				sketch.addShape(circle);
			}else if(shape.getClass() == Rectangle.class){
				Rectangle rect = (Rectangle)shape;
				rect.setCoordinates((x - xOffset),  (y - yOffset), rect.width, rect.getHeight());
				sketch.addShape(rect);
			}else if(shape.getClass() == Line.class){
				//Implementation for redrawing a Line here TBD

			}
			sketch.repaint();			
		}
	}

	/**
	 * This listener detects the users mouse activity and enables drawing and selection of shapes
	 * @author Carlos
	 * @version 1.0
	 */
	private class DesignListener implements MouseInputListener{
		private DesignListener() {
		
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			if(e.getSource() == sketch){
				iPoint = e.getPoint();		//used when determining initial position to draw a new shape
  			//	System.out.println("Pressed at location : ("+e.getX()+", "+e.getY()+")");
			}//sets Text to selected
			else if(e.getSource() == DesignTools.TEXT.getButton()){
				DesignTools.textSelected();
			}//sets Circle to selected
			else if(e.getSource() == DesignTools.CIRCLE.getButton()){
				DesignTools.circleSelected();
			}//sets Rectangle to selected
			else if(e.getSource() == DesignTools.RECTANGLE.getButton()){
				DesignTools.rectangleSelected();
			}//sets Line to selected
			else if(e.getSource() == DesignTools.LINE.getButton()){
				DesignTools.lineSelected();
			}//sets Select to Selected
			else if(e.getSource() == DesignTools.SELECT.getButton()){
				DesignTools.selectSelected();
			}//sets Delete to Selected
			else if(e.getSource() == DesignTools.DELETE.getButton()){
				DesignTools.deleteSelected();
			}//sets  Export to Selected
			else if(e.getSource() == DesignTools.EXPORT.getButton()){
				DesignTools.exportSelected();
			}	
			if(DesignTools.SELECT.isActive()){
					for(Shape shape : sketch.getShapes()){
						if(shape.contains(e.getPoint().x, e.getPoint().y)){	// if press is within a shape's boundary
							if (!pressedSwitch) {
								Rectangle2D testRect = shape.getBounds2D();
								yOffset = e.getY() - testRect.getY();
								xOffset = e.getX() - testRect.getX();
								pressedSwitch = true;
							}
						}
					}
			}
			if(DesignTools.DELETE.isActive()){
				for(Shape shape : sketch.getShapes()){
					if(shape.contains(e.getPoint().x, e.getPoint().y)){	
						sketch.removeShape(shape);
						break;
					}
				}
			}
			if(DesignTools.EXPORT.isActive()){
				//System.out.println("Taking a screenshot");
				try {
					this.saveScreenShot(sketch, "src/net/sf/memoranda/ui/resources/SketchImage.png");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//System.out.println("After a screenshot");

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource() == sketch){
  				fPoint = e.getPoint();			//used when determining final position to draw a new shape
  				sketch.repaint();
  			//	System.out.println("Released at location : ("+e.getX()+", "+e.getY()+")");
  			}
			if (pressedSwitch) {
				yOffset = 0;
				xOffset = 0;
				pressedSwitch = false;
			}
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			//Check if SELECT is active, if so iterate through on mouse presses to see which shape
			//is currently being pressed on to later move position.
			//System.out.println("Dragged at location : ("+e.getX()+", "+e.getY()+")");
			if(DesignTools.SELECT.isActive()){
					for(Shape shape : sketch.getShapes()){
						if(shape.contains(e.getX(),e.getY())){
							sketch.updateLocation(shape,e); 
							break;
						}
					}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
		////////////////////////////////////////			
		/*Referenced "Programming Knowledge" user name to take screen shot of a JPanel
		* https://www.youtube.com/watch?v=6xGihZDvOA0
		* NOTE: not precisely the same as the owner's original work
		*/
		/////////////////////////////////////////
		/**
		 * This Method 
		 * @param panel	The panel requesting a screenshot.
		 * @param destination	Destination of the screenshot.
		 * @throws IOException if file destination does not exist.
		 */
		public void saveScreenShot(JPanel panel, String destination) throws IOException{
			 BufferedImage bufImage = new BufferedImage(
		    		   panel.getWidth(), 
		    		   panel.getHeight(),
		    		   BufferedImage.TYPE_INT_RGB);
			 panel.paint(bufImage.createGraphics());
			 ImageIO.write(bufImage, "png", new File(destination));
		}
	}
}
