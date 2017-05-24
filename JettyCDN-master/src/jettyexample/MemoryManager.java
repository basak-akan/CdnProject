package jettyexample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class MemoryManager {

	private static MemoryCache<String, BufferedImage> cache = new MemoryCache<String, BufferedImage>(1000);
	private static Hashtable<String, Integer> requestFreqTable = new Hashtable<String, Integer>();
	public static String TUMBNAILS = "./img/france.jpg";
	public MemoryManager() {
		// hash = new Hashtable<String, BufferedImage>(3,0);
	}

	public boolean isImgExist(String filename) {
		return cache.containsKey(filename);
	}

	public void addMemory(String filename, BufferedImage img) {
		if (cache.put(filename, img) != null) {
			requestFreqTable.put(filename, requestFreqTable.get(filename) + 1);
		} else
			requestFreqTable.put(filename, 0);

	}

	public boolean swapFile() {
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		bi = ImageIO.read(new File(TUMBNAILS));
		cache.put("asda", bi);
		cache.put("asd1a", bi);
		cache.put("as2da", bi);
		if (cache.put("as3da", bi) == null)
			System.out.println("asfa");
		System.out.println(cache.size());

	}

}
