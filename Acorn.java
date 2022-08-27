// Acorns that hamster can eat to increase score 

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random; 

public class Acorn {
	
	// Dimensions of game frame
	public int X = Game.width;
	public int Y = Game.height;
	
	// Dimensions of acorn
	public int acornW = 50;
	public int acornH = 51;
	
	// Initial coordinates
	// x coordinate is random but max does not go out of the screen
	// y coordinate drops the acorn from the top of the screen
	Random rand = new Random(); 
	int x = rand.nextInt(X - acornW);
	int y = 0;
	
	// Motion coefficients 
	int coX = 1, coY = 1; 
	
	// Reads in acorn image
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	// Draws acorn image at initial coordinates
	Image acorn = loadImage("src/graphics/acorn.png");
	
	public void draw(Graphics g) {
		
		g.drawImage(acorn,  x,  y,  null);
	}
	
	// Animates acorn
	public void update() {
		
		// Displacement equations for projectile motion for x and y components
		int dtX = (int) ((20) * (Math.cos(Math.toRadians(60))) * (Game.time));
		int dtY = (int) ((20) * (Math.sin(Math.toRadians(60))) * (Game.time) - 0.5 * 9.8 * Math.pow(Game.time, 2));

		// Hits top
		if (y < Game.topline) {
			// Reset to top 
			y = Game.topline;
			// Restart motion time so velocity doesnt increase 
			Game.time = 0;
			// Change motion direction 
			coY *= -1;
		}

		// Hits left
		if (x < Game.leftline) {
			// Reset to left 
			x = Game.leftline;
			Game.time = 0;
			coX *= -1;
		}
		
		// Hits right
		if (x > Game.rightline) {
			// Reset to right 
			x = Game.rightline;
			Game.time = 0;
			coX *= -1;
		}
		
		// Hits hamster: score +2 and acorn disappears
		// Displacement of components between acorn and hamster 
		int disX = Math.abs(x - Game.lob.x);
		int disY = Math.abs(y - Game.lob.y);
		
		if (disX < 80 && disY < 80 ) {
			// Score increases by 2 
			Score.score += 2; 
			// Sets y coordinates of hamster out of the screen bottom 
			y = Y + 100; 
			// Resets game timer
			// Game.time = 0;
			// Sets score label 
			Score.scoreLab.setText("Score: " + Score.score );
			
		}
		
		// Location updates based on equations of motion and coefficients determine direction
		x += dtX*coX;
		y += dtY*coY;
	}	
}
