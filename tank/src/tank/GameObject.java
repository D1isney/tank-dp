package tank;

import java.awt.Graphics;

//游戏的物体
public abstract class GameObject {
	//位置
	int x, y;
	
	//画自己
	public abstract void paint(Graphics g);
}
