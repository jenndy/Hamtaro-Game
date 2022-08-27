// Keeps track of the number of lives 

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lives {
	
	// Number of lives per round 
	public static int lifeNum = 3;
	
	// Dimensions of life potion image 
	public int lifeWidth = 28;
	public int lifeHeight = 40;
	
	// Coordinates of potion 
	public int y = Game.height/15; 
	public int x1 = Game.width*5/6;
	public int x2 = Game.width*5/6 + 40;
	public int x3 = Game.width*5/6 - 40;

	// Reads in life potion image 
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image life = loadImage("src/graphics/life.png");

	// Draws number of life potions specified by integer value of lifeNum 
	// Spaces each potion out by using the integer value of i 
	public void draw(Graphics g) {
		for(int i = 0 ; i < lifeNum; i++) {
			g.drawImage(life, Game.width*5/6 - 40 + i*40, y, null);
		}
		
	}
	
}
