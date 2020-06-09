/* [DisplayGrid.java]
 * A Small program for Display a 2D String Array graphically
 * @ Kyrollous
 */

// Graphics Imports
import javax.swing.*;
import java.awt.*;


class DisplayGrid { 
  
  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private Entity[][] world;
  private Color backgroundColor = new Color(34, 139, 34);
  
  DisplayGrid(Entity[][] w) { 
    this.world = w;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Map of World");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  public void refresh() { 
    frame.repaint();
  }
  
  class GridAreaPanel extends JPanel {
    Image maleSheep = Toolkit.getDefaultToolkit().getImage("maleSheep.png"); 
    Image femaleSheep = Toolkit.getDefaultToolkit().getImage("femaleSheep.png"); 
    Image babyWolf = Toolkit.getDefaultToolkit().getImage("babyWolf.png"); 
    Image adultWolf = Toolkit.getDefaultToolkit().getImage("adultWolf.png"); 
    Image grass = Toolkit.getDefaultToolkit().getImage("grass.png"); 
    Image flower = Toolkit.getDefaultToolkit().getImage("flower.png");
    Image creeper = Toolkit.getDefaultToolkit().getImage("creeper.png");
    Image nothing = Toolkit.getDefaultToolkit().getImage("nothing.png");
    Image exploded = Toolkit.getDefaultToolkit().getImage("exploded.png");
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      setDoubleBuffered(true); 
//      g.setColor(backgroundColor);
      
      for(int i = 0; i<world[0].length;i=i+1)      { 
        for(int j = 0; j<world.length;j=j+1)         { 
          g.drawImage(nothing,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          if (world[i][j] instanceof ExplodedArea){
            g.drawImage(exploded,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);          
          }else if (world[i][j] instanceof Sheep && ((Sheep)world[i][j]).getGender().equals("male")){    //This block can be changed to match character-color pairs
            g.drawImage(maleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if (world[i][j] instanceof Sheep && ((Sheep)world[i][j]).getGender().equals("female")){
            g.drawImage(femaleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if (world[i][j] instanceof Grass && world[i][j].getHealth() >= 30){
            g.drawImage(flower,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if (world[i][j] instanceof Grass && world[i][j].getHealth() < 30){
            g.drawImage(grass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if(world[i][j] instanceof Wolf && ((Wolf)world[i][j]).getAge() < 80 ){
            g.drawImage(babyWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if(world[i][j] instanceof Wolf && ((Wolf)world[i][j]).getAge() >= 80 ){
            g.drawImage(adultWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if(world[i][j] instanceof Creeper){
            g.drawImage(creeper,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this); 
          } 
          g.setColor(Color.BLACK);
          // g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
          g.drawString("Sheep Alive: " + Ecosystem.sheepCount, 1200, 200); // display how many of each are still alive
          g.drawString("Wolf Alive: " + Ecosystem.wolfCount, 1200, 300);
          g.drawString("Grass Alive: " + Ecosystem.grassCount, 1200, 400);
        }
      }
    }
  }//end of GridAreaPanel
  
} //end of DisplayGrid