package net.sf.memoranda.ui.treetable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public enum DesignTools {
	UNDO("Undo", false, "resources/icons/undo.png"),
	RED("Redo",false,"resources/icons/redo.png"),
	TEXT("Text", false,"resources/icons/text.png"),
	RECTANGLE("Rectangle",false,"resources/icons/rectangle.png"),
	CIRCLE("Circle",false,"resources/icons/circle.png"),
	LINE("Line", false,"resources/icons/line.png"),
	SELECT("Select", false, "resources/icons/select.png");
	
	
	private String title;
	private boolean active;
	private ImageIcon icon;
	private JButton button;
	private  static DesignTools inUse = SELECT;
	
	DesignTools(String title, boolean status, String iconLocation){
		
		this.title = title;
		this.active = status;
		this.icon = new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class
				.getResource(iconLocation));
		this.button = new JButton(this.icon);
	}
	
	public static DesignTools getInUse(){
		return inUse;
	}
	public boolean isActive() {
		return active;
	}

	private void setActive(boolean active) {
		this.active = active;
	}

	public String getTitle() {
		return title;
	}
	
	public static void selectSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(true);
		inUse = SELECT;
	}
	public static void textSelected(){
		TEXT.setActive(true);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		inUse = TEXT;
	}
	public static void circleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(true);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
		SELECT.setActive(false);
		inUse = CIRCLE;
	}
	public static void rectangleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(true);
		LINE.setActive(false);
		SELECT.setActive(false);
		inUse = RECTANGLE;
	}
	public static void lineSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(true);
		SELECT.setActive(false);
		inUse = LINE;
	}

	public JButton getButton() {
		return button;
	}
}