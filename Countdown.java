// The countdown bar that times the duration of each round 
// Length of countdown bar increases with each round 

import java.awt.Graphics;

public class Countdown {

	public int x = Game.width/15;
	public int y = Game.height/15;
	
	public int width1 = 200;
	public int width2 = 200;
	
	public int height = 40;
	
	public void draw(Graphics g) {
		
		// Draws two rectangles, 1 clear and 1 filled 
		g.clearRect(x, y, width1, height);
		g.fillRect(x, y, width2, height);
		
	}
	
	// The filled rectangle shrinks to simulate a timer 
	// The timer shrinks at a slower rate with each successive round 
	public void update() {
		width2 -= 0.1 *(20 - 6*Score.round);
		
	}
}
