import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
public class song extends Thread
{
	File song;
	Clip clip;
	song(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		song = new File(path);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
	}
	public void run(int choice)
	{
		switch(choice)
		{
		case 0: clip.stop();
		break;
		case 1: clip.start();
		break;
		case 2: clip.setMicrosecondPosition(0);
		break;
		}
	}
}
