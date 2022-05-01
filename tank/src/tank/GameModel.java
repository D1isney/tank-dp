package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class GameModel {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
//	
//	public List<BaseBullet> bullets = new ArrayList<>();
//	public List<BaseTank> tanks = new ArrayList<>();
//	public List<BaseExplode> explodes = new ArrayList<>();
	
	private List<GameObject> objects = new ArrayList();

	public Tank getMainTank() {
		return myTank;
	}
	
	

	//创建一个门面模式
	public GameModel() {
		int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
		//初始化敌方坦克
		for(int i=0; i<initTankCount; i++) {
			add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
		}
	}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
//		g.drawString("敌人的数量:" + tanks.size(), 10, 80);
//		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);

		myTank.paint(g);
		for (int i = 0; i <objects.size(); i++) {
			objects.get(i).paint(g);
		}
		
		// for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
		// Bullet b = it.next();
		// if(!b.live) it.remove();
		// }

		// for(Bullet b : bullets) {
		// b.paint(g);
		// }
	}

}
