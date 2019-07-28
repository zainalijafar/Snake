import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameWindow extends JPanel implements Runnable,KeyListener {

	private static final long serialVersionUID = 1L;
	private static final int Width = 500; 
	private static final int Height = 500; 
	
	private Thread thread;
	private boolean running; 
	
	private Pieces piece;
	private ArrayList<Pieces> snakePieces;
	private int xCoord = 10;
	private int yCoord = 10; 
	private int size = 15;
	
	private boolean right,left,up,down;
	
	private Apple apple; 
	private ArrayList<Apple> applePieces;
	
	Random rand;
	
	private int ticks = 0;
	
	public GameWindow() {
		
		setFocusable(true);
		setPreferredSize(new Dimension(Width,Height));
		this.snakePieces = new ArrayList<>();
		this.applePieces = new ArrayList<>();
		
		rand = new Random();
		
		addKeyListener(this);
		start();
		
	}
	
	public void start() {
		running = true; 
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false; 
		
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
		if(this.snakePieces.size() == 0) {
			this.piece = new Pieces(this.xCoord,this.yCoord,10);
			this.snakePieces.add(this.piece);
		}
		ticks++;
		
		if(ticks>250000) {
			
			if(right) {
				this.xCoord++;
			}
			if(left) {
				this.xCoord--;
			}
			if(up) {
				this.yCoord--;
			}
			if(down) {
				this.yCoord++;
			}
			
			ticks = 0;
			
			piece = new Pieces(this.xCoord,this.yCoord,10);
			this.snakePieces.add(this.piece);
			
			if(this.snakePieces.size()>size) {
				this.snakePieces.remove(0);
			}
		}
		
		if(applePieces.size()== 0) {
			
			int xCoord = rand.nextInt(49);
			int yCoord = rand.nextInt(49);
			this.apple = new Apple(xCoord,yCoord,10);
			this.applePieces.add(this.apple);
		}
		
		for(int i=0; i<applePieces.size(); i++) {
			
			if(xCoord == applePieces.get(i).getXCoord() && yCoord == applePieces.get(i).getYCoord()) {
				size++;
				applePieces.remove(i);
				i++;
			}
		}
		
		for(int i=0; i<snakePieces.size(); i++) {
			
			if((xCoord == snakePieces.get(i).getXCoord() && yCoord == snakePieces.get(i).getYCoord()) && xCoord!=10 && i!=(snakePieces.size()-1)) {
				
				System.out.println("Game Over");
				stop();
			}
		}
		
		if(xCoord<0 || xCoord> 49 || yCoord <0 || yCoord >49) {
			System.out.println("Game Over");
			stop();
		} 
	}
	
	public void paint(Graphics g) {
		
		g.clearRect(0,0,Width,Height);
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Width,Height);
		
		for(int i=0; i<Width/10; i++) {
			g.drawLine(i*10,0,i*10,Height);
		}
		for(int i=0; i<Height/10; i++) {
			g.drawLine(0,i*10,Height,i*10);
		}
		for(int i=0; i<snakePieces.size(); i++) {			
			this.snakePieces.get(i).draw(g);
		}
		for(int i=0; i<applePieces.size(); i++) {
			this.applePieces.get(i).draw(g);
		}
	}
	
	public void run() {
		
		while(running) {
			tick();
			repaint();
		}
	}

	public void keyPressed(KeyEvent ke) {
		
		int key = ke.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT && !left) {
			
			right = true;
			down = false; 
			up = false;
		}
		
		if(key == KeyEvent.VK_LEFT && !right) {
			 
			left = true; 
			down = false; 
			up = false;
		}
		
		if(key == KeyEvent.VK_UP && !down) {
			
			right = false; 
			left = false; 
			up = true; 
		}
		
		if(key == KeyEvent.VK_DOWN && !up) {
			
			right = false; 
			left = false;
			down = true;
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {
	
	}
}