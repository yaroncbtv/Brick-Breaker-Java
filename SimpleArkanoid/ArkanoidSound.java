


import java.io.*;
import javax.sound.sampled.*; 

public class ArkanoidSound {
	
	AudioFormat adFormat = getAudioFormat();
	Clip clip;

	
	private AudioFormat getAudioFormat() {
		float sampleRate = 42100.0F;
		int sampleSizeInbits = 16;
		int channels = 1;
		boolean signed = true;
		float frameRate = 2;
		boolean bigEndian = false;
		return new AudioFormat (sampleRate, sampleSizeInbits, channels, signed, bigEndian);
	}
	void chargesound(File file) {
		try {
			
			AudioInputStream in = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(in);
			
			}catch (IOException e) {
			e.printStackTrace();
			}catch (LineUnavailableException e) {
			e.printStackTrace();
			}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		
			
			//Thread.sleep(1000);			
			
						
		}		
	}
	void startsound () {
		if (clip.isRunning())
		clip.stop();  
		clip.setFramePosition(0); 
		clip.loop(Clip.LOOP_CONTINUOUSLY);		
	}
	void ONCEstartsound () {
		
		clip.setFramePosition(0); 
		clip.start();
	}

		
	void stopsound () {
		
		if (clip.isRunning())
			clip.stop(); 
			clip.setFramePosition(0); 
	}
}
