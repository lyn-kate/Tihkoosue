/**
 * Final Project Assignment: Main Program
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class MainProgram{
  JFrame gameWindow;
  GraphicsPanel canvas;
  BasicKeyListener keyListener;
  BasicMouseListener mouseListener;
  
  Music music = new Music("audio/backgroundMusic.wav");
  Music openingMusic = new Music("audio/openingMusic.wav");
  boolean musicPlaying = false;
  boolean scoreUpdated = false;
  String currentScreen = "startingScreen";
  
  GameScreen gameScreen = new GameScreen();
  GameMode gameMode = new GameMode();
  GameOver gameOver = new GameOver();
  NameScreen nameScreen = new NameScreen();
  
  //Screen Properties
  Sprite startingBackground = new Sprite(0,0, "sprites/startingBackground.png");
  Sprite guideBackground = new Sprite (0,0, "sprites/guide.png");
  Sprite gameModeBackground = new Sprite (0,0, "sprites/gameModeBackground0.png");
  Sprite snow = new Sprite(0,0, "sprites/snow.png");
  Sprite snow2 = new Sprite(0,-600, "sprites/snow.png");
  
  //File
  File scoreFile = new File("text file/scoreFile.txt");
  
//Contructor ----------------------------------------------------------------------------------------------------------
  MainProgram(){
    gameWindow = new JFrame("Tihkoosue");
    gameWindow.setSize(Constants.WIDTH, Constants.HEIGHT);
    gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Graphics Panel
    canvas = new GraphicsPanel();
    gameWindow.add(canvas);
    
    //Listeners
    keyListener = new BasicKeyListener();
    canvas.addKeyListener(keyListener); 
    mouseListener = new BasicMouseListener();    
    canvas.addMouseListener(mouseListener);
    
    gameWindow.setVisible(true);
    
  }
  
// RUN GAME ---------------------------------------------------------------------------------------------------------
  public void run() throws Exception{
    //Read highscores file
    gameOver.updateFile(scoreFile);
    
    while (true){
      playMusic();
      try  {Thread.sleep(Constants.FRAME_PERIOD);} catch(Exception e){}
      
      //Game screen controls
      if (currentScreen.equals("gameScreen")){
        gameScreen.animate();
        //check if game is over
        currentScreen = gameScreen.endGame(nameScreen, gameOver);
      }
      //Game over controls
      else if (currentScreen.equals("gameOverScreen")){
        //Update score array if changed
        if (!scoreUpdated){
          nameScreen.updateScores(scoreFile, gameScreen.player);
          gameOver.updateFile(scoreFile);
          scoreUpdated = true;
        }
      }
      //if a new name is entered, set scoreUpdated to false
      else if (currentScreen.equals("nameScreen") && scoreUpdated == true){
        scoreUpdated = false;
      }
      else if (currentScreen.equals("startingScreen")){
        //Snow Animation
        scrollImages(snow);
        scrollImages(snow2);
      }
      
      gameWindow.repaint();
    }
  }
  
// Draw Images -------------------------------------------------------------------------------------------------------
  public class GraphicsPanel extends JPanel{
    public GraphicsPanel(){
      setFocusable(true);
      requestFocusInWindow();
    }
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      if (currentScreen.equals("startingScreen")){
        startingBackground.draw(g);
        snow.draw(g);
        snow2.draw(g);
        
      }else if (currentScreen == "guideScreen"){
        guideBackground.draw(g);
        
      }else if (currentScreen.equals("gameMode")){
        gameModeBackground.draw(g);
        
      }else if (currentScreen.equals("gameScreen")){
        gameScreen.draw(g);
      }
      
      else if (currentScreen.equals("gameOverScreen")){
        gameOver.draw(g,gameScreen.player);
      }
      else if (currentScreen.equals("nameScreen")){
        nameScreen.draw(g);
      }
    }
  }
  
//KeyListener ----------------------------------------------------------------------------------------------------------
  public class BasicKeyListener implements KeyListener{      
    public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();
      
      //Name keyboard controls
      if (currentScreen.equals("nameScreen")){
        if (key>=65 && key<=90){
          nameScreen.addLetter(e.getKeyChar());
        }else if (key == KeyEvent.VK_BACK_SPACE){
          nameScreen.removeLetter();
        }else if (key == KeyEvent.VK_ENTER){
          currentScreen = "gameOverScreen";
        }
      }
      
      //Start game controls
      else if (key == KeyEvent.VK_ENTER){
        if(currentScreen == "startingScreen"){
          currentScreen = "guideScreen";
          SoundEffects.playSelectNoise();
        }else if (currentScreen == "guideScreen"){
          currentScreen = "gameMode";
          SoundEffects.playSelectNoise();
        }
        
      //Penguin controls
      }else if (currentScreen.equals("gameScreen")){
        gameScreen.penguin.keyPressed(key);
        gameScreen.pressPause(key);
      }
    }
    
    
    public void keyReleased(KeyEvent e){
      int key = e.getKeyCode();
      //Penguin controls
      if (currentScreen.equals("gameScreen")){
        gameScreen.penguin.keyReleased(key);
      }
    }
    
    
    public void keyTyped(KeyEvent e){
    }
  }
  
//Mouse Listener ------------------------------------------------------------------------------------------------------
  public class BasicMouseListener implements MouseListener{
    public void mouseClicked(MouseEvent e){
      int mouseX = e.getX();
      int mouseY = e.getY();
      if (currentScreen.equals("gameMode")){
        //Sets gamemode and currentScreen
        currentScreen = gameMode.selectGameMode(mouseX, mouseY, gameScreen);
      }
      if (currentScreen.equals("gameScreen")){
        currentScreen = gameScreen.selectExit(mouseX, mouseY);
      }
      if (currentScreen.equals("gameOverScreen")){
        currentScreen = gameOver.selectGameOver(mouseX, mouseY, gameScreen);
      }
    }
    
    public void mousePressed(MouseEvent e){  
    }
    public void mouseReleased(MouseEvent e){ 
    }
    public void mouseEntered(MouseEvent e){ 
    }
    public void mouseExited(MouseEvent e){ 
    }
  }
//----------------------------------------------------------------------------------------------------------------------
  //Plays Music for correct Screen
  public void playMusic(){
    if (currentScreen.equals("gameScreen")){
      if (gameScreen.paused){
        music.stop();
        musicPlaying = false;
      }else if (!musicPlaying){
        openingMusic.stop();
        music.start();
        music.loop();
        musicPlaying = true;
        }
    }else if (!currentScreen.equals("gameScreen")){
      musicPlaying = false;
      music.stop();
      openingMusic.start();
    }
  }
  
  //Scrolls through images for movement effect
  public void scrollImages(Sprite sprite){
   if (sprite.getY() > Constants.HEIGHT)
     sprite.setY(-600);
   else
     sprite.setY(sprite.getY() + 10);
  }
  
//----------------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) throws Exception{
    MainProgram game = new MainProgram();
    game.run();
    
  }
}