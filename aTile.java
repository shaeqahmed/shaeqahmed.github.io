import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class aTile extends JComponent{
    boolean isBlack = false; // tile starts off as unactive
    boolean isGray = false;
    boolean isYellow = false;
    boolean isRed = false;
    int length = 150; // the length of a tile
    int width = 105;  // the width of a tile
    int x; // for the x coord of a tile
    int y; // for the y coord of a tile

    public aTile(int x, int y){  // this constructor method is used to create a tile at the given x and y coords
        super(); // makes a JComponent
        this.length = length; // sets length 
        this.width = width; // sets width
        this.x = x;  // assigns the given x coord
        this.y = y;  // assigns the given y coord
        repaint(); // updates the graphic, in this case making it visible for the first time though
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // initializes
        if(isBlack){
          g.setColor(Color.BLACK);
        }
        else if (isGray){
          g.setColor(Color.GRAY);
        }
        else if (isYellow){
          g.setColor(Color.YELLOW);
        }
        else if (isRed){
          g.setColor(Color.RED);
        }
        else {
          g.setColor(Color.WHITE);
          }
        g.fillRect(x, y, width, length);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, length);
    
    }

    public void makeBlack(){
        isBlack = true;
        isGray = false;
        isYellow = false;
        isRed = false;
    }
    public void makeGray(){
      isGray = true;
      isBlack = false;
      isYellow = false;
      isRed = false;
    }
       
    public void makeYellow(){
        isBlack = false;
        isGray = false;
        isYellow = true;
        isRed = false;
    }
    public void makeWhite(){
        isBlack = false;
        isGray = false;
        isYellow = false;
        isRed = false;
    }
    public void makeRed(){
      isBlack = false;
      isGray = false;
      isYellow = false;
      isRed = true;
    }
    public void move(){
      this.y += 150;
      
    }
    public int getY(){
        return y;
    }
}