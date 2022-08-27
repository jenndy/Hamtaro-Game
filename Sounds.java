// Audio input 

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	public static File sound;
	public static Clip clip;
	public static AudioInputStream audioIn;
	
	// Constructor that takes in a sound file 
	protected Sounds(String filename) {
		sound = new File(filename);

		try {
			audioIn = AudioSystem.getAudioInputStream(sound);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		try {
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Sound file for winning 
	public static class WinS extends Sounds{

		// Constructor 
		public WinS() {
			super("sounds/win.wav");
		}
	}
	
	// Sound file for transitioning between rounds 
	public static class TransitionS extends Sounds {
		
		// Constructor 
		public TransitionS() {
			super("sounds/transition.wav");
		}
	}
	
	// Sound file for losing 
	public static class LoseS extends Sounds{

		// Constructor 
		public LoseS() {
			super("sounds/lose.wav");
		}
	}
	
	// Sound file for hamster falling out of screen 
	public static class FallS extends Sounds{

		// Constructor 
		public FallS() {
			super("sounds/fall.wav");
		}
	}
	
	// Sound file for hamster bumping into side of screen or pillow 
	public static class BumpS extends Sounds {
		
		// Constructor 
		public BumpS() {
			super("sounds/bump.wav");
		}
	}
	
	// Sound file for background music 
	public static class BgMusicS extends Sounds{
		
		// Constructor 
		public BgMusicS() {
			super("sounds/bg.wav");
		}
	}
}
