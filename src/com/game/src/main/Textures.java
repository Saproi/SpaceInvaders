package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	
	public BufferedImage player, missile , enemy;
	
	private SpriteSheet ss;
	/**
	 * 
	 * @param game instancia del juego
	 */
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
		
	}
	/**
	 * Obtiene imagenes del sprite_sheet.png
	 */
	private void getTextures() {
		player = ss.grabImage(1, 1, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3,1,32,32);
	}
}
