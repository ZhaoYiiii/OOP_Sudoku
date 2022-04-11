package sudoku;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
   
/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
   BG_MUSIC("/audio/sample3.wav");      // bullet
   
   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   String dir = System.getProperty("user.dir");
   
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
         // Use URL (instead of File) to read from disk and JAR.
//         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         String audioFilePath = dir + soundFileName;
         File file = new File(audioFilePath);
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
    	 System.out.println("The specified audio file is not supported.");
         e.printStackTrace();
      } catch (IOException e) {
    	 System.out.println("Error playing the audio file.");
         e.printStackTrace();
      } catch (LineUnavailableException e) {
    	 System.out.println("Audio line for playing back is unavailable.");
         e.printStackTrace();
      }
   }
   

   
   public void playContinue() {
	   if (volume != Volume.MUTE) {
		   if (clip.isRunning())
			   clip.stop();   // Stop the player if it is still running
		   clip.setFramePosition(0); // rewind to the beginning
		   clip.start();     // Start playing
		   clip.loop(Clip.LOOP_CONTINUOUSLY);
		   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		   double gain = 0.01;   
		   float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		   gainControl.setValue(dB);
	   } 
   }
   
   public void playResume() {
	   if (volume != Volume.MUTE) {
		   if (clip.isRunning()) {
			   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			   double gain = 0.01;   
			   float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
			   gainControl.setValue(dB);
		   }
	   } 
   }
   
   public void playMute() {
	   if (volume == Volume.MUTE) {
		   if (clip.isRunning()) {
			   FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			   double gain = 0.00;   
			   float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
			   gainControl.setValue(dB);
		   }
	   } 
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}