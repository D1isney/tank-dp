package tank.cor;

import tank.GameObject;
import tank.Tank;
import tank.Wall;
import tank.Bullet;

//只负责子弹和坦克的相撞
public class BulletWallCollider implements Collider {

	@Override
	//必须保证o1，o2只能是子弹或坦克
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Wall) {
			Bullet b1 = (Bullet)o1;
			Wall w1 = (Wall)o2;
			if(b1.rect.intersects(w1.rect)){
				b1.die();
			}
		}
		else if(o1 instanceof Wall && o2 instanceof Bullet) {
			return collide(o2, o1);
		}
		return true;
	}

}
