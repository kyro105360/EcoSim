/**
 * Wolf
 * Wolf species that are able to be put on the board
 */

public class Wolf extends Animal implements Comparable<Wolf>{
  
  Wolf(int h, int x, int y){
    super(h, x, y);
  }
  public int compareTo(Wolf w){
    return getHealth() - w.getHealth();
  }
  /**
   * method to make two wolves clash
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   * @param opponentX takes in the desired coordinate x on map
   * @param opponentY takes in desired coordinate Y on map
   */ 
  public void wolfClash(Entity map[][], int x, int y, int opponentX, int opponentY){
    if (compareTo(((Wolf)map[opponentY][opponentX])) > 0){ 
      loseHealth(((Wolf)map[opponentY][opponentX]).getHealth());
      map[opponentY][opponentX] = null;
      move(map, x, y, opponentX, opponentY);
      Ecosystem.wolfCount --;
    }else if (compareTo(((Wolf)map[opponentY][opponentX])) == 0){
      map[y][x] = null;
      map[opponentY][opponentX] = null;
      Ecosystem.wolfCount -= 2;
    }else{
      ((Wolf)map[opponentY][opponentX]).loseHealth(getHealth());
      map[y][x] = null;
      ((Wolf)map[opponentY][opponentX]).move(map, opponentX, opponentY, x, y);
      Ecosystem.wolfCount --;
    }
  }
  /**
   * method to eat sheep
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   * @param sheepX takes in the sheep x on map
   * @param sheepY takes in sheep coordinate Y on map
   */ 
  public void eatSheep(Entity[][] map, int x, int y, int sheepX, int sheepY){
    gainHealth(((Sheep)map[sheepY][sheepX]).getHealth());
    map[sheepY][sheepX] = null;
    move(map, x, y, sheepX, sheepY);
    Ecosystem.sheepCount --;
  }
  /**
   * method to move to a location
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   * @param destX takes in the desired coordinate x on map
   * @param destY takes in desired coordinate Y on map
   */ 
  
  public void move(Entity[][] map, int x, int y, int destX, int destY) {
    if (map[destY][destX] instanceof Sheep){
      eatSheep(map, x, y, destX, destY);
    }else if (map[destY][destX] instanceof Wolf){
      wolfClash(map, x, y, destX, destY);
    }else{
      if (map[destY][destX] instanceof Grass){
        Ecosystem.grassCount --;
      }
      setTired(true);
      loseHealth(1);
      map[destY][destX] = map[y][x];
      map[y][x] = null;
      setX(destX);
      setY(destY);
    }
  }
  /**
   * method to make a decision on what to do
   * @param map takes in map.
   * @param y takes in coordinate on map
   * @param x takes in coordinate on map
   */ 
  public void decision(Entity[][] map, int y, int x){
    if (!getTired()){
      if (getHealth() <= 0 || getAge() >= 300){
        Ecosystem.wolfCount --;
        map[y][x] = null;
      }else{
//        System.out.println(x + ":" + y +  ", " + getX() + ": " + getY() + " Health:" + getHealth() + " Age:" + getAge());
        if ((x > 0) && (y > 0) && ((map[y-1][x-1] instanceof Sheep) || (map[y-1][x-1] instanceof Wolf))){
          move(map, x, y, x-1, y-1);
        }else if ((x < map.length-1) && (y < map.length -1) && 
                  ((map[y+1][x+1] instanceof Sheep) || (map[y+1][x+1] instanceof Wolf)) ){
          move(map, x, y, x+1, y+1);
        }else if ((y > 0) && map[y-1][x] instanceof Sheep){
          move(map, x, y, x, y-1);
        }else if ((x < map.length -1) && (y > 0) && ((map[y-1][x+1] instanceof Sheep) || (map[y-1][x+1] instanceof Wolf))){
          move(map, x, y, x+1, y-1);
        }else if(( x < map.length -1) && ((map[y][x+1] instanceof Sheep) || (map[y][x+1] instanceof Wolf))){
          move(map, x, y, x+1, y);
        }else if(( x > 0) && ((map[y][x-1] instanceof Sheep) || (map[y][x-1] instanceof Wolf))){
          move(map, x, y, x-1, y);
        }else if( (y < map.length-1) && ((map[y+1][x] instanceof Sheep) || (map[y+1][x] instanceof Wolf))){
          move(map, x, y, x, y+1);
        }else if((x > 0) && (y < map.length -1) && ((map[y+1][x-1] instanceof Sheep) || (map[y+1][x-1] instanceof Sheep))){
          move(map, x, y, x-1, y+1);
        }else{
          moveRandom(map, y, x);
        }
      }
    }
  }
}
