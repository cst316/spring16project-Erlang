package net.sf.memoranda.ui;

public class TextField {
	String text;
	int x,y;
	public TextField(String text, int x, int y){
		this.text = text;
		this.x = x;
		this.y = y;
	}
	protected String getText() {
		return text;
	}
	protected void setText(String text) {
		this.text = text;
	}
	protected int getX() {
		return x;
	}
	protected void setX(int x) {
		this.x = x;
	}
	protected int getY() {
		return y;
	}
	protected void setY(int y) {
		this.y = y;
	}
	
}
