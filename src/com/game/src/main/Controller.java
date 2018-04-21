package com.game.src.main;

import java.awt.Graphics;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Controller {
	private Lista<EntityA> ea = new Lista<EntityA>();
	private Lista<EntityB> eb = new Lista<EntityB>();

	
	EntityA enta;
	EntityB entb;
	
	private Textures tex;
	Random r = new Random();
	private Game game;
	/**
	 * 
	 * @param tex imagen
	 * @param game instancia del juego
	 */
	public Controller(Textures tex,Game game) {
		this.tex = tex;
		this.game = game;
	}
	/**
	 * 
	 * @param enemy_count cuantos enemigos hay
	 */
	public void createEnemy(int enemy_count) {
		for(int i = 0; i < enemy_count; i++)
			addEntity(new Enemy(r.nextInt(640),-10,tex,this,game));
	}
	/**
	 * Actualiza pantalla
	 */
	public void tick() {
		for(int i = 0; i < ea.capacidad(); i++) {
				enta = ea.getDato(i);
				
				enta.tick();
		}
		for(int i = 0; i < eb.capacidad(); i++) {
			entb = eb.getDato(i);
			
			entb.tick();
	}
	}
	/**
	 * Muestra en pantalla
	 * @param g 
	 */
	public void render(Graphics g) {
		for(int i = 0; i < ea.capacidad(); i++) {
			enta = ea.getDato(i);
			
			enta.render(g);
		}
		for(int i = 0; i < eb.capacidad(); i++) {
			entb = eb.getDato(i);
			
			entb.render(g);
		}
	}
	
	public void addEntity(EntityA block) {
		ea.agregar(block);
	}
	public void removeEntity(EntityA block) {
		ea.eliminarDato(block);
	}
	public void addEntity(EntityB block) {
		eb.agregar(block);
	}
	public void removeEntity(EntityB block) {
		eb.eliminarDato(block);
	}
	public Lista<EntityA> getEntityA(){
		return ea;
	}
	public Lista<EntityB> getEntityB(){
		return eb;
	}
}
