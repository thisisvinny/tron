//-----------------------------------------------
//  Tron.java       Authors: Davon G. & Vincent Y.
//
//  Driver class for the Tron game.
//  Used with Cell.java, Controller.java, Grid.java, State.java
//  2-Player game where the players try to crash the other player.
//-----------------------------------------------

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Tron 
{
  //Public static void println(Object o)
  public static void println(Object o)
  {
    System.out.println(o);
  }
  
  //Declare constants
  private static final int UNIT_SIZE = 12;
  private static final int GRID_SIZE = 40;
  private static final int SIZE = (UNIT_SIZE + 2) * GRID_SIZE + 2;
  
  //Declaration of instance variables
  private static JFrame frame;
  private static Grid grid;
  
  //Boolean used to loop the game until it is over
  private static boolean running = false;
  
  public static void main(String[] args) 
  {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    //Initialize instance variables
    println("Welcome to TRON");
    frame = new JFrame("TRON");
    grid = new Grid(UNIT_SIZE, GRID_SIZE);
    
    //Set game state to INIT
    State.setState(State.INIT);
    
    //Create game panel and arena
    //Add controller to make game playable
    grid.setPreferredSize(new Dimension(SIZE, SIZE));
    grid.setFocusable(true);
    grid.addKeyListener(new Controller());
    frame.setResizable(false);
    frame.setBackground(Color.BLACK);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(grid);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    
    //Loop to run the game
    running = true;
    println("Game is running");
    println("Starting game loop...");
    while (running) {
      //Update the frame as the game is played
      frame.repaint();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  //--------------------------------------
  //  getUnitSize ()
  //  Returns the unit size constant.
  //--------------------------------------
  public static int getUnitSize() 
  {
    return UNIT_SIZE;
  }
  
  //-------------------------------------------
  //  getGridSize ()
  //  Returns the grid size constant.
  //-------------------------------------------
  public static int getGridSize() 
  {
    return GRID_SIZE;
  }
  
  //------------------------------------------
  //  getSize ()
  //  Returns the size constant of the grid.
  //------------------------------------------
  public static int getSize() 
  {
    return SIZE;
  }
  
  //-------------------------------------------
  //  getGrid ()
  //  Returns the Grid object (game arena).
  //-------------------------------------------
  public static Grid getGrid() 
  {
    return grid;
  }
  
  //--------------------------------------------------
  //  isRunning ()
  //  Returns boolean running, which determines whether the game is still running or not.
  //--------------------------------------------------
  public static boolean isRunning() 
  {
    return running;
  }
  
  //---------------------------------------------------
  //  setRunning()
  //  Sets boolean running, which determines whether the game is still running or not.
  //---------------------------------------------------
  public static void setRunning(boolean r)
  {
    running = r;
  }
  
}