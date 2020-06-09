/**
 * Creeper
 * Creeper species that can be put onto the board. Creepers will blow up and destroy everything around it
 */
public class Creeper extends Animal{
  private int numOfTurns;
  Creeper(int h, int x, int y){
    super(h, x, y);
    this.numOfTurns = 0;
  }
  /**
   * method to make a decision on what to do
   * @param map takes in map.
   * @param y takes in coordinate on map
   * @param x takes in coordinate on map
   */
  public void decision(Entity[][] map, int y, int x){                      
    if (!getTired()){
      // check if surrounding area is another animal
      if ((x > 0) && (y > 0) && (map[y-1][x-1] instanceof Animal )){
        destroyArea(map, x, y);
      }else if ((x < map.length-1) && (y < map.length -1) && (map[y+1][x+1] instanceof Animal)){
        destroyArea(map, x, y);
      }else if ((y > 0) && map[y-1][x] instanceof Animal){
        destroyArea(map, x, y);
      }else if ((x < map.length -1) && (y > 0) && (map[y-1][x+1] instanceof Animal)){
        destroyArea(map, x, y);
      }else if(( x < map.length -1) && (map[y][x+1] instanceof Animal)){
        destroyArea(map, x, y);
      }else if(( x > 0) && (map[y][x-1] instanceof Animal)){
        destroyArea(map, x, y);
      }else if( (y < map.length-1) && (map[y+1][x] instanceof Animal)){
        destroyArea(map, x, y);
      }else if((x > 0) && (y < map.length -1) && (map[y+1][x-1] instanceof Animal)){
        destroyArea(map, x, y);
      }else if (numOfTurns == 3){
        numOfTurns = 0;
        moveRandom(map, y, x);
      }
    }
    numOfTurns ++;
  }
  /**
   * method to destroy surrounding area
   * @param map takes in map.
   * @param y takes in coordinate on map
   * @param x takes in coordinate on map
   */
  public void destroyArea(Entity[][] map, int x, int y){
    map[y][x] = null;
    //check if the area to destroy is within the map
    if ((x > 0) && (y > 0)){
     checkIfOtherType(map, y-1, x-1);
      map[y-1][x-1] = null;
      map[y-1][x-1] = new ExplodedArea(3, x-1, y-1);
    }
    if ((x + 1 < map.length) && (y + 1< map.length)){
     checkIfOtherType(map, y+1, x+1);
      map[y+1][x+1] = null;
      map[y+1][x+1] = new ExplodedArea(3, x+1, y+1);
    }
    if ((y > 0)){
     checkIfOtherType(map, y-1, x);
      map[y-1][x] = null;
      map[y-1][x] = new ExplodedArea(3, x, y-1);
    }
    if ((x +1 < map.length) && (y > 0)){
     checkIfOtherType(map, y-1, x+1);
      map[y-1][x+1] = null;
      map[y-1][x+1] = new ExplodedArea(3, x+1, y-1);
    }
    if ( x < map.length -1){
     checkIfOtherType(map, y, x+1);
      map[y][x+1] = null;
      map[y][x+1] = new ExplodedArea(3, x+1, y);
    }
    if(x > 0){
    checkIfOtherType(map, y, x-1);
      map[y][x-1] = null;
      map[y][x-1] = new ExplodedArea(3, x-1, y);
    }
    if (y < map.length-1){
    checkIfOtherType(map, y+1, x);
      map[y+1][x] = null;
      map[y+1][x] = new ExplodedArea(3, x, y+1);
    }
    if((x > 0) && (y < map.length -1)){
    checkIfOtherType(map, y+1, x-1);
      map[y+1][x-1] = null;
      map[y+1][x-1] = new ExplodedArea(3, x-1, y+1);
    }
  }
  /**
   * method to check if area destroyed is another entity
   * @param map takes in map.
   * @param y takes in coordinate on map
   * @param x takes in coordinate on map
   */
  public void checkIfOtherType(Entity[][] map, int y, int x){
    if (map[y][x] instanceof Sheep){
      Ecosystem.sheepCount --;
    }
    else if (map[y][x] instanceof Grass){
      Ecosystem.grassCount --;
    }
    else if (map[y][x] instanceof Wolf){
      Ecosystem.wolfCount --;
    }
  }
  /**
   * method to move to a location
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   * @param destX takes in the desired coordinate x on map
   * @param destY takes in desired coordiante Y on map
   */
  public void move(Entity[][] map, int x, int y, int destX, int destY){
    map[destY][destX] = map[y][x];
    map[y][x] = null;
    setX(destX);
    setY(destY);
    
  }
}

