package tank.abstractfactory;

import java.awt.Graphics;

import tank.Tank;

public abstract class BaseBullet {
	public abstract void paint(Graphics g);

	public abstract void collideWith(BaseTank tank);
}
