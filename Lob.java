import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lob {
	
	public int hamWidth = 80;
	public int hamHeight = 82;
	
	public int x = Game.width/2 - hamWidth/2;
	public int y = Game.baseline - 50 - hamHeight/2;
	
	public int vX = 0;
	public int vY = 0;
	public int coX = 1, coY = 1;
	
	public boolean gameOver = false;
	public boolean win = false;

	public BumpS bumpS;
	public TransitionS transitionS;
	public FallS fallS;
	public WinS winS;
	public LoseS loseS;
	
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	//draws the hamtaro 
	Image hamster = loadImage("src/graphics/hamster.png");
	
	public void draw(Graphics g) {
		g.drawImage(hamster,  x,  y,  null);
	}

	//calculates the projectile motion 
	public void update() {
	
		int a = (int)(Game.time*(20*Math.cos(Math.toRadians(60))));	
		int b = (int)(20*Math.sin(Math.toRadians(60))*Game.time-0.5*9.8*Math.pow(Game.time, 2));
		//System.out.println(x + "  and "  + y);
		
		//countown finishes (player wins the round) 
		if (Game.countdown.width2  <= 0) {
			
			Score.score += 5; 
			Score.round += 1; 
			
			//player wins the game 
			if (Score.round > 3) {
				Game.gameOver();
				win = true;
				winS = new WinS();
				//stops bg music (?)
				//Sounds.clip.stop();
			
			}else {
				//tranistions to new round 
				Score.roundLab.setText("Round: " + Score.round + " |");
				Game.countdown.width2 = 200;
				Lives.lifeNum = 3;
				transitionS = new TransitionS();
			}
			
			Score.scoreLab.setText("Score: " + Score.score );
		}
	
		//hamster falls out of screen 
		if (y >= Game.baseline) {
			
			Score.score -= 2; 
			Lives.lifeNum -= 1; 
			y = 50;
			Game.time = 0;
			fallS = new FallS();
			
			if ( Lives.lifeNum == 0 ) {
				Game.gameOver();	
				//draw game over 
				gameOver = true;
				loseS = new LoseS();
			}
			Score.scoreLab.setText("Score: " + Score.score );
		}
		
		//hamster can hit 3 sides and the paddle 
		if (y < Game.topline) {
			y = Game.topline;
			Game.time = 0; // reset timer to 0 after each hit 
			coY *= -1;
			bumpS = new BumpS();
			
		}
		if (x < Game.leftline) {
			
			//set hamster back within boundary 
			x = Game.leftline;
			Game.time = 0; 
			coX *= -1;
			bumpS = new BumpS();
			
		}
		if (x  > Game.rightline) {
			x = Game.rightline; 
			Game.time = 0;
			coX *= -1;
			bumpS = new BumpS();
			
		}
		
		//calculates the distance between the paddle and the hamster
		//y value can be negative, determines direction of deflection 
		int j = Math.abs(x - Game.paddle.centerX);
		int k = Game.paddle.centerY - y;
		
		//determines motion of hamster if it bumps the paddle 
		if (j < 80 && k < 80 && k > 0){
			y = Game.paddle.centerY - 90;
			Game.time = 0;
			coY *= -1;
			bumpS = new BumpS();
			
		}
		if (j < 80 && k > -10 && k < 0) {
			y = Game.paddle.centerY + 30 ;
			Game.time = 0; 
			coY *= -1;
			bumpS = new BumpS();
		}
		
		x += (coX *a);
		y += (coY *b);
	}
}
