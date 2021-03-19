import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {
	private int frameWidth = 700, frameHeight = 700, frameBorderWidth = 5;
	private boolean running;
	private Thread thread;
	private LoadedTextures LT;
	private List<Light> lights;

	public Game() {
		new GameFrame(this, frameWidth, frameHeight, frameBorderWidth);
		frameWidth -= frameBorderWidth * 2;
		frameHeight -= frameBorderWidth * 2;
		
		LT = new LoadedTextures();
		
		lights = new ArrayList<Light>();
		// change the colors here
		lights.add(new Light(-50, 100, 500, 500, 5, new Color(255, 0, 0)));
		lights.add(new Light(250, 100, 500, 500, 5, new Color(0, 0, 255)));
		lights.add(new Light(125, -50, 500, 500, 5, new Color(0, 255, 0)));
		lights.add(new Light(125, 250, 500, 500, 3, new Color(255, 255, 255)));
		lights.add(new Light(225, 225, 250, 250, 3, new Color(0, 0, 0)));
		
		running = true;
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
	}
	
	// Painting
	public void paint() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		////////////////////////////////////////////////
		
		// initial background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frameWidth, frameHeight);

		//g.drawImage(LT.getCat(), 0, 0, null);
		
		for(Light tempLight : lights) {
			tempLight.draw(g);
		}
		
		////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	// Loop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				// update all objects
				delta--;
			}
			paint();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	public void stop() { // ends the whole program
		running = false;
		try {
			thread.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	// Listeners
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) {
			stop();
		}
	}
	public void keyReleased(KeyEvent e) {}
	
	// Useless
	public static void main(String[] args) {
		new Game();
	}
	public void keyTyped(KeyEvent e) {}
}
