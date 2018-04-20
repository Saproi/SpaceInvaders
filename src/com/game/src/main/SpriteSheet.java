package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	/**
	 * 
	 * @param image imagen 
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	/**
	 * 
	 * @param col columna
	 * @param row fila
	 * @param width ancho
	 * @param height alto
	 * @return imagen
	 */
	public BufferedImage grabImage(int col, int row, int width ,int height) {
		
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}

}
