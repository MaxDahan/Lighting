import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public GameFrame(Game game, int frameWidth, int frameHeight, int frameBorderWidth) {
		requestFocus();
		setUndecorated(true);
		setSize(frameWidth, frameHeight);
		setLocationRelativeTo(null);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, frameBorderWidth));
		add(game);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
