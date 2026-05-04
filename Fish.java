/**
 * Fish (Amanda) 
 * @author Katelyn Ma and Amanda Xi
 * @version Dec 2022
 */

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

abstract class Fish{
  private int x;
  private int y;
  private Sprite fishSprite;
  private int w;
  private int h;
  private int shift;
  private static int gameModeShift;
  
  Fish(int x, int y){
    this.x = x;
    this.y = y;
    this.shift = gameModeShift;
  }

// GETTERS AND SETTERS ---------------------------------------------------------------------------------------------
  public Sprite getSprite(){
    return this.fishSprite;
  }
  
  public int getX(){
    return this.x;
  }
  
  public int getY(){
    return this.y;
  }
  
  public int getW(){
    return this.w;
  }
  
    public int getH(){
    return this.h;
  }
  
    public void setSprite(Sprite sprite){
    this.fishSprite  = sprite;
  }
  
  public void setW(int w){
    this.w = w;
  }
  
  public void setH(int h){
    this.h = h;
  }
  
  public static void setGameModeShift(int shift){
    gameModeShift = shift;
  }

  
// METHODS ----------------------------------------------------------------------------------------------------------
  
 //Generates a new fish
  public static Fish newFish(ArrayList<Fish> fishList, int y){
    int fishType = (int)(Math.random()*20);
    int fishX = (int)(Math.random()*(Constants.WIDTH-100));
    
    if (fishType>=0&&fishType<8){
      return(new RegularFish(fishX,y));
    }else if (fishType>=8&&fishType<12){
      return(new IceFish(fishX, y));
    }else if (fishType==12){
      return(new RainbowFish(fishX, y));
    }else if (fishType==13){
      return(new GoldenFish(fishX,y));
    }else{
      return(new DeadFish(fishX,y));
    }
  }
  
  //Generates fish at beginning of game
  public static void generateFish(ArrayList<Fish> fishList){
    for (int y=-100;y>=-800;y=y-100){
      fishList.add(newFish(fishList,y));
    }
    
  }
  
  //Removes fish when they leave screen
  public static void removeFish(ArrayList<Fish> fishList){
    for (int i=0;i<fishList.size();i++){
      if (fishList.get(i).getY() > Constants.HEIGHT){
        fishList.remove(i);
        fishList.add(newFish(fishList, fishList.get(fishList.size()-1).getY()-100));
      }
    }
  }
  
  //Draws fish
  public static void drawFish(Graphics g, ArrayList<Fish> fishList){
    for (Fish fish: fishList){
      fish.fishSprite.draw(g);
    }
  }
  
  //Moves fish every frame
  public static void moveFish(ArrayList<Fish> fishList){
    for (Fish fish: fishList){
      fish.fall();
    }
  }
  
  //Makes individual fish move down 
  public void fall(){
    this.y = this.y+shift;
    this.fishSprite.setY(this.y);
  }
  
  //Makes fish disappear when collided
  public static void collectFish(Penguin penguin, Player player, ArrayList<Fish> fishList){
    for (int i=0;i<fishList.size();i++){
      if (checkCollision(penguin, fishList.get(i).getX(), fishList.get(i).getY(), fishList.get(i).getW(), fishList.get(i).getH())){
        fishList.get(i).collected(player, penguin);
        fishList.remove(i);
        fishList.add(newFish(fishList,fishList.get(fishList.size()-1).getY()-100));
      }    
    }
  }
  
  //Checks collision with penguin
  public static boolean checkCollision(Penguin penguin, int fishX, int fishY, int fishW, int fishH){
    if((fishY+fishH)>penguin.getY()&&fishY<(penguin.getY()+penguin.getH())){
      if ((fishX+fishW)>penguin.getX()&& fishX<(penguin.getX()+penguin.getW())){
        return true;
      }
    }return false;
  }

  //different effects of collection for each fish
  abstract public void collected(Player player, Penguin penguin); 
}

