/**
 * Final Project Assignment: Music Class
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class Music{
    Clip music;
//------------------------------------------------------------------------------         
    Music(String musicName){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicName));
            this.music = AudioSystem.getClip();
            this.music.open(audioStream);
        } 
        catch (IOException ex){System.out.println("File not found");}
        catch (UnsupportedAudioFileException ex){System.out.println("Unsupported file");}   
        catch (LineUnavailableException ex){System.out.println("Audio feed already in use");}
    }
//------------------------------------------------------------------------------         
    public void start(){
        this.music.start();
    }
    public void loop(){
        this.music.loop(Clip.LOOP_CONTINUOUSLY); 
    }
    
    public void stop(){
      this.music.stop();
    }
}