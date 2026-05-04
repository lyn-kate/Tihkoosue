/**
 * Final Project Assignment: Golden Fish Child Class (Amanda)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

public class GoldenFish extends Fish{
  GoldenFish(int x, int y){
    super(x,y);
    this.setSprite(new Sprite(x,y,"sprites/GoldenFish.png"));
    this.setW(this.getSprite().getWidth());
    this.setH(this.getSprite().getHeight());
  }
  
  @Override
  public void collected(Player player, Penguin penguin){
    player.addScore(1000);
    SoundEffects.playGoldNoise();
  }
}