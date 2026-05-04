/**
 * Regular Fish (Amanda)
 * @author Katelyn Ma and Amanda Xi
 * @version Dec 2022
 */

public class RegularFish extends Fish{
  RegularFish(int x, int y){
    super(x,y);
    this.setSprite(new Sprite(x,y,"sprites/RegularFish.png"));
    this.setW(this.getSprite().getWidth());
    this.setH(this.getSprite().getHeight());

  }
  
  @Override
  public void collected(Player player, Penguin penguin){
    player.addScore(100);
    SoundEffects.playCollectionNoise();
  }
}