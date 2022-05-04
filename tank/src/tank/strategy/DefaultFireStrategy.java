package tank.strategy;

import decorator.RectDecorator;
import decorator.TailDecorator;
import tank.Audio;
import tank.Bullet;
import tank.GameModel;
import tank.Group;
import tank.Tank;

public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		new Bullet(bX, bY, t.dir, t.group);
		//使用装饰器
//		GameModel.getInstance().add(
//				new RectDecorator(
//						new TailDecorator(
//						new Bullet(bX,bY,t.dir,t.group))));
		
		if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

}
