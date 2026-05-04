/**
 * Final Project Assignment: Ice Fish Child Class (Amanda)
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

public class IceFish extends Fish{
  IceFish(int x, int y){
    super(x,y);
    this.setSprite(new Sprite(x,y,"sprites/IceFish.png"));
    this.setW(this.getSprite().getWidth());
    this.setH(this.getSprite().getHeight());
  }
  
  @Override
  public void collected(Player player, Penguin penguin){
    player.addScore(100);
    penguin.decreaseStep();
    SoundEffects.playFrozenNoise();
  }
}