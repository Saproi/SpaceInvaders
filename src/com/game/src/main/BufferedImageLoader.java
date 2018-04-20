package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	/**
	 * 
	 * @param path ruta imagen
	 * @return imagen
	 * @throws IOException retiene error por si no encuentra imagen
	 */
	public BufferedImage loadImage(String path) throws IOException {
		
		image = ImageIO.read(getClass().getResource(path));
		return image;
		
	}
}
