package tank.cor;

import tank.GameObject;
import tank.Tank;
import tank.Wall;
import tank.Bullet;

//只负责子弹和坦克的相撞
public class TankWallCollider implements Collider {

	@Override
	//必须保证o1，o2只能是子弹或坦克
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Wall) {
			Tank t1 = (Tank)o1;
			Wall w1 = (Wall)o2;
			if(t1.rect.intersects(w1.rect)){
				t1.back();
			}
		}
		else if(o1 instanceof Wall && o2 instanceof Bullet) {
			return collide(o2, o1);
		}
		return true;
	}
}
