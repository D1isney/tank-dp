package decorator;

import java.awt.Color;
import java.awt.Graphics;

import tank.GameObject;

public class TailDecorator extends GoDecorator {

	public TailDecorator(GameObject go) {
		super(go);
	}

	@Override
	public void paint(Graphics g) {
		go.paint(g);
		
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawLine(super.go.x,super.go.y , super.go.x + getWidth(),super.go.y + getHeight());
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
