package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	private LinkedList<Entity> e = new LinkedList<Entity>();
	
	Entity ent;
	private Textures tex;
	Random r = new Random();
	
	public Controller(Textures tex) {
		this.tex = tex;
		for(int i = 0; i < 20; i++)
			addEntity(new Enemy(r.nextInt(640),10,tex));
	}
	
	public void tick() {
		for(int i = 0; i < e.size(); i++) {
				ent = e.get(i);
				
				ent.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < e.size(); i++) {
			ent = e.get(i);
			
			ent.render(g);
		}
	}
	
	public void addEntity(Entity block) {
		e.add(block);
	}
	public void removeEntity(Entity block) {
		e.remove(block);
	}
}
