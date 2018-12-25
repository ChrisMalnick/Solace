package main;

import javax.swing.JPanel;

import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.util.ArrayList;

import entity.Ball;

public class Panel extends JPanel implements Runnable, MouseListener, MouseWheelListener {
	
	private static final long serialVersionUID = 0L;
	
	/*Dimensions*/
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	/*Thread*/
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	/*Entities*/
	private ArrayList<Ball> entityList = new ArrayList<Ball>();
	public static int diameter = 16;
	
	public Panel() {
		
		super();
		setLayout(null);
		setFocusable(true);
		requestFocus();
		
	}
	
	public void addNotify() {
		
		super.addNotify();
		
		if(thread == null) {
			
			thread = new Thread(this);
			addMouseListener(this);
			addMouseWheelListener(this);
			thread.start();
			
		}
		
	}
	
	private void initialize() {running = true;}
	
	public void run() {
		
		initialize();
		
		long start;
		long elapsed;
		long wait;
		
		/*Loop*/
		while(running) {
			
			start = System.nanoTime();
			
			update();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0)
				wait = 5;
			
			try {
				
				Thread.sleep(wait);
				
			}
			
			catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	private void update() {
		
		targetTime = 1000 / FPS;
		
		for(Ball b : entityList)
			b.move();
		
	}
	
	public synchronized void paintChildren(Graphics g) {
		
		//g.drawLine(WIDTH + diameter, 0, WIDTH + diameter, HEIGHT + diameter);
		g.drawLine(0, HEIGHT + diameter, WIDTH + diameter, HEIGHT + diameter);
		
		g.drawRect(8, HEIGHT + 23, 80, 20);
		g.drawRect(96, HEIGHT + 23, 80, 20);
		
		g.drawString("Clear", 32, HEIGHT + 38);
		g.drawString("Undo", 122, HEIGHT + 38);
		g.drawString("FPS: " + FPS, 188, HEIGHT + 38);
		g.drawString("Count: " + entityList.size(), 248, HEIGHT + 38);
		
		for(Ball b : entityList)
			b.draw(g);
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(/*e.getX() < WIDTH + diameter && */e.getY() < HEIGHT + diameter) {
			
			Ball b = new Ball(e.getX(), e.getY());
			entityList.add(b);
		
		}
		
		if(e.getX() >= 8 && e.getX() <= 88 && e.getY() >= HEIGHT + 23 && e.getY() <= HEIGHT + 43)
			entityList.clear();
		
		if(e.getX() >= 96 && e.getX() <= 176 && e.getY() >= HEIGHT + 23 && e.getY() <= HEIGHT + 43)
			if(entityList.size() > 0)
				entityList.remove(entityList.size() - 1);
		
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if(FPS == 24 && e.getWheelRotation() > 0 || FPS == 240 && e.getWheelRotation() < 0)
			return;
		
		FPS = FPS - e.getWheelRotation();
		
	}
	
}
