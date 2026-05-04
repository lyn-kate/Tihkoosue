/**
 * Final Project Assignment: Gamemode Class (Katelyn)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

class GameMode{

//CONTRUCTOR ------------------------------------------------------------------------------------------------------
  GameMode(){
  }
  
// METHODS ----------------------------------------------------------------------------------------------------------
  
//Game mode selection through nouse
  public String selectGameMode(int mouseX, int mouseY, GameScreen gameScreen){
    gameScreen.reset();
    if (mouseY >= 335 && mouseY < 386){
      if (mouseX >= 144 && mouseX <= 337){
        this.setEasy(gameScreen);
        SoundEffects.playSelectNoise();
        return "gameScreen";
      }else if (mouseX >= 403 && mouseX <= 597){
        this.setMedium(gameScreen);
        SoundEffects.playSelectNoise();
        return "gameScreen";
      }else if (mouseX >= 660 && mouseX <= 853){
        this.setHard(gameScreen);
        SoundEffects.playSelectNoise();
        return "gameScreen";
      }
    }
    return "gameMode";
  }
  
//Set fish shift to correct game mode
  public void setEasy(GameScreen gameScreen){
    Fish.setGameModeShift(Constants.FISH_SHIFT_EASY);
  }
  public void setMedium(GameScreen gameScreen){
    Fish.setGameModeShift(Constants.FISH_SHIFT_MEDIUM);
  }
  public void setHard(GameScreen gameScreen){
    Fish.setGameModeShift(Constants.FISH_SHIFT_HARD);
  }
  
    
}