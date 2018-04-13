package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.libs.Animation;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB{
		
	private Textures tex;
	
	Animation anim;
	
	public Enemy(double x,double y,Textures tex) {
		super(x,y);
		this.tex = tex;
		
		anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
	}
	
	public void tick() {
		y += 1;
		
	anim.runAnimation();
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.enemy, (int)x, (int)y, null);
		//g.drawImage(tex.enemy[0], (int)x, (int)y, null);
		anim.drawAnimation(g, x, y, 0);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}
