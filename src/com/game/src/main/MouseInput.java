package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}
	/**
	 * Obtiene entradas del mouse
	 */
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		/**
		 * public Rectangle playButton = new Rectangle(Game.WIDTH /2 + 120 ,150 ,100 ,50);
			public Rectangle helpButton = new Rectangle(Game.WIDTH /2 + 120 ,250 ,100 ,50);
			public Rectangle quitButton = new Rectangle(Game.WIDTH /2 + 120 ,350 ,100 ,50);
		 */
		//Play
		if(mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH /2 + 220)
		{
			if(my >= 150 && my <= 200)
			{
				Game.State = Game.STATE.GAME;
			}
		}
		//Quit
		if(mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH /2 + 220)
		{
			if(my >= 350 && my <= 400)
			{
				System.exit(1);
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
