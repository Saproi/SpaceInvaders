package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12* 9;
	public static final int SCALE = 2;
	public static final String TITLE = "Space Invaders";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private boolean is_shooting = false;
	private boolean left = false;
	private boolean right = false;
	
	private int enemy_count = 9;
	private int enemy_killed = 0;

	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	
	public Lista<EntityA> ea;
	public Lista<EntityB> eb;
	
	public static int HEALTH = 100 * 2;
	/**
	 * 
	 * @author Saproi
	 *
	 */
	public static enum STATE{
		MENU,
		GAME
	};
	public static STATE State = STATE.MENU;
	
	/**
	 * Inicia todo lo necesario para que funcione el juego
	 */
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		
		
		c = new Controller(tex,this);
		p = new Player(320, 440, tex,this,c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		c.createEnemy(enemy_count);
	
	}
	
	/**
	 * Thread de comienzo
	 */
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Thread para finalizar
	 */
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	/**
	 * Corazon del juego
	 */
	public void run() {
		init();
		long  lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks,Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	/**
	 * Actualiza la pantalla
	 */
	private void tick() {
		if(State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		
		if(enemy_killed == 9){
			enemy_count = 9;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
	}
	/**
	 * Muestra en pantalla los elementos
	 */
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs== null) {
			
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
		
		g.drawImage(background, 0, 0, this);
		if(State == STATE.GAME) {
			p.render(g);
			c.render(g);
			
			g.setColor(Color.gray);
			g.fillRect(5,5,200,50);
			
			g.setColor(Color.green);
			g.fillRect(5,5,HEALTH,50);
			
			g.setColor(Color.white);
			g.drawRect(5,5,200,50);
		
		}else if(State == STATE.MENU) {
			menu.render(g);
		}
		
		
		
		//////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	/**
	 * Detecta las entradas del teclado
	 * @param e es la entrada del teclado
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
			if(key == KeyEvent.VK_RIGHT) {
				right = true;
				p.setVelX(5);
				
			}else if(key == KeyEvent.VK_LEFT) {
				left = true;
				p.setVelX(-5);
				
			}else if(key == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(),p.getY(),tex, this));
			}
		}
		
	}
	/**
	 * Detecta cuando se suelta una tecla
	 * @param e entrada del teclado
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
			if(key == KeyEvent.VK_RIGHT) {
				right = false;
				p.setVelX(0);
				
			}else if(key == KeyEvent.VK_LEFT) {
				left = false;
				p.setVelX(0);
				
			}else if(key == KeyEvent.VK_SPACE) {
				is_shooting = false;
			}
		}
	}

	/**
	 * Inicia la instancia Game
	 */
	public static void main(String args[]) {
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public int getEnemy_count() {
		return enemy_count;
	}


	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}


	public int getEnemy_killed() {
		return enemy_killed;
	}


	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	public boolean getRight(){
		return right;
	}
	public boolean getLeft(){
		return left;
	}



}
