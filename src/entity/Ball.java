package entity;

import java.awt.Color;
import java.awt.Graphics;

import main.Panel;

public class Ball {
	
	int x = 0;
	int y = 0;
	int dx = 1;
	int dy = 1;
	Color color = Color.BLACK;
	
	public Ball(int ix, int iy) {
		
		super();
		x = ix;
		y = iy;
		color = new Color(x % 256, y % 256, (x + y) % 256);
		dx = x % 16 + 1;
		dy = y % 16 + 1;
		
	}
	
	public void move () {
		
		if(x < 0 || x >= Panel.WIDTH)
			dx = - dx;
		
		if(y < 0 || y >= Panel.HEIGHT)
			dy = - dy;
		
		x += dx;
		y += dy;
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval(x, y, Panel.diameter, Panel.diameter);
		
	}
	
}
