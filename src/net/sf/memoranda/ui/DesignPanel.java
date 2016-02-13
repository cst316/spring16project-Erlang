package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


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
	
	private Point iPoint, fPoint;			// initial and final points, used for mouse position
	
	private DesignListener listener;
	private ImageIcon textI, circleI, rectangleI, lineI;	//Icons for buttons
	private SketchToolsPanel  buttonPanel;	
	private Sketch sketch;
	
	public DesignPanel(){
		this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 1000));
      
		this.setBackground(Color.WHITE);
        this.setOpaque(true);
        iPoint = null;
        fPoint = null;
        listener = new DesignListener();
        
        buttonPanel = new SketchToolsPanel();		//contains the buttons
        sketch = new Sketch();			//where the user will draw
       
        sketch.addMouseListener(listener);
     
        this.add(buttonPanel,BorderLayout.NORTH);

        this.add(sketch,BorderLayout.CENTER);        
	}
	/**
	 * This inner class will be used to stage the sketch tools for drawing the design
	 * @author Carlos
	 * @version 1.0
	 */
	private class SketchToolsPanel extends JPanel{
		
		private JButton text, circle, rectangle, line, undo, redo;			
		private GridBagLayout gridbag = new GridBagLayout();
		private GridBagConstraints c = new GridBagConstraints();
		private JComboBox penSizes, penColors;									// Drop Down Menu for drawing size and colors
		private String[] colors = {"Green", "Red","Black","Blue"};				// contains color options
		private String[] sizes = {"1","2","3","4","5","6","7", "8", "9", "10"};	// contains pen size options
		
		SketchToolsPanel(){
			setLayout(gridbag);
			  penSizes =new JComboBox(sizes);
		      penColors = new JComboBox(colors);
			c.weightx = -5;
			//labels the JButtons with name of function 
	        text = new JButton(DesignTools.TEXT.getTitle());
	        circle = new JButton(DesignTools.CIRCLE.getTitle());
	        rectangle = new JButton(DesignTools.RECTANGLE.getTitle());
	        line = new JButton(DesignTools.LINE.getTitle());
	        
	        undo = new JButton("Undo");
	        redo = new JButton("Redo"); 
	        
	        text.addMouseListener(listener);
	        circle.addMouseListener(listener);
	        rectangle.addMouseListener(listener);
	        line.addMouseListener(listener);
	        penSizes.addMouseListener(listener);
	        penColors.addMouseListener(listener);
	        undo.addMouseListener(listener);
	        redo.addMouseListener(listener);     
	        
	        c.ipadx = 20;// adjust internal padding until image icons replace text
	        gridbag.setConstraints(text, c);
	        c.ipadx = 17;
	        gridbag.setConstraints(circle, c);
	        c.ipadx = 0;
	        gridbag.setConstraints(rectangle, c);
	        c.ipadx = 20;
	        gridbag.setConstraints(line, c);
	        c.ipadx = 0;
	        
	        add(undo);
	        add(redo);
	        add(penSizes);
	        add(penColors);
	        add(text);
	        add(circle);
	        add(rectangle);
	        add(line);
		}
		public JButton getTextButton(){
			return text;
		}
		public JButton getCircleButton(){
			return circle;
		}
		public JButton getRectangleButton(){
			return rectangle;
		}
		public JButton getLineButton(){
			return line;
		}
		public JComboBox getPenSizes() {
			return penSizes;
		}
		public JComboBox getPenColors() {
			return penColors;
		}
		
	}
	/**
	 * This inner class panel will be used to sketch the design
	 * @author Carlos
	 * @version 1.0
	 */
	private class Sketch extends JPanel{
		private int w,h,x,y;

		private Sketch(){
			super();
			setBackground(Color.white);
			this.setOpaque(true);
			w=h=x=y=0;
			
		}// needs implementation to draw on the go, and leaving the previous drawing on board
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int check = 0;
			g.setColor(Color.RED);
		if(iPoint != null){
			if (DesignTools.RECTANGLE.isActive()) {
				check = checkCondition(); // checks initial and final point axis'
				call(check);			 //arranges points based on condition
				g.drawRect(x, y, w, h);
            }
			else if (DesignTools.CIRCLE.isActive()) {
                g.setColor(Color.RED);
                check = checkCondition(); // checks initial and final point axis'
				call(check);			 //arranges points based on condition
				g.drawOval(x, y, w, h);
            }
			else if (DesignTools.LINE.isActive()) {
                g.setColor(Color.RED);
                g.drawLine(iPoint.x, iPoint.y, fPoint.x, fPoint.y);
                
            }//STRING NEEDS IMPLEMENTATION FOR USER INPUT to write on the fly.
			else if (DesignTools.TEXT.isActive()) {
                g.setColor(Color.RED);
                //g.drawString("HELLO",iPoint.x, iPoint.y);
            }
		}
			
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
		 * This method is used to arrange the initial and final point values to draw the correct shape
		 * regardless of direction drawn
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
	}
	/**
	 * This listener detects the users mouse activity and enables drawing of shapes
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
			else if(e.getSource() == buttonPanel.getTextButton()){
				DesignTools.textSelected();
			}//sets Circle to selected
			else if(e.getSource() == buttonPanel.getCircleButton()){
				DesignTools.circleSelected();
			}//sets Rectangle to selected
			else if(e.getSource() == buttonPanel.getRectangleButton()){
				DesignTools.rectangleSelected();
			}//sets Line to selected
			else if(e.getSource() == buttonPanel.getPenSizes()){
				 JComboBox cb = (JComboBox)e.getSource();
			       String newSelection = (String)cb.getSelectedItem();
			       System.out.println(newSelection);	
			
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource() == sketch){
				fPoint = e.getPoint();
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