package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private Point iPoint, fPoint;				// initial and final points, used for mouse position
	
	private DesignListener listener;
	private SketchToolsPanel  toolsPanel;	
	private Sketch sketch;
	private int w,h,x,y;

	public DesignPanel(){
		
		this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 1000));
		this.setBackground(Color.WHITE);
        this.setOpaque(true);
		w=h=x=y=0;
		
        iPoint = null;
        fPoint = null;
        listener = new DesignListener();
        
        toolsPanel = new SketchToolsPanel();		//contains the design/drawing tools
        sketch = new Sketch();						//where the user will draw
       
        sketch.addMouseListener(listener);
     
        this.add(toolsPanel,BorderLayout.NORTH);
        this.add(sketch,BorderLayout.CENTER);        
	}

	
	/**
	 * This inner class will be used to stage the sketch tools for drawing the design
	 * @author Carlos
	 * @version 1.0
	 */
	private class SketchToolsPanel extends JPanel{
		
	//	private GridBagLayout gridbag = new GridBagLayout();
		//private GridBagConstraints c = new GridBagConstraints();
		private JComboBox penSizes, penColors;									// Drop Down Menu for drawing size and colors
		private String[] colors = {"Green", "Red","Black","Blue"};				// contains color options
		private String[] sizes = {"1","2","3","4","5","6","7", "8", "9", "10"};	// contains pen size options
		
		SketchToolsPanel(){
			//setLayout(gridbag);
			 penSizes =new JComboBox(sizes);
		     penColors = new JComboBox(colors);

			penSizes.addMouseListener(listener);
	        penColors.addMouseListener(listener);
	       
	        //need to fix button aligment, bring closer together
	  //     gridbag.setConstraints(DesignTools.TEXT.getButton(), c);
	    //    gridbag.setConstraints(DesignTools.CIRCLE.getButton(), c);
	      //  gridbag.setConstraints(DesignTools.RECTANGLE.getButton(), c);
	        //gridbag.setConstraints(DesignTools.LINE.getButton(), c);

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
	}
	/**
	 * This inner class panel will be used to sketch the user's design
	 * @author Carlos
	 * @version 1.0
	 */
	private class Sketch extends JPanel{
		//may need a hash table instead of aray list for position of ++shapes
		private ArrayList <Shape> shapes;			// All shapes drawn on the pane.
		
		//private RoundRectangle2D rect;
		private Sketch(){
			super();
			setBackground(Color.white);
			setOpaque(true);
			shapes = new ArrayList<Shape>(5);
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.RED);
			drawShape();
			
			//This for each loop will iterate through an ArrayList and redraw each shape.
			for(Shape shape : shapes){
				g2.draw(shape);
			}
		}
		
		/**
		 * This method will take the inputs of each user mouse click and release then create the 
		 * relative shapes at to its location and add them to an ArrayList.
		 */
		public void drawShape(){
			if(iPoint != null){
				int check = 0;
				switch(DesignTools.getInUse()){
				case RECTANGLE:
					check = checkCondition(); 	// checks initial and final point axis'
					call(check);			 	//arranges points based on condition drawn
					RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w, h, 10, 10);
					addShape(rect);					
					break;
				case CIRCLE:
					check = checkCondition(); 	// checks initial and final point axis'
					sketch.call(check);			 	//arranges points based on condition
					Ellipse2D ell = new Ellipse2D.Double(x,y,w,h);
					addShape(ell);
					break;
				case LINE:
					Line2D line = new Line2D.Double(iPoint.x, iPoint.y, fPoint.x, fPoint.y);
					sketch.addShape(line);
					break;
				}
				iPoint = null;	//explicitly prevent redrawing if not null
				fPoint = null;	
			}
		}
		private void updateLocation(Shape shape){
			
		}
		/**
		 * This method will add the newly drawn Rectangle to the shapes ArrayList
		 * @param shape to be added to the ArrayList of shapes
		 */
		private void addRectangle(RoundRectangle2D rect){
			shapes.add(rect);
		}
		/**
		 * This method will delete the shape that was drawn onto the Sketch, into the ArrayList
		 * @param shape to be deleted from the ArrayList of shapes
		 */
		private void deleteShape(Shape shape){
			shapes.remove(shape);
		}
		/**
		 * This method will add the newly drawn Ellipse to the shapes ArrayList
		 * @param ellipse
		 */
		private void addEllipse(Ellipse2D ellipse){
			shapes.add(ellipse);
		}
		private void addShape(Shape shape){
			shapes.add(shape);
		}
		
	
		/**
		 * This method is used to check the conditions of the iPoint and fPoints of user click on scrren
		 * @return the case condition of iPoint and fPoint values
		 */
		public int checkCondition(){
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
		public void call(int selection){
			
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
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == sketch){
				iPoint = e.getPoint();
				System.out.println("Initial Point: " + iPoint.toString());
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
				DesignTools.SELECT.selectSelected();
			}//if pensize jcombo box is selected (needs implementation)
			else if(e.getSource() == toolsPanel.getPenSizes()){
				 JComboBox cb = (JComboBox)e.getSource();
			}//if pencolors jcombo box is selected (needs implementation)
			else if(e.getSource() == toolsPanel.getPenColors()){
				JComboBox cb = (JComboBox)e.getSource();
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource() == sketch){
  				fPoint = e.getPoint();
  				repaint();
  				System.out.println("Finish Point: " + fPoint.toString());
  			}
		}
			
	

		@Override
		public void mouseEntered(MouseEvent e) {
			//System.out.println("Mouse Entered Sketch");
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//System.out.println("Mouse Exited Sketch");
			//repaint();
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			//TODO
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		//	fPoint = e.getPoint();			
		}
	
	}	
}