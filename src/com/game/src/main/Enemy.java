package com.game.src.main;

import java.awt.Graphics;

public class Enemy implements Entity{
	
	private double x, y;
	
	private Textures tex;
	
	public Enemy(double x,double y,Textures tex) {
		this.x = x;
		this.y = y;
		this.tex = tex;
	}
	
	public void tick() {
		y += 1;
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}
