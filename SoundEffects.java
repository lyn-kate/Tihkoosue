/**
 * Final Project Assignment: Constant variables (Katelyn)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

public final class SoundEffects{
  public static Sound collectionNoise = new Sound("audio/collectionNoise.wav");
  public static Sound loseLifeNoise = new Sound("audio/loseLifeNoise.wav");
  public static Sound frozenNoise = new Sound ("audio/frozenNoise.wav");
  public static Sound goldNoise = new Sound ("audio/goldNoise.wav");
  public static Sound speedNoise = new Sound ("audio/speedNoise.wav");
  public static Sound selectNoise = new Sound ("audio/selectNoise.wav");
  
  SoundEffects(){
    }
     
  public static void playCollectionNoise(){
    collectionNoise.stop();
    collectionNoise.flush();
    collectionNoise.setFramePosition(0);
    collectionNoise.start();
  }
  public static void playLoseLifeNoise(){
    loseLifeNoise.stop(); 
    loseLifeNoise.flush();
    loseLifeNoise.setFramePosition(0);
    loseLifeNoise.start();
  }
  public static void playFrozenNoise(){
    frozenNoise.stop(); 
    frozenNoise.flush();
    frozenNoise.setFramePosition(0);
    frozenNoise.start();
  }
  public static void playGoldNoise(){
    goldNoise.stop(); 
    goldNoise.flush();
    goldNoise.setFramePosition(0);
    goldNoise.start();
  }
  public static void playSpeedNoise(){
    speedNoise.stop(); 
    speedNoise.flush();
    speedNoise.setFramePosition(0);
    speedNoise.start();
  }
  public static void playSelectNoise(){
    selectNoise.stop(); 
    selectNoise.flush();
    selectNoise.setFramePosition(0);
    selectNoise.start();
  }
     
}