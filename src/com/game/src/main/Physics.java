package com.game.src.main;

import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics {
	/**
	 * 
	 * @param enta instancia EntityA
	 * @param entb instancia EntityB
	 * @return
	 */
	public static boolean Collision(EntityA enta,EntityB entb) {
		
		if(enta.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param entb EntityB
	 * @param enta EntityA
	 * @return boolean si interseca o no
	 */
	public static boolean Collision(EntityB entb,EntityA enta) {
		if(entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}

}
