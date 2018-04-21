package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{
	
	private Textures tex;
	private Game game;
	
	/**
	 * 
	 * @param x posicion en eje x
	 * @param y posicion en eje y
	 * @param tex imagen asociada a bala
	 * @param game instancia del juego
	 */
	public Bullet(double x,double y, Textures tex,Game game) {
		super(x,y);
		this.tex = tex;
		this.game = game;
		
	}
	/**
	 * Actualiza pantalla
	 */
	public void tick() {
		y -= 4;
	}
	/**
	 * Muestra en pantalla
	 */
	public void render(Graphics g) {
		g.drawImage(tex.missile, (int)x, (int)y, null);
	}
	/**
	 * Obtiene limites del rectangulo
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}

}
