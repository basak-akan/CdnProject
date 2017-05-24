package jettyexample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HarddiskManager {

	private String baseLocation = ".\\img\\";
	private String filename;

	public HarddiskManager(String filename) {
		this.filename = filename;
	}

	public boolean isImgExist(String filename) {
		return new File(baseLocation + filename).exists();
	}

	public BufferedImage getImage(String filename) throws IOException {
		return ImageIO.read(new File(baseLocation + filename));
	}

	public void saveImage(String filename) {
		new File(baseLocation + filename);
	}

}
