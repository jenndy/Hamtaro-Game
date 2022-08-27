import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame implements ActionListener, KeyListener{
	
	public static int width = 600;
	public static int height = 600; 
	public static int baseline = 580;
	public static int topline = 20;
	public static int leftline = 10;
	public static int rightline = 520;
	public Canvas canvas;
	public static Timer timer; 
	public static Lob lob = new Lob(); 
	public static Paddle paddle = new Paddle();
	public static Acorn r1acorn1, r2acorn1, r2acorn2, r3acorn1, r3acorn2, r3acorn3; // (5) 
	public static Countdown countdown;
	public static Lives lives;
	public static Score score;

	public static double time = 0;
	public BgMusicS bgMusicS;
	
	public Game() {
		setTitle("Hamster Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		score = new Score(); 
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		add(canvas, BorderLayout.CENTER);
		pack();

		timer = new Timer(50, this);
		
		canvas.addKeyListener(this); 
		
		setVisible(true);
		canvas.setFocusable(true);
		timer.start();
		
		lob = new Lob();
	
		paddle = new Paddle();
		
		r1acorn1 = new Acorn(); // Create in constructor (5) 
		r2acorn1 = new Acorn();
		r2acorn2 = new Acorn();
		r3acorn1 = new Acorn(); 
		r3acorn2 = new Acorn();
		r3acorn3 = new Acorn();
		
		countdown = new Countdown();
		lives = new Lives();
		
		bgMusicS = new BgMusicS();
	}
	
	protected class Canvas extends JPanel{
		public Canvas() {
			setFocusable(true);
			add(score.roundLab);
			add(score.scoreLab);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			
			switch(Score.round) {
			case 1:
				drawBG1(g);
				r1acorn1.draw(g); // Draw in paint component (5)  
				break;
			case 2:
				drawBG2(g);
				r2acorn1.draw(g);
				r2acorn2.draw(g);
				break;
			case 3:
				drawBG3(g);
				r3acorn1.draw(g);
				r3acorn2.draw(g);
				r3acorn3.draw(g);
				break;
			case 4:
				drawBG3(g);
				break;
			}
	
			lob.draw(g);
		
			lives.draw(g);

			paddle.draw(g); 
			
			g.setColor(Color.GREEN);
			countdown.draw(g);
		
			if (lob.gameOver == true) {
				score.draw(g);
			}	
		
			if (lob.win == true) {
				score.draw2(g);
			}	
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();	
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	
		lob.update();
		paddle.update();
		countdown.update();
		
		switch(Score.round) {
		case 1:
			r1acorn1.update(); // Update in actionPerformed (5) 
			break;
		case 2:
			r2acorn1.update();
			r2acorn2.update();
			break;
		case 3:
			r3acorn1.update();
			r3acorn2.update();
			r3acorn3.update();
			break;
		
		}
		time += 0.08; // * 

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Pressed");
		int keyCode = e.getKeyCode();
		switch( keyCode ) {
		case KeyEvent.VK_UP:
			//System.out.println("Up");
			paddle.vY -= 10; 
			break;
		case KeyEvent.VK_DOWN:
			//System.out.println("Down");
			paddle.vY += 10;
			break;
		case KeyEvent.VK_LEFT:
			//System.out.println("Left");
			paddle.vX -= 10;
			break;
		case KeyEvent.VK_RIGHT:
			//System.out.println("Right");
			paddle.vX += 10;
			//System.out.println(paddle.vX);
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("Released");
		paddle.vX = 0;
		paddle.vY = 0;
		
	}
	
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image bg1 = loadImage("src/graphics/bg1.png");
	Image bg2 = loadImage("src/graphics/bg2.png");
	Image bg3 = loadImage("src/graphics/bg3.png");
	
	public void drawBG1(Graphics g) {
		g.drawImage(bg1, 0, 0, null);
	}
	public void drawBG2(Graphics g) {
		g.drawImage(bg2, 0, 0, null);
	}
	public void drawBG3(Graphics g) {
		g.drawImage(bg3, 0, 0, null);
	}
	
	public static void reStart() {
		timer.restart();
		Score.score = 0;
		Score.round = 1;
	}
	
	public static void gameOver() { 
		timer.stop();
		time = 0; 
		
	}

}
