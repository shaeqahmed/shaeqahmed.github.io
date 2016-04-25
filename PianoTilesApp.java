import javax.swing.*;
import java.awt.*;
//import java.util.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;

public class PianoTilesApp extends JApplet {

    public void init() {
        setSize(435, 640);
        setLayout(new BorderLayout());
        add(new PianoTiles());
    }


class PianoTiles extends JPanel{
    
  aTile[][] gameboard = new aTile[4][4]; // the piano tiles gameboard is created with capability of four rows and four columns of tiles. 
  //private JLabel label = new JLabel("0");
  int score = 0;// to keep track of the number of black tiles the user has sucessfully pressed.
  double time = 0.00;
  JLabel label = new JLabel("9.00\"");
  boolean started = false;
  int tempX, tempY;
  DecimalFormat df;
  Timer timer;
  ActionListener actionListener;
  public PianoTiles(){
    super(); // creates a Jpanel
    this.setSize(420, 600); // we want the size to be just enough to fit a 4x4 tiles board.
    tempX = 0; // x coord of first tile
    tempY = 450; // y coord of first tile
    //using two nested four loops we fill the gameboard with four rows and clumns of tiles,
    //making one in each row black using a random number generating helper function.
    
    df = new DecimalFormat(".00");
    actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        if (score>0){
          time++;
          if(time < 450.0){
            label.setText(String.valueOf(df.format(9.00-(time/50.)))+"\"");
          }
          else if (time == 450.0){
            label.setText(String.valueOf("Score: "+score));
          }
        }
      }
    };
    timer = new Timer(20, actionListener);
    timer.start();
    for(int y = 0; y<4; y++){
      int randomtile = randomfour();
      for(int x = 0; x<4; x++){
        gameboard[x][y] = new aTile(tempX, tempY);
        if(x == randomtile && y != 0){
          gameboard[x][y].makeBlack(); //activating a tile in each row...
        }
        if (y == 0){
          gameboard[x][y].makeYellow();
        } 
        tempX+=105;
      }
      tempY-=150;
      tempX = 0;
    }
    label.setVisible(true);
    label.setForeground(Color.red);
    label.setFont(new Font("Calibri",1,40));
    
    this.add(label);
    MyMouseAdapter myMouseAdapter = new MyMouseAdapter(); 
    addMouseListener(myMouseAdapter);
  }
  public int randomfour(){
    return (int)(Math.random()*4);// random number generator with range of four to determine which tile in a row will be made balck.
  }
  
  public void reset(){
    score = 0;// to keep track of the number of black tiles the user has sucessfully pressed.
    time = 0.00;
    label.setText("9.00\"");
    started = false;
    tempX = 0; // x coord of first tile
    tempY = 450; // y coord of first tile
    for(int y = 0; y<4; y++){
      int randomtile = randomfour();
      for(int x = 0; x<4; x++){
        gameboard[x][y].makeWhite();
        if(x == randomtile && y != 0){
          gameboard[x][y].makeBlack(); //activating a tile in each row...
        }
        if (y == 0){
          gameboard[x][y].makeYellow();
        } 
        tempX+=105;
      }
      tempY-=150;
      tempX = 0;
    }
  }
  
  
  
  class MyMouseAdapter extends MouseAdapter {
    public void moveBoard(){
      if (!started){
        started = true;
      }
      for(int x = 0; x<4; x++){
        gameboard[x][0].makeWhite();
      }
      for(int y = 1; y<4; y++){
        for(int x = 0; x<4; x++){
          if (gameboard[x][y].isBlack || gameboard[x][y].isGray){
            if (gameboard[x][y].isBlack) gameboard[x][y-1].makeBlack();
            if (gameboard[x][y].isGray) gameboard[x][y-1].makeGray();
            gameboard[x][y].makeWhite();
            break;
          }
        }
      }
      gameboard[randomfour()][3].makeBlack();
    }
    
    
    
    
    
    public void mousePressed(java.awt.event.MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      int desiredTile = 0;
      int desiredX = 0;
      int desiredY = 0;
      
      for(int a = 0; a<4; a++){
        if(gameboard[a][1].isBlack){
          desiredTile = a;
        }
      }
      
      desiredX = desiredTile*105;
      desiredY = gameboard[desiredTile][1].getY();
      boolean inrange = (x>=desiredX && x<=(desiredX+105) && y>=desiredY && y<=(desiredY+150));// was the click "on" the tile?
      if(inrange){
        score++;
        gameboard[desiredTile][1].makeGray();
        moveBoard(); //progress the game by one tile.
      }
      else {
        reset();
      }
      repaint();
    }
  }
  
  
  
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    for(int y = 0; y<4; y++){
      for(int x = 0; x<4; x++){
        gameboard[x][y].paintComponent(g);
      }
    }
  }

}
}
  //PianoTiles.add(label);
  
  // 
  // label.setFont(new Font("Verdana",1,40));
  
//  public static void main(String[] args){
//    PianoTiles PianoTiles = new PianoTiles();
//    JFrame Game = new JFrame();
//    Game.add(PianoTiles);
//    Game.setSize(435,640);
//    Game.setVisible(true);
//  }
