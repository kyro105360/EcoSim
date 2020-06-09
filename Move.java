/**
 * Movement
 * Interface that enables species to move around the board.
 */ 
public interface Move{
  public void move(Entity[][] map, int x, int y, int destX, int destY);
}