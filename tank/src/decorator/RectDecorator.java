package decorator;

import java.awt.Color;
import java.awt.Graphics;

import tank.GameObject;

public class RectDecorator extends GoDecorator {

	public RectDecorator(GameObject go) {
		super(go);
	}

	@Override
	public void paint(Graphics g) {
		go.paint(g);
		
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawRect(super.go.x,super.go.y, super.go.getWidth()+2, getHeight()+2);
		g.setColor(c);
	}
	
	@Override
	public int getWidth() {
		return super.go.getWidth(); 
	}

	@Override
	public int getHeight() {
		return super.go.getHeight();
	}

}
