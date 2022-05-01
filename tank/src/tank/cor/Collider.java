package tank.cor;

import tank.GameObject;

//碰撞器
public interface Collider {
	//负责两个物体之间的碰撞
	boolean collide(GameObject o1,GameObject o2);
}
