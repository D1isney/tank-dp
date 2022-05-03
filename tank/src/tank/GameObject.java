package tank;

import java.awt.Graphics;

//游戏的物体
public abstract class GameObject {
	//位置
	public int x;
	public int y;
	
	//画自己
	public abstract void paint(Graphics g);
	
	public abstract int getWidth();
	public abstract int getHeight();
}
