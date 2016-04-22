package net.sf.memoranda.ui;

public class TextField {
	String text;
	int x,y;
	public TextField(String text, int x, int y){
		this.text = text;
		this.x = x;
		this.y = y;
	}
	public String getText() {
		return text;
	}
	protected void setText(String text) {
		this.text = text;
	}
	public int getX() {
		return x;
	}
	protected void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	protected void setY(int y) {
		this.y = y;
	}
	
}
