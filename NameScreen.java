/**
 * Final Project Assignment: NameScreen Class (Amanda)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import java.awt.*;
import java.io.*;

class NameScreen{
  Font font = new Font("Monospaced", Font.BOLD, 60);
  Sprite nameScreen = new Sprite (0,0, "sprites/nameScreen.png");
  String name = "";

//Constructors --------------------------------------------------------------------------------------------------------
  NameScreen(){
  }
  
// Methods -----------------------------------------------------------------------------------------------------------
  
  //new letter typed
  public void addLetter(char a){
    if (this.name.length()<10){
      this.name = this.name + a;
    }
  }
  
  //backspace typed
  public void removeLetter(){
    if (this.name.length()>0){
      this.name = this.name.substring(0,this.name.length()-1);
    }
  }
  
  public void reset(){
    this.name = "";
  }
  
  //draw name entering screen
  public void draw(Graphics g){
    nameScreen.draw(g);
    g.setFont(font); 
    g.setColor(Color.BLACK);
    g.drawString(this.name, 315,350);
  }
  
  //write new score to file
  public void updateScores (File scoreFile, Player player) throws Exception{
    FileOutputStream scoreWrite = new FileOutputStream((scoreFile),true);
    PrintWriter output = new PrintWriter (scoreWrite);
    output.append("\n"+this.name+":"+player.getScore());
    output.close();
  }
}
