import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public Main() {
		
		JFrame Jframe = new JFrame();
		
		GameWindow window = new GameWindow();
		
		Jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Jframe.setTitle("Snake Coding");
		
		Jframe.add(window);
		
		Jframe.pack();
		Jframe.setVisible(true);
		Jframe.setLocationRelativeTo(null);
		
	}
	public static void main(String[]args) {
		
		new Main();
	}
}
