import java.io.*;
import javax.sound.sampled.*;
public class AudioPlay{

	Clip clip;
	long clipTime;
	
	AudioPlay(String audioFile) {	
		File file = new File(audioFile);	
		music(file);
	}

	public void music (File file) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();	
			
		}
		catch(Exception e) {
			System.err.println(e);
		}

		
	}

	public long pause() {
		clipTime = clip.getMicrosecondPosition();
		clip.stop();
		return clipTime;
	}

	public void play(long clipTime) {
		clip.setMicrosecondPosition(clipTime);
		clip.start();
	}

}