package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import tank.cor.ColliderChain;



public class GameModel{
	//饿汉式-单例
	private static final GameModel INSTANCE = new GameModel();

	//静态语句块
	static {
		INSTANCE.init();
	}
	
	Tank myTank;
	
//	public List<BaseBullet> bullets = new ArrayList<>();
//	public List<BaseTank> tanks = new ArrayList<>();
//	public List<BaseExplode> explodes = new ArrayList<>();
	
//	Collider colider1 = new BulletTankCollider();
//	Collider colider2 = new TankTankCollider();
	
	ColliderChain chain = new ColliderChain();
	
	private List<GameObject> objects = new ArrayList<GameObject>();

	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	
	/**
	 * 大管家
	 */
	//创建一个门面模式
	public GameModel() {}
	
	private void init(){
		//初始化主战坦克
		myTank = new Tank(200,400,Dir.DOWN,Group.GOOD);
		int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
		//初始化敌方坦克
		for(int i=0; i<initTankCount; i++) {
			new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD);
		}
		
		//初始化墙
		add(new Wall(150,150,200,50));
		add(new Wall(550,150,200,50));
		add(new Wall(300,300,50,200));
		add(new Wall(550,300,50,200));
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
		
		//碰撞逻辑
		for(int i = 0;i<objects.size();i++) {
			for(int j = i+1;j<objects.size();j++) {
				//把i,j每个都拿出来碰撞
				//互相之间看看能不能相互碰撞
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
//				也要判断类型
//				o1.collideWith(o2);
				
				//碰撞器
//				colider1.collider(o1, o2);
//				colider2.collider(o1, o2);
				
				/**
				 * 第一种写法
				 * 遍历for
				 */
				
				/**
				 * 第二种写法
				 * 让链条自己去撞
				 */
				chain.collide(o1,o2);
			}
		}
		
		// for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
		// Bullet b = it.next();
		// if(!b.live) it.remove();
		// }

		// for(Bullet b : bullets) {
		// b.paint(g);
		// }
	}
	public Tank getMainTank() {
		return myTank;
	}
	
	public void save() {
		File f = new File("D:/Java-tank/tank.data");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			//不能实现序列化
			oos.writeObject(myTank);
			oos.writeObject(objects);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		File f2 = new File("D:/Java-tank/tank.data");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f2));
			//先写先读
			myTank = (Tank)ois.readObject();
			objects = (List)ois.readObject();
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
