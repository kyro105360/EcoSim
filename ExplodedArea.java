/**
 * ExplodedArea
 * Area of explosion by creeper
 */
public class ExplodedArea extends Entity{
  private boolean justExploded = true; //variable to make sure all exploded areas disappear at the same time
  
  ExplodedArea(int h, int x, int y){
    super(h, x, y);
  }
  public void setExploded(){
    this.justExploded = false;
  }
  /**
   * method to make a decision on what to do
   * @param map takes in map.
   * @param y takes in coordinate on map
   * @param x takes in coordinate on map
   */ 
  public void decision(Entity[][] map, int y, int x){
    if (getHealth() <= 0){
      map[y][x] = null;
    }else{
      if (!justExploded){
        loseHealth(1);
      }
    }  
  }
}