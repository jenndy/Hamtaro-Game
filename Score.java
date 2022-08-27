import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Score extends JPanel implements ActionListener {

	public static int score = 0;
	public static int round = 1;
	public static JLabel scoreLab;
	public static JLabel roundLab;
	public static JButton restartButton;
	
	public Score() {
		setPreferredSize(new Dimension(100, 100));
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);
		
		scoreLab = new JLabel("Score: " + score);
		roundLab = new JLabel("Round: " + round + " |");
		
		Score.scoreLab.setForeground(Color.WHITE);
		Score.roundLab.setForeground(Color.WHITE);

	}
	
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image gameover = loadImage("src/graphics/gameover.png");
	Image win = loadImage("src/graphics/win.png");
	
	public void draw(Graphics g) {
			g.drawImage(gameover, 0, Game.height - 4*Game.height/5, null);
		}
		
	public void draw2(Graphics g) {
			g.drawImage(win, 0, Game.height - 4*Game.height/5, null);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
