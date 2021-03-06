package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import tank.observer.TankFireEvent;
import tank.observer.TankFireHandler;
import tank.observer.TankFireObserver;
import tank.strategy.DefaultFireStrategy;
import tank.strategy.FireStrategy;
import tank.strategy.FourDirFireStrategy;

public class Tank extends GameObject {
	
	private static final int SPEED = 2;
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	
	public Rectangle rect = new Rectangle();
	
	private Random random = new Random();

	//记录上一次的变量
	public int oldx,oldy;
	
	public Dir dir = Dir.DOWN;

	public Group group = Group.BAD;

	private boolean moving = true;
	
	private boolean living = true;
	
	FireStrategy fs;
	
	
//	public GameModel gm;
	
	public Tank(int x, int y, Dir dir, Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
//		this.gm = gm;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		
		if(group == Group.GOOD) {
//			fs = new FourDirFireStrategy();
			String goodFSName = (String)PropertyMgr.get("goodFS");
			try {
				fs = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		} else {
//			fs = new DefaultFireStrategy();
			String badFSName = (String)PropertyMgr.get("badFS");
			try {
				fs = (FireStrategy)Class.forName(badFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		GameModel.getInstance().add(this);
	}
	
	
	private void boundsCheck() {
		if (this.x < 2) x = 2;
		if (this.y < 28) y = 28;
		if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
	}
	
	public void die() {
		this.living = false;
	}
	
	public void fire() {
		fs.fire(this);
		
//		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
//		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
//		
//		Dir[] dirs = Dir.values();
//		for(Dir dir : dirs) {
//			gm.gf.createBullet(bX, bY, dir, group, gm);
//		}
		
		
		
//		int bx = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
//		int by = this.y + RectTank.HEIGHT/2 - Bullet.WIDTH/2;
//		
//		Dir []dirs = Dir.values();
//		for(Dir dir : dirs) {
//			gm.gf.createBullet(bx, by, dir, group, gm);
//		}
		
		if(group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}
	
	public Dir getDir() {
		return dir;
	}
	
	
	public Group getGroup() {
		return group;
	}
	public Rectangle getRect() {
		return rect;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}

	private void move() {
		//记录移动前的位置
		oldx = x;
		oldy = y;
		
		if(!moving) return ;
		
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		if(this.group == Group.BAD && random.nextInt(100) > 95) 
			this.fire();
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
		
		boundsCheck();
		//update rect
		rect.x = this.x;
		rect.y = this.y;
	}
	
	public void paint(Graphics g) {
		if(!living) GameModel.getInstance().remove(this);
		
		switch(dir) {
		case LEFT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		}
	
		move();
	
	}
	
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int getWidth() {
		return WIDTH; 
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	public void back() {
		this.x = oldx;
		this.y = oldy;
	}


	
	//观察者模式
	//transizent 透明的
	private List<TankFireObserver> fireObserver = Arrays.asList(new TankFireHandler());
	public void handlefire() {
		TankFireEvent event = new TankFireEvent(this);
		for(TankFireObserver o :fireObserver) {
			o.actionOnFire(event);
		}
	}
	
}
