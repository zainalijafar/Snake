import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Pieces {

	private int x; 
	private int y; 
	private int Width;
	private int Height;
	
	public Apple(int x, int y, int pieceSize) {
	
		super(x,y,pieceSize);
		
		this.x = x; 
		this.y = y; 
		this.Width = pieceSize; 
		this.Height = pieceSize;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(x * this.Width , y * this.Height , this.Width , this.Height);
	}
}