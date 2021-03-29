package bsu.rfe.java.group8.lab6.Gulakov.varC3;

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList; 
import javax.swing.JPanel; 
import javax.swing.Timer; 
 
@SuppressWarnings("serial") 
public class Field extends JPanel { 
	
	private double dragOffsetX;
	private double dragOffsetY;
	
 
	private boolean paused; 
	private boolean paused1;
	boolean isDragged;
	
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	
	
	
	private Timer repaintTimer = new Timer(10, new ActionListener() { 
		public void actionPerformed(ActionEvent ev) { 
			repaint(); 
			} 
		});    
	 
	public Field() { 
		 
		setBackground(Color.WHITE); 
		repaintTimer.start(); 
		} 
	
	
	public void paintComponent(Graphics g) { 
		
		super.paintComponent(g); 
		Graphics2D canvas = (Graphics2D) g; 
		 
		for (BouncingBall ball: balls) { 
			ball.paint(canvas); 			
			} 
		
		Graphics2D path = (Graphics2D) g;
		rectangle.paint(path);
		} 
	

	public void addBall() { 
		 
		balls.add(new BouncingBall(this));
		addMouseMotionListener(new MouseMotionHandler());
		addMouseListener(new MouseHandler());
		} 
	
	
	public  void pause() { 
		 
		paused = true; 
		} 
	
	public  void pause1() { 
		
		paused1 = true; 
		} 
	
	public synchronized void resume() { 
		
		paused = false; 
		paused1 = false; 
		notifyAll(); 
		} 

	public boolean Proverka(){
		boolean mark = false;
		for (BouncingBall ball: balls) { 
			if(ball.getRadius() < 10)
			{
				mark =  true;
				break;
			}
			else
				mark =  false;
			} 
		return mark;
	} 
	public synchronized void canMove(BouncingBall ball) throws 
	InterruptedException { 
		if (paused) { 
			if (ball.getRadius() < 10)
				wait();
			} 
		if(paused1)
			wait();
		} 
	
	public class MouseHandler extends MouseAdapter{
		public MouseHandler() {
			
		}
		
		public void mouseClicked(MouseEvent e) {
		}
		
		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		@SuppressWarnings("deprecation")
		public void mousePressed(MouseEvent e) {
			if ((e.getModifiers() & MouseEvent.BUTTON2_MASK) == 0)
				if(rectangle.contains(e.getX(), e.getY())){
					isDragged = true;
					dragOffsetX = e.getX()-rectangle.getX();
					dragOffsetY = e.getY()-rectangle.getY();
				}
				else 
					isDragged = false;
			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			}
		}
		
	
	public class MouseMotionHandler implements MouseMotionListener{
				
		public void mouseDragged(MouseEvent e){
			if (isDragged){
				rectangle.setPos(e.getX()-dragOffsetX, e.getY()-dragOffsetY);
			}
		}
		
		public void mouseMoved(MouseEvent e){
		} 
	}
} 