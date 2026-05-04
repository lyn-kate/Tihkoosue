/**
 * Final Project Assignment: Penguin Class (Katelyn)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import java.awt.event.*;
import java.awt.*;

class Penguin{
  private int x = 440;
  private int y = 430;
  private int baseStep = Constants.PENGUIN_SHIFT;
  private int stepX = Constants.PENGUIN_SHIFT;
  private int lives = 3;
  private int spriteNum = 0;
  private Sprite [] penguinSprites = new Sprite[7];
  boolean leftHeld = false;
  boolean rightHeld = false;
  
  private Sprite [] hearts = new Sprite[3];
  private boolean speedIncreased;
  private boolean speedDecreased;
  
//Constructor ---------------------------------------------------------------------------------------------------------
  Penguin(){
    //Load Sprites
    for(int i = 0; i < 7; i++){
      penguinSprites[i] = new Sprite(this.x, this.y, "sprites/penguin" + i + ".png");
    }
    
    int j = Constants.WIDTH-100;
    for(int i = 0; i < 3; i++){
      hearts[i] = new Sprite(j,110,"sprites/heartFull.png");
      j = j-70;
    }
  }
  
//Setters and Getters -------------------------------------------------------------------------------------------------
  public Sprite getSprite(){
    return this.penguinSprites[this.spriteNum];
  }
  public void setStepX(int step){
    this.stepX = step;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public int getW(){
    return this.penguinSprites[this.spriteNum].getWidth();
  }
  public int getH(){
    return this.penguinSprites[this.spriteNum].getHeight();
  }
  
  public int getStepX(){
    return this.stepX;
  }
  
  public boolean getSpeedIncreased(){
    return this.speedIncreased;
  }
  
  public boolean getSpeedDecreased(){
    return this.speedDecreased;
  }
  
  public int getLives(){
    return this.lives;
  }
  
  
//Methods ------------------------------------------------------------------------------------------------------------
  public void moveLeft(){
    //Change Sprite Image
    if (this.spriteNum < 3 && this.spriteNum > 0)
      this.spriteNum = this.spriteNum + 1;
    else
      this.spriteNum = 1;
    this.changeSprite();
    //Check for barrier
    if (this.x > 0)
      this.x = this.x - this.stepX;
    
  }
  
  public void moveRight(){
    //Change Sprite Image
    if (this.spriteNum < 6 && this.spriteNum > 3)
      this.spriteNum = this.spriteNum + 1;
    else 
      this.spriteNum = 4;
    this.changeSprite();
    //Check for barrier
    if (this.x + this.getSprite().getWidth() < Constants.WIDTH)
      this.x = this.x + this.stepX;
  }
  
  public void stop(){
    this.spriteNum = 0;
    this.changeSprite();
  }
  
  public void changeSprite(){
    this.penguinSprites[this.spriteNum].setX(this.x);
    this.penguinSprites[this.spriteNum].setY(this.y);
  } 
  
  //Checks for penguin movement controls
  public void keyPressed(int key){
    if (key == KeyEvent.VK_LEFT){
      this.leftHeld = true;
    }else if (key == KeyEvent.VK_RIGHT){
      this.rightHeld = true;
    }
  }
  
  //Checks for penguin stopping
  public void keyReleased(int key){
    if (key == KeyEvent.VK_LEFT){
      this.leftHeld = false;
    }else if (key == KeyEvent.VK_RIGHT){
      this.rightHeld = false;
    }
  }
  
  //Animates Penguin movement based on input
  public void animatePenguin(){
    if (this.leftHeld == true)
      this.moveLeft();
    if (this.rightHeld == true)
      this.moveRight();
    if (this.leftHeld == false && this.rightHeld == false)
      stop();
  }
  
  //(Amanda's method) speeds penguin up
  public void increaseStep(){
    if (!this.speedIncreased){
      this.speedDecreased = false;
      this.speedIncreased = true;
      this.stepX = this.baseStep+ 15;
    }
  }
  
  
  //(Amanda's method) slows penguin
  public void decreaseStep(){
    if (!this.speedDecreased){
      this.speedIncreased = false;
      this.speedDecreased = true;
      this.stepX = this.baseStep - 20;
    }
  }
  
  //(Amanda's method) makes speed normal
  public void revertStep(){
    this.stepX = this.baseStep;
    this.speedIncreased = false;
    this.speedDecreased = false;
  }
  
  //(Amanda's method) times speed effects
  public void timeSpeed(long startTime){
    long timePeriod = 5000;
    long timePassed;
    timePassed = System.currentTimeMillis()-startTime;
    if (timePassed>timePeriod){
      this.revertStep();
    }
  }
  
  //Decreases lives
  public void loseLife(){
    this.lives = this.lives-1;
    if (this.lives == 2){
      this.hearts[0] = new Sprite(Constants.WIDTH-100,110,"sprites/heartEmpty.png");
    }else if (this.lives == 1){
      this.hearts[1] = new Sprite(Constants.WIDTH-170,110,"sprites/heartEmpty.png");
    }
  }
  
  //(Amanda's method) displays lives
  public void drawHearts(Graphics g){
    for (Sprite heart: this.hearts){
      heart.draw(g);
    }
  }
  
  
}
