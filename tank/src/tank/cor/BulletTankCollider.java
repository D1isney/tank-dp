package tank.cor;

import tank.GameObject;
import tank.Tank;
import tank.Bullet;
import tank.Explode;

//只负责子弹和坦克的相撞
public class BulletTankCollider implements Collider {

	@Override
	//必须保证o1，o2只能是子弹或坦克
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet)o1;
			Tank t = (Tank)o2;
//			if(b.collideWith(t))return false;
			if(b.group == t.getGroup())return true;
			
			if(b.rect.intersects(t.rect)) {
				t.die();b.die();
				int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
				int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
				new Explode(eX, eY);
				return false;
			}

		}
		else if(o1 instanceof Tank && o2 instanceof Bullet) {
			//如果o1是坦克，o2是子弹 就重新调用一次他们
			//把他们反过来
			return collide(o2,o1);
		}
		return true;
	}
}
