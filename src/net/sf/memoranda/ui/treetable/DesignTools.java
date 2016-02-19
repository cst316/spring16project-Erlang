package net.sf.memoranda.ui.treetable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public enum DesignTools {
	UNDO("Undo", false, "resources/icons/undo.png"),
	RED("Redo",false,"resources/icons/redo.png"),
	TEXT("Text", false,"resources/icons/text.png"),
	RECTANGLE("Rectangle",false,"resources/icons/rectangle.png"),
	CIRCLE("Circle",false,"resources/icons/circle.png"),
	LINE("Line", false,"resources/icons/line.png");
	
	
	private String title;
	private boolean active;
	private ImageIcon icon;
	private JButton button;
	
	DesignTools(String title, boolean status, String iconLocation){
		this.title = title;
		this.active = status;
		
		icon = new ImageIcon(
				net.sf.memoranda.ui.AppFrame.class
				.getResource(iconLocation));
		
		this.button = new JButton(icon);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTitle() {
		return title;
	}
	
	public static void textSelected(){
		TEXT.setActive(true);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(false);
	}
	public static void circleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(true);
		RECTANGLE.setActive(false);
		LINE.setActive(false);

	}
	public static void rectangleSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(true);
		LINE.setActive(false);

	}
	public static void lineSelected(){
		TEXT.setActive(false);
		CIRCLE.setActive(false);
		RECTANGLE.setActive(false);
		LINE.setActive(true);
	}

	public JButton getButton() {
		return button;
	}
}
