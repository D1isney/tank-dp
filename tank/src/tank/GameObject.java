package tank;

import java.awt.Graphics;
import java.io.Serializable;

//游戏的物体
public abstract class GameObject implements Serializable{
	//位置
	public int x;
	public int y;
	
	//画自己
	public abstract void paint(Graphics g);
	
	public abstract int getWidth();
	public abstract int getHeight();
}
