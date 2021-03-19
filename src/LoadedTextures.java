import java.awt.image.BufferedImage;

public class LoadedTextures {
	private BufferedImageLoader BIL = new BufferedImageLoader();
	private String dataPath = "data/";
	private BufferedImage cat = BIL.loadImage(dataPath + "cat.jpg");
	
	public BufferedImage getCat() {
		return cat;
	}
}
