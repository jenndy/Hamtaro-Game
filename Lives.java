import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lives {

	public static int lifeNum = 3;
	
	public int lifeWidth = 28;
	public int lifeHeight = 40;
	
	public int y = Game.height/15; 
	
	public int x1 = Game.width*5/6;
	public int x2 = Game.width*5/6 + 40;
	public int x3 = Game.width*5/6 - 40;

	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image life = loadImage("src/graphics/life.png");

	public void draw(Graphics g) {
		for(int i = 0 ; i < lifeNum; i++) {
			g.drawImage(life, Game.width*5/6 - 40 + i*40, y, null);
		}
		
	}
	
}
