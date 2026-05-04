/**
 * Player Class (Amanda)
 * @author Katelyn Ma and Amanda Xi
 * @version Dec 2022
 */

import java.awt.*;
import java.util.Comparator;

public class Player{
  Font font = new Font("Futura", Font.BOLD, 40);
  private String name;
  private int score;
  Sprite display = new Sprite(0,0,"sprites/scoreBackground.png");
  
  Player(){
    this.score = 0;
  }
  
  Player(String name, int score){
    this.name = name;
    this.score = score;
  }
  
  //methods -----------------------------
  
  public void addScore(int num){
    this.score = this.score+num;
  }
  
  public void drawScore(Graphics g){
    this.display.draw(g);
    g.setFont(font); 
    g.setColor(Color.black);
    g.drawString("" + this.getScore(), 720, 70);
  }
  
  
  //getters setters
  public String getName(){
    return this.name;
  }
  public int getScore(){
    return this.score;
  }
  
  //comparator
  public static Comparator<Player> COMPARE_SCORE = new Comparator<Player>(){
    public int compare(Player player1, Player player2){
      return (player2.getScore() - player1.getScore());
    }
  };
  
  @Override
  public String toString(){
    return(name + ": " + score);
  }
  
}