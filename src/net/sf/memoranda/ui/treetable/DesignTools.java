package net.sf.memoranda.ui.treetable;

public enum DesignTools {
	TEXT("Text", false),
	RECTANGLE("Rectangle",false),
	CIRCLE("Circle",false),
	LINE("Line", false);
	
	private String title;
	private boolean active;
	
	DesignTools(String title, boolean status){
		this.title = title;
		this.active = status;
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
	

}
