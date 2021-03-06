/**
 * Entity
 * Entities that are able to be put on the board and interact with each other
 */
abstract public class Entity{
  private int health;
  private int x;
  private int y;
  
  Entity(int h, int x, int y){
    this.health = h;
    this.x = x;
    this.y = y;
  } 
  public void gainHealth(int amountGained){
    this.health += amountGained;
  }
  public void loseHealth(int amountLost){
    this.health -= amountLost;
  }
  public int getHealth(){
    return this.health;
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  abstract public void decision(Entity[][] map, int y, int x);
  
}