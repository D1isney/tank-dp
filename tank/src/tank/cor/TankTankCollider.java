package tank.cor;

import tank.GameObject;
import tank.Tank;
import tank.Bullet;

//只负责子弹和坦克的相撞
public class TankTankCollider implements Collider {

	@Override
	//必须保证o1，o2只能是子弹或坦克
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Tank) {
			Tank t1 = (Tank)o1;
			Tank t2 = (Tank)o2;
			if(t1.getRect().intersects(t2.getRect())){
				//两个坦克相撞 让坦克回到原来的位置
				t1.back();
				t2.back();
			}
		}
		return true;
	}
}
