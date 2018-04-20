package com.game.src.main;

import java.awt.Rectangle;

public class GameObject {
	
	public double x,y;
	/**
	 * 
	 * @param x posicion en eje x
	 * @param posicion en eje y
	 */
	public GameObject(double x,double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * 
	 * @param width largo del rectangulo
	 * @param height alto del rectangulo
	 * @return
	 */
	public Rectangle getBopunds(int width, int height) {
		return new Rectangle((int)x,(int)y,width,height);
	}

}
