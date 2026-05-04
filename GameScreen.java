/**
 * Final Project Assignment: Game Screen (Katelyn)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */


import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

class GameScreen{
  
  Penguin penguin = new Penguin();
  Sprite gameBackground = new Sprite(0,0, "sprites/gameBackground.png");
  Sprite exitScreen = new Sprite(0, 0, "sprites/exitScreen.png");
  ArrayList<Fish> fishList = new ArrayList<Fish>();
  boolean paused = false;
  Player player = new Player();
  
  //Time properties
  boolean timeStarted = false;
  long startTime;
  
  GameScreen(){
  }
  
// METHODS ----------------------------------------------------------------------------------------------------------
  
  //Draws graphics for Game Screen
  public void draw(Graphics g){
    this.gameBackground.draw(g);
    this.penguin.getSprite().draw(g);
    Fish.drawFish(g, this.fishList);
    this.penguin.drawHearts(g);
    this.player.drawScore(g);
    if (this.paused == true){
      this.exitScreen.draw(g);
    }
  }
  
  //Animates Whole Screen
  public void animate(){
    if (this.paused == false){
      this.penguin.animatePenguin();
      animateFish();
      checkSpeed();
    }
  }
  
  //Animates Fish and movement
  public void animateFish(){
    if (fishList.isEmpty()){
      Fish.generateFish(fishList);
    }
    Fish.moveFish(this.fishList);
    Fish.removeFish(this.fishList);
    // Fish.collectFish(this.penguin,this.fishList);
    Fish.collectFish(this.penguin, this.player, this.fishList);
  }
  
  //If key is pressed to pause
  public void pressPause(int key){
    if (key == KeyEvent.VK_ESCAPE){
      if (this.paused == true){
        this.paused = false;
      }else if (this.paused == false){
        this.paused = true;
      }
    }
  }
  
  //Exit and navigation menu mouse select
  public String selectExit(int mouseX, int mouseY){
    if (mouseX  >= 225 && mouseX <= 745 && this.paused == true){
      if (mouseY >= 224 && mouseY <= 292){
        this.paused = false;
        SoundEffects.playSelectNoise();
        return "gameScreen";
      }else if (mouseY >= 300 && mouseY <= 368){
        this.reset();
        this.paused = false;
        SoundEffects.playSelectNoise();
      }else if (mouseY >= 377 && mouseY <= 445){
        this.reset();
        this.paused = false;
        SoundEffects.playSelectNoise();
        return "startingScreen";
      }
    }
    return "gameScreen";
  }
  
  //Resets Game
  public void reset(){
    this.penguin = new Penguin();
    this.fishList = new ArrayList<Fish>();
    this.player = new Player();
    this.paused = false;
  }
  
  //(Amanda's method) Check to see if game has ended
  public String endGame(NameScreen nameScreen, GameOver gameOver){
    if (penguin.getLives() == 0){
      //check if player's score is a high score
      int lowestScore = 0;
      if (gameOver.players.size()>=5){
        lowestScore = gameOver.players.get(4).getScore();
      }
      //if high-score, asks for name
      if (gameOver.players.size()<5 || this.player.getScore()>lowestScore){
        nameScreen.reset();
        return "nameScreen";
      }else
        return "gameOverScreen";
    }
    else
      return "gameScreen";
  }
  
  //(Amanda's method) track time for speed changes
  public void checkSpeed(){
    if (penguin.getSpeedIncreased()||penguin.getSpeedDecreased()){
      if (!timeStarted){
        startTime = System.currentTimeMillis();
        timeStarted = true;
      }
      penguin.timeSpeed(startTime);
    }
    else
      timeStarted = false;
  }
}
