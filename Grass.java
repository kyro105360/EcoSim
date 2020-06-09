/**
 * Grass
 * Grass species that can be put onto the board.
 */
public class Grass extends Entity{
  
  Grass(int h, int x, int y){
    super(h, x, y);
  }
  /**
   * method to make a decision on what to do
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   */
  public void decision(Entity[][] map, int y, int x){
    if (getHealth() > 0){
      setX(x);
      setY(y);
      loseHealth(1);
    }else{
      map[y][x] = null;
      Ecosystem.grassCount --;
    }   
  }
}