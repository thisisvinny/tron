//----------------------------------------------------
//  Grid.java       Authors: Davon G. & Vincent Y.
//
//  Logic and arena of the game are dealt with here.
//----------------------------------------------------

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Grid extends JPanel 
{
  //Declaration of constants
  private final int UNIT_SIZE;
  private final int GRID_SIZE;
  private final int SIZE;
  
  //2D array of enumerated Cell objects, this is the game arena
  private Cell[][] arena;
  
  //Declaration of instance variables
  private int player1X, player1Y, player1D;
  private int player2X, player2Y, player2D;
  
  //Variable that represents the winner of the game
  private String winner = "";
  
  //Colors for player1, player1's tail, player2, and player2's tail
  private Color player1 = new Color(00, 0xF3, 0xF9);
  private Color player1tail = new Color(0xB2, 0xF0, 0xFF);
  private Color player2 = new Color(0xB9, 0x12, 0xFF);
  private Color player2tail = new Color(0xFF, 0x18, 0xF6);
  
  //------------------------------------
  //  Grid (int uS, int gS)
  //  Constructor method for a Grid object.
  //------------------------------------
  public Grid(int unitSize, int gridSize) 
  {
    Tron.println("Contructing grid...");
    
    //Initialize constants
    UNIT_SIZE = unitSize;
    GRID_SIZE = gridSize;
    SIZE = (UNIT_SIZE + 2) * GRID_SIZE + 2;
    
    //Initialize instance variables
    player1X = GRID_SIZE;
    player1Y = 1;
    player1D = 4;
    
    player2X = 1;
    player2Y = GRID_SIZE;
    player2D = 2;
    
    arena = new Cell[GRID_SIZE + 2][GRID_SIZE + 2];
    initArena();
  }
  
  //-----------------------------------------
  //  paintComponent (Graphics g)
  //  Draws appropriate drawings depending on game state.
  //-----------------------------------------
  public void paintComponent(Graphics g) 
  {
    State current = State.getState();
    if (current == State.INIT) 
    {
      // Print start menu and directions
      g.setColor(player1);
      g.setFont(new Font("Verdana", Font.BOLD, 36));
      g.drawString("TRON", 230, 46);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Arial", Font.PLAIN, 20));
      g.drawString("by Davon G. and Vincent Yu", 170, 76);
      g.drawString("The goal of the game is to survive longer than your opponent. ", 10, 106);
      g.drawString("If your opponent crashes into your tail or a wall, you win, and if you crash into a ", 10, 126);
      g.drawString("wall or your opponent's tail, you lose.", 10, 146);
      g.drawString(" Use the controls below and press ENTER when you're ready!", 10, 166);

      // print controls
      g.setColor(Color.GRAY);

      g.setColor(player1);
      g.drawString("Player 1", 10, 396);
      g.drawString("UP Arrow    - up", 10, 421);
      g.drawString("DOWN Arrow  - down", 10, 446);
      g.drawString("LEFT Arrow  - left", 10, 471);
      g.drawString("RIGHT Arrow - right", 10, 496);

      g.setColor(player2);
      g.drawString("Player 2", 350, 396);
      g.drawString("W Key - up", 350, 421);
      g.drawString("A Key - left", 350, 446);
      g.drawString("S Key - down", 350, 471);
      g.drawString("D Key - right", 350, 496);
    } 
    else if (current == State.PLAY) 
    {
      tick(); //Progresses the game
      drawArena(g); //Updates the arena during playing state
    } 
    else if (current == State.GAME_OVER) 
    {
      //Print "GAME OVER" message on screen and the winner
      g.setFont(new Font("Arial", Font.BOLD, 36));
      if (winner.equals("player1")) 
      {
        g.setColor(player1);
        g.drawString("GAME OVER!", 191, 299);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player 1 WON!", 191, 299 + 30);
      } 
      else if (winner.equals("player2")) 
      {
        g.setColor(player2);
        g.drawString("GAME OVER!", 191, 299);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player 2 WON!", 191, 299 + 30);
      } 
      else 
      {
        g.setColor(Color.GRAY);
        g.drawString("GAME OVER!", 191, 299);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("It\'s a draw!", 191, 299 + 30);
      }
      g.drawString("Press \'R\' to restart...", 191, 299 + 70);
    }
  }
  
  //-------------------------------------------
  //  tick()
  //  Progresses the game. Moves the players one unit ahead and increase tail lengths.
  //-------------------------------------------
  public void tick()
  {
    //Increase player1's tail length by adding its current position
    arena[player1X][player1Y] = Cell.PLAYER1_TAIL;
    
    //Move player1's location 1 unit ahead in corresponding direction
    if (player1D == 1) 
    {
      player1Y--;
    } 
    else if (player1D == 2)
    {
      player1X++;
    } 
    else if (player1D == 3) 
    {
      player1Y++;
    } 
    else if (player1D == 4)
    {
      player1X--;
    }
    
    //Increase player2's tail length by adding its current position
    arena[player2X][player2Y] = Cell.PLAYER2_TAIL;
    
    //Move player2's direction 1 unit ahead in corresponding direction
    if (player2D == 1) 
    {
      player2Y--;
    } 
    else if (player2D == 2) 
    {
      player2X++;
    } 
    else if (player2D == 3) 
    {
      player2Y++;
    } 
    else if (player2D == 4) 
    {
      player2X--;
    }
    
    //Checks to see if the game is a draw by checking if the cells in front of both player1 and player2 are empty
    if (arena[player1X][player1Y] != Cell.EMPTY && arena[player2X][player2Y] != Cell.EMPTY) 
    {
      Tron.println("DRAW");
      State.setState(State.GAME_OVER);
      
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return;
    }
    
    //If next location is empty, player1 moves ahead 1 unit
    if (arena[player1X][player1Y] == Cell.EMPTY) 
    {
      arena[player1X][player1Y] = Cell.PLAYER1;
    } 
    //If not, player1 loses
    else 
    {
      winner = "player2";
      Tron.println("Player1 Died");
      State.setState(State.GAME_OVER);
      
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return;
    }
    
    //If next location is empty, player2 moves ahead 1 unit
    if (arena[player2X][player2Y] == Cell.EMPTY) 
    {
      arena[player2X][player2Y] = Cell.PLAYER2;
    } 
    //If not, player2 loses
    else 
    {
      winner = "player1";
      Tron.println("Player2 Died");
      State.setState(State.GAME_OVER);
      
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  //--------------------------------------------
  //  drawArena (Graphics g)
  //  Updates arena during playing state by redrawing it.
  //--------------------------------------------
  private void drawArena(Graphics g) 
  {
    if (State.getState() == State.GAME_OVER)
      return;
    //Loop through 2D array and draw the arena
    for (int x = 1; x < GRID_SIZE + 1; x++) 
    {
      for (int y = 1; y < GRID_SIZE + 1; y++) 
      {
        if (arena[x][y] == Cell.PLAYER1) 
        {
          g.setColor(player1);
        } 
        else if (arena[x][y] == Cell.PLAYER2) 
        {
          g.setColor(player2);
        } 
        else if (arena[x][y] == Cell.PLAYER1_TAIL) 
        {
          g.setColor(player1tail);
        } 
        else if (arena[x][y] == Cell.PLAYER2_TAIL) 
        {
          g.setColor(player2tail);
        } 
        else 
        {
          continue;
        }
        g.fillRect(2 + (x - 1) * (UNIT_SIZE + 2), 2 + (y - 1) * (UNIT_SIZE + 2), UNIT_SIZE, UNIT_SIZE);
      }
    }
  }
  
  //-----------------------------------------
  //  printArena ()
  //  Prints the arena in interactions pane.
  //-----------------------------------------
  private void printArena() 
  {
    for (int x = 1; x < GRID_SIZE + 1; x++) 
    {
      for (int y = 1; y < GRID_SIZE + 1; y++) 
      {
        System.out.print(arena[x][y] + "\t");
      }
      Tron.println("");
    }
  }
  
  //----------------------------------------
  //  addWalls()
  //  Make sides/edges of the arena the walls.
  //----------------------------------------
  private void addWalls() 
  {
    Tron.println("Adding outer walls to the arena...");
    //Add walls to top and bottom sides
    for (int x = 0; x < GRID_SIZE + 2; x++) 
    {
      arena[x][0] = Cell.WALL;
      arena[x][GRID_SIZE + 1] = Cell.WALL;
    }
    //Add walls to left and right sides
    for (int y = 0; y < GRID_SIZE + 2; y++) 
    {
      arena[0][y] = Cell.WALL;
      arena[GRID_SIZE + 1][y] = Cell.WALL;
    }
  }
  
  //---------------------------------------
  //  initArena ()
  //  Clears and resets arena by making all cells empty then adds walls again.
  //---------------------------------------
  private void initArena() 
  {
    Tron.println("Clearing arena...");
    //Loops through 2D array and sets each cell to empty
    for (int x = 0; x < GRID_SIZE + 2; x++) 
    {
      for (int y = 0; y < GRID_SIZE + 2; y++) 
      {
        arena[x][y] = Cell.EMPTY;
      }
    }
    //Add walls to arena
    addWalls();
  }
  
  //--------------------------------------
  //  reset()
  //  Resets the game by resetting all variables to their default values.
  //--------------------------------------
  public void reset() 
  {
    Tron.println("Resetting...");
    
    //Reset winner
    winner = "";
    
    //Reset locations
    player1X = GRID_SIZE;
    player1Y = 1;
    player1D = 4;
    
    player2X = 1;
    player2Y = GRID_SIZE;
    player2D = 2;
    
    //Clear and reset arena
    initArena();
  }
  
  //-----------------------------------
  //  getPlayer1D ()
  //  Returns player1's direction.
  //-----------------------------------
  public int getPlayer1D() 
  {
    return player1D;
  }
  
  //-------------------------------------
  //  setPlayer1D (int d)
  //  Sets player1's direction.
  //-------------------------------------
  public void setPlayer1D (int d) 
  {
    //If the sum of old and new direction is 4, then the player is trying to go from up to down, or down to up
    //If the sum of old and new direction is 6, then the player is trying to go from left to right, or right to left
    //Both scenarios are not allowed in the game
    if (d + this.player1D != 4 && d + this.player1D != 6) 
    {
      this.player1D = d;
      Tron.println("Player 1's direction is now " + d);
    }
  }
  
  //---------------------------------------
  //  getPlayer2D ()
  //  Returns player2's direction.
  //---------------------------------------
  public int getPlayer2D() 
  {
    return player2D;
  }
  
  //-----------------------------------------
  //  setPlayer2D (int d)
  //  Sets player2's direction.
  //-----------------------------------------
  public void setPlayer2D (int d) 
  {
    //If the sum of old and new direction is 4, then the player is trying to go from up to down, or down to up
    //If the sum of old and new direction is 6, then the player is trying to go from left to right, or right to left
    //Both scenarios are not allowed in the game
    if (d + this.player2D != 4 && d + this.player2D != 6) 
    {
      this.player2D = d;
      Tron.println("Player 2's direction is now " + d);
    }
  }
  
}