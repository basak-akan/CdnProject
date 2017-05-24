package jettyexample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HarddiskCache {

	private String baseLocation = ".\\img\\";

	public HarddiskCache(String filename) {

	}

	public boolean isImgExist(String filename) {
		return new File(baseLocation + filename).exists();
	}

	public BufferedImage getImage(String filename) throws IOException {
		return ImageIO.read(new File(baseLocation + filename));
	}

	public void saveImage(String filename, BufferedImage img) throws IOException {
		ImageIO.write(img, "jpg", new File(baseLocation + filename));
	}

}
