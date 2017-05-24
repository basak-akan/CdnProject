package jettyexample;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class MemoryManager {

	Hashtable<String, BufferedImage> hash;

	public MemoryManager() {
		hash = new Hashtable<String, BufferedImage>();
	}

	public boolean isImgExist(String filename) {
		return hash.containsKey(filename);
	}

	public void addMemory(String filename, BufferedImage img) {
		hash.put(filename, img);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
