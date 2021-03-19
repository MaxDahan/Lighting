import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Light {
	
	public Light(int x, int y, int maxWidth, int maxHeight, int maxOpacity, Color color) {
		this.currentColor = color;
		setUp(x, y, maxWidth, maxHeight, maxOpacity);
	}
	public Light(int x, int y, int maxWidth, int maxHeight, int maxOpacity, List<Color> colors) {
		this.colors = colors;
		setUp(x, y, maxWidth, maxHeight, maxOpacity);
	}
	public void setUp(int x, int y, int maxWidth, int maxHeight, int maxOpacity) {
		this.x = x;
		this.y = y;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.maxOpacity = maxOpacity;
		
		totalFrames = 100;
		maxOpacity = 10;
		
		widthAddition = this.maxWidth/totalFrames;
		heightAddition = this.maxHeight/totalFrames;
		
		opacityReduction = currentOpacity/totalFrames;
	}
	
	public void draw(Graphics2D g) {
		currentOpacity = maxOpacity;
		currentWidth = widthAddition;
		currentHeight = heightAddition;
		
		for(int i = 0; i < totalFrames; i++) {
			currentX = x;
			currentY = y;
			
			currentOpacity -= opacityReduction;
			
			currentWidth += widthAddition;
			currentHeight += heightAddition;
			
			currentX += maxWidth/2 - currentWidth/2;
			currentY += maxHeight/2 - currentHeight/2;
			
			if(colors != null) alternateColors();
			
			g.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), currentOpacity));
			g.fillOval(currentX, currentY, currentWidth, currentHeight);
		}
	}
	public void alternateColors() {
		if(colorAlternation >= colors.size() - 1) {
			colorAlternation = 0;
		} else {
			colorAlternation++;
		}
		currentColor = colors.get(colorAlternation);
	}
	

	// Fields
	private int x, y, currentX, currentY; // position
	private int maxWidth, maxHeight, currentWidth, currentHeight, widthAddition, heightAddition; // size
	private int opacityReduction, currentOpacity, maxOpacity; // opacity
	private int totalFrames, colorAlternation; // miscellaneous
	
	private Color currentColor;
	private List<Color> colors;
}
