// Animation for the hamster 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lob {
	
	// Dimensions of hamster 
	public int hamWidth = 80;
	public int hamHeight = 82;
	
	// Starting location of hamster 
	public int x = Game.width/2 - hamWidth/2;
	public int y = Game.baseline - 50 - hamHeight/2;
	
	// Initial coefficients 
	public int vX = 0;
	public int vY = 0;
	public int coX = 1, coY = 1;
	
	// Game booleans that decide which end picture to draw 
	public boolean gameOver = false;
	public boolean win = false;

	// Sound effects 
	public Sounds sounds;
	public Sounds.BumpS bumpS;
	public Sounds.TransitionS transitionS;
	public Sounds.FallS fallS;
	public Sounds.WinS winS;
	public Sounds.LoseS loseS;
	
	// Reads the image file 
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image hamster = loadImage("src/graphics/hamster.png");
	
	// Draws the hamster at the initial position 
	public void draw(Graphics g) {
		g.drawImage(hamster,  x,  y,  null);
	}

	//calculates the projectile motion 
	public void update() {
	
		// Equations for motion in x and y directions 
		int a = (int)(Game.time*(20*Math.cos(Math.toRadians(60))));	
		int b = (int)(20*Math.sin(Math.toRadians(60))*Game.time-0.5*9.8*Math.pow(Game.time, 2));
		
		// Countown finishes = player wins the round 
		if (Game.countdown.width2  <= 0) {
			
			// +5 points for winning round and goes to next round 
			Score.score += 5; 
			Score.round += 1; 
			
			// Finishes round 3 = player wins the game 
			if (Score.round > 3) {
				Game.gameOver();
				win = true;
				winS = new WinS();
			
			}else {
				// Transitions to new round: set round label, reset countdown and life 
				Score.roundLab.setText("Round: " + Score.round + " |");
				Game.countdown.width2 = 200;
				Lives.lifeNum = 3;
				transitionS = new Sounds.TransitionS();
			}
			
			// Sets score label 
			Score.scoreLab.setText("Score: " + Score.score );
		}
	
		// Hamster falls out of screen: -2 points, -1 life, resets y-coordinate of hamster 
		if (y >= Game.baseline) {
			
			Score.score -= 2; 
			Lives.lifeNum -= 1; 
			y = 50;
			Game.time = 0;
			fallS = new Sounds.FallS();
			
			// Hamster falls out of screen and no more lives = game over 
			if ( Lives.lifeNum == 0 ) {
				Game.gameOver();	
				//draw game over 
				gameOver = true;
				loseS = new Sounds.LoseS();
			}
			
			// Sets score label 
			Score.scoreLab.setText("Score: " + Score.score );
		}
		
		// Hamster hits top, left, or right 
		// Reset hamster back in boundary, restart timer, change coefficient to opposite 
		if (y < Game.topline) {
			y = Game.topline;
			Game.time = 0; // reset timer to 0 after each hit 
			coY *= -1;
			bumpS = new Sounds.BumpS();
			
		}
		if (x < Game.leftline) {
			
			//set hamster back within boundary 
			x = Game.leftline;
			Game.time = 0; 
			coX *= -1;
			bumpS = new Sounds.BumpS();
			
		}
		if (x  > Game.rightline) {
			x = Game.rightline; 
			Game.time = 0;
			coX *= -1;
			bumpS = new Sounds.BumpS();
			
		}
		
		// Hamster hits the paddle 
		// Calculates displacement between paddle and hamster
		// y value can be negative, determines direction of deflection 
		int j = Math.abs(x - Game.paddle.centerX);
		int k = Game.paddle.centerY - y;
		
		// Motion of hamster if it bumps the paddle 
		if (j < 80 && k < 80 && k > 0){
			y = Game.paddle.centerY - 90;
			Game.time = 0;
			coY *= -1;
			bumpS = new Sounds.BumpS();
			
		}
		if (j < 80 && k > -10 && k < 0) {
			y = Game.paddle.centerY + 30 ;
			Game.time = 0; 
			coY *= -1;
			bumpS = new Sounds.BumpS();
		}
		
		// Adding displacement with coefficient direction to original position 
		x += (coX *a);
		y += (coY *b);
	}
}
