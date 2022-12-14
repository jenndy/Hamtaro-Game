// Animation for the pillow / paddle 

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Paddle {

	// Coordinates to center pillow 
	public int centerX = Game.width/2;
	public int centerY = Game.baseline;
	
	// Dimensions of paddle 
	public int width = 100;
	public int height = 20;
	
	// Initial velocity of paddle 
	public int vX = 0;
	public int vY = 0;
	
	public void setLoc(int x, int y) {
		centerX = x;
		centerY = y;
	}

	// Reads in pillow image 
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image pillow = loadImage("src/graphics/pillow.png");
	
	// Draws the pillow 
	public void draw(Graphics g) {
		int x = centerX - width/2;
		int y = centerY - height/2;
		g.drawImage(pillow,  x,  y,  null);
	}
	
	public void update() {
		
		// Adds displacement to the pillow's coordinates 
		centerX += vX;
		centerY += vY;

		// Prevents pillow from going outside of boundaries 
		if (centerY >= Game.baseline) {
			centerY = Game.baseline;
			vY = 0;
		}
		if (centerY <= Game.topline) {
			centerY = Game.topline;
			vY = 0; 
		}
		if (centerX <= Game.leftline) {
			centerX = Game.leftline;
			vX = 0; 
		}
		if (centerX  >= Game.rightline) {
			centerX = Game.rightline;
			vX = 0; 
		}
		
	}
}
