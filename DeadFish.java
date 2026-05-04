/**
 * Final Project Assignment: Dead Fish Child Class (Amanda)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

public class DeadFish extends Fish{
  DeadFish(int x, int y){
    super(x,y);
    this.setSprite(new Sprite(x,y,"sprites/DeadFish.png"));
    this.setW(this.getSprite().getWidth());
    this.setH(this.getSprite().getHeight());
  }
  
  @Override
  public void collected(Player player, Penguin penguin){
    penguin.loseLife();
    SoundEffects.playLoseLifeNoise();
  }
}