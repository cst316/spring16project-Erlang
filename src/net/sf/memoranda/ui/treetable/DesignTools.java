package net.sf.memoranda.ui.treetable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * This Enumeration defines the implementation of constants that will be used
 * to create user interaction interface for the DesignPanel, SketchToolbar
 * @author Carlos
 *
 */
public enum DesignTools {
	DELETE("Delete",false,"resources/icons/delete.png"),
	TEXT("Text", false,"resources/icons/text.png"),
	RECTANGLE("Rectangle",false,"resources/icons/rectangle.png"),
	CIRCLE("Circle",false,"resources/icons/circle.png"),
	LINE("Line", false,"resources/icons/line.png"),
	SELECT("Select", false, "resources/icons/select.png"),
	EXPORT("Export", false, "resources/icons/import.png");
	
	
	private String title;
	//active is used to determine if this constant is set or not
	private boolean active;
	//This icon representing the button 
	private ImageIcon icon;
	private JButton button;
	//static variable will be used to get the constant in use
	private  static DesignTools inUse = SELECT;
	
	DesignTools(String title, boolean status, String iconLocation){
		
		this.title = title;
		this.active = status;
		this.icon = new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class
				.getResource(iconLocation));
		this.button = new JButton(this.title,this.icon);
	}
	/**
	 * Get the Constant in use
	 * @return Constant in use
	 */
	public static DesignTools getInUse(){
		return inUse;
	}
	/**
	 * This method will notify whether it is currently active or not
	 * @return if this constant is active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * This method sets the constant active or inactive
	 * @param active status of this constant
	 */
	private void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * This method sets SELECT as active and in use
	 */
	public static void selectSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(true);
		DELETE.setActive(false);
		EXPORT.setActive(false);
		inUse = SELECT;
	}
	/**
	 * This method sets TEXT as active and in use
	 */
	public static void textSelected(){
		TEXT.setActive(true);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		DELETE.setActive(false);
		EXPORT.setActive(false);
		inUse = TEXT;
	}
	/**
	 * This method sets CIRCLE as active and in use
	 */
	public static void circleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(true);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		DELETE.setActive(false);
		EXPORT.setActive(false);

		inUse = CIRCLE;
	}
	/**
	 * This method sets RECTANGLE as active and in use
	 */
	public static void rectangleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(true);
		LINE.setActive(false);
		SELECT.setActive(false);
		DELETE.setActive(false);
		EXPORT.setActive(false);
		inUse = RECTANGLE;
	}
	/**
	 * This method sets LINE as active and in use
	 */
	public static void lineSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(true);
		SELECT.setActive(false);
		DELETE.setActive(false);
		EXPORT.setActive(false);
		inUse = LINE;
	}
	/**
	 * This method sets DELETE as active and in use
	 */
	public static void deleteSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		DELETE.setActive(true);
		EXPORT.setActive(false);
		inUse = DELETE;
	}
	/**
	 * This method sets EXPORT as active and in use
	 */
	public static void exportSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		DELETE.setActive(false);
		EXPORT.setActive(true);
		inUse = EXPORT;
	}
	/**
	 * This method returns the button 
	 * @return button
	 */
	public JButton getButton() {
		return button;
	}
}
