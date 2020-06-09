/**
 * Sheep
 * Sheep species that can be put onto the board.
 */

public class Sheep extends Animal{
  private String gender;
  private int breedCooldown; // cooldown so that sheep dont continually breed 
  
  Sheep(int h, int x, int y){
    super(h, x, y);
    this.breedCooldown = 0;
    int genderDecider = (int)(Math.random()*2) + 1;
    if (genderDecider == 1){
      this.gender = "male";
    }else{
      this.gender = "female";
    }
  }
  public String getGender(){
    return this.gender;
  }
  public int getBreedCooldown(){
    return this.breedCooldown;
  }
  public void setBreedCooldown(){
    this.breedCooldown = 0;
  }
  /**
   * method to check if opposite sex
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   */
  public boolean ifOppositeSex(Entity map[][], int x, int y){
    if (!getGender().equals(((Sheep)map[y][x]).getGender())){
      return true;
    }else{
      return false;
    }
  }
    /**
   * method to breed
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   */
  public void breed(Entity map[][], int x, int y){
    if (ifOppositeSex(map, x, y) && breedCooldown >= 5 && ((Sheep)map[y][x]).getBreedCooldown() >= 5 && (getAge() >= 10)
          && ((Sheep)map[y][x]).getAge() >= 10){
      setBreedCooldown();
      ((Sheep)map[y][x]).setBreedCooldown();
      loseHealth(10);
      ((Sheep)map[y][x]).loseHealth(10);
      int newSheepX;
      int newSheepY;
      if (y - 1 >= 0 && map[y-1][x] == null){
        newSheepY = y-1;
        newSheepX = x;
      }else if(y + 1 < map.length && map[y+1][x] == null){
        newSheepY = y+1;
        newSheepX = x;
      }else if(x + 1 < map.length && map[y][x+1] == null ){
        newSheepY = y;
        newSheepX = x+1;
      }else if (x - 1 >= 0 && map[y][x-1] == null ){
        newSheepY = y;
        newSheepX = x-1;
      }else {        
        do{
          newSheepX = (int)(Math.random()*map.length);
          newSheepY = (int)(Math.random()*map.length);
        }while (map[newSheepY][newSheepX] != null);
      }
      map[newSheepY][newSheepX] = new Sheep(60, newSheepX, newSheepY);
      Ecosystem.sheepCount ++;
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
  public void eatGrass(Entity[][] map, int grassX, int grassY){
    gainHealth(((Grass)map[grassY][grassX]).getHealth()/4);
    map[grassY][grassX] = null;
    Ecosystem.grassCount --;
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
    if (map[destY][destX] instanceof Sheep){
      breed(map, destX, destY);
    }else{
      if (map[destY][destX] instanceof Grass){
        eatGrass(map, destX, destY); 
      }
      map[destY][destX] = map[y][x];
      map[y][x] = null;
      setX(destX);
      setY(destY);
    }
    setTired(true);
    loseHealth(1);
  }
  /**
   * method to make a decision on what to do
   * @param map takes in map.
   * @param x takes in coordinate on map
   * @param y takes in coordinate on map
   */
  public void decision(Entity[][] map, int y, int x){
    if (!getTired()){
      if (getHealth() <= 0 || getAge() >= 300){
        map[y][x] = null;
        Ecosystem.sheepCount --;
      }else{
        this.breedCooldown ++;
//        System.out.println(x + ":" + y +  ", " + getX() + ": " + getY() + " Health:" + getHealth() + " Age:" + getAge());
        if ((x > 0) && (y > 0) && (map[y-1][x-1] instanceof Grass )){
          move(map, x, y, x-1, y-1);
        }else if ((x < map.length-1) && (y < map.length -1) && (map[y+1][x+1] instanceof Grass)){
          move(map, x, y, x+1, y+1);
        }else if ((y > 0) && map[y-1][x] instanceof Grass){
          move(map, x, y, x, y-1);
        }else if ((x < map.length -1) && (y > 0) && (map[y-1][x+1] instanceof Grass)){
          move(map, x, y, x+1, y-1);
        }else if(( x < map.length -1) && (map[y][x+1] instanceof Grass)){
          move(map, x, y, x+1, y);
        }else if(( x > 0) && (map[y][x-1] instanceof Grass)){
          move(map, x, y, x-1, y);
        }else if( (y < map.length-1) && (map[y+1][x] instanceof Grass)){
          move(map, x, y, x, y+1);
        }else if((x > 0) && (y < map.length -1) && (map[y+1][x-1] instanceof Grass)){
          move(map, x, y, x-1, y+1);
        }else{
          moveRandom(map, y, x);
        }
      }
    }
  }
}


