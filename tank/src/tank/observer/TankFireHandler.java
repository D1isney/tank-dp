package tank.observer;


import tank.Tank;

public class TankFireHandler implements TankFireObserver {
	
	@Override
	public void actionOnFire(TankFireEvent e) {
		//拿到具体事件源
		Tank t = e.getSource();
		t.fire();
	}
}
