package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {

	//定义宽度和长度
	int w,h;
	
	//方块模型
	public Rectangle rect;
	
	public Wall(int x,int y,int w,int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		//初始化
		this.rect = new Rectangle(x,y,w,h);
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}
	
	@Override
	public int getWidth() {
		return w; 
	}

	@Override
	public int getHeight() {
		return h;
	}

}
