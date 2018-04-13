package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.libs.Animation;
import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{
	
	private Textures tex;
	private Game game;
	
	Animation anim;

	public Bullet(double x,double y, Textures tex,Game game) {
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5,tex.missile[0],tex.missile[1],tex.missile[2]);
	}
	
	public void tick() {
		y -= 4;
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.missile, (int)x, (int)y, null);
		//g.drawImage(tex.missile[0], (int)x, (int)y, null);
		anim.drawAnimation(g, x, y, 0);
	}
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
