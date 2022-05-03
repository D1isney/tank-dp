package decorator;

import java.awt.Graphics;

import tank.GameObject;

public abstract class GoDecorator extends GameObject {

	GameObject go;
	
	public GoDecorator(GameObject go) {

		this.go = go;
	}
	
	@Override
	public abstract void paint(Graphics g);
	
}
