package tank.cor;

import java.util.LinkedList;
import java.util.List;

import tank.GameObject;

/**
 * 高内聚 低耦合
 */
//责任链
public class ColliderChain implements Collider{
											//链表
											//不需要动态修改长度
											//不需要下标访问
	private List<Collider> colliders = new LinkedList<>();
	
	/**
	 * 对list做了一个封装
	 */ 
	
	//责任链 逻辑链
	public ColliderChain() {
		add(new BulletTankCollider());
		add(new TankTankCollider());
		add(new BulletWallCollider());
		add(new TankWallCollider());
	}
	
	public void add(Collider c) {
		colliders.add(c);
	}

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		for(int i=0;i<colliders.size();i++) {
			if(!colliders.get(i).collide(o1, o2)) return false;
		}
		return true;
	}


	
	
	
}
