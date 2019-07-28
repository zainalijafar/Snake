import java.awt.Color;
import java.awt.Graphics;

public class Pieces {

	private int xCoord;
	private int yCoord; 
	private int Width; 
	private int Height;
	
	public Pieces(int xCoord,int yCoord,int pieceSize) {
		
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		Width = pieceSize; 
		Height = pieceSize;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillRect(this.xCoord * Width ,this.yCoord * Height,Width,Height);
		
	}
	
	public void tick() {
		
	}
	
	public void setXCoord(int x) {
		
		this.xCoord = x;
	}
	
	public void setYCoord(int y) {
		
		this.yCoord = y;
	}
	
	public int getXCoord() {
		
		return this.xCoord;
	}
	
	public int getYCoord() {
		
		return this.yCoord;
	}
}