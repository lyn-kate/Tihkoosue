/**
 * Final Project Assignment: GameOver Class (Amanda)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import java.awt.*;
import java.io.*;
import java.util.*;

class GameOver{
  Font font = new Font("Futura", Font.BOLD, 60);
  Font smallFont = new Font("Monospaced", font.BOLD, 30);
  Sprite gameOverBackground = new Sprite (0,0, "sprites/gameOver.png");
  ArrayList<Player> players = new ArrayList<Player>();
  
//Constructors --------------------------------------------------------------------------------------------------------
  GameOver(){
  }
  
// Methods -----------------------------------------------------------------------------------------------------------
  public String selectGameOver(int mouseX, int mouseY, GameScreen gameScreen){
    if (mouseY >= 20 && mouseY <= 100){
      if (mouseX >= 20 && mouseX <= 100){
        SoundEffects.playSelectNoise();
        return "startingScreen";
      }else if (mouseX >= 110 && mouseX <= 190){
        gameScreen.reset();
        SoundEffects.playSelectNoise();
        return "gameScreen";
      }
    }
    return "gameOverScreen";
  }
  
  //update list of high scores
  public void updateFile(File scoreFile) throws Exception{
    this.players.clear();
    Scanner input = new Scanner(scoreFile);
    while (input.hasNext()){
      String line = input.nextLine();
      if (!line.isEmpty()){
        int indexOfColon = line.indexOf(":");
        String name = line.substring(0, indexOfColon).trim();
        int score = Integer.parseInt(line.substring(indexOfColon+1, line.length()).trim());
        this.players.add(new Player(name,score));
      }
    }
    input.close();
    Collections.sort(players,Player.COMPARE_SCORE);
  }
  
  //draw highScore numbers
  public void drawHighScore(Graphics g){
    g.setFont(smallFont); 
    g.setColor(Color.BLACK);
    int nameY = 280;
    for (int i=0;i<5&& i<players.size();i++){
      g.drawString(""+players.get(i).getName(), 500, nameY);
      g.drawString(""+players.get(i).getScore(), 750, nameY);
      nameY = nameY+53;
    }
  }
  
  //draw game over screen
  public void draw(Graphics g, Player player){
    gameOverBackground.draw(g);
    g.setFont(font); 
    g.setColor(Color.WHITE);
    g.drawString("" + player.getScore(), 140,380);
    drawHighScore(g);
    }
}
