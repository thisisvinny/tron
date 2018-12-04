//------------------------------------------
//  Cell.java        Authors: Davon G. & Vincent Y.
//
//  Enumerated type: Cell
//  All the possible things/objects on the game arena.
//------------------------------------------

public enum Cell 
{
  //--------------------------------------------
  //  Six possible things/objects on the game arena:
  //   1) WALL: sides/ends of the arena
  //   2) EMPTY: an empty spot where players can move to
  //   3) PLAYER1: player 1's current position
  //   4) PLAYER2: player 2's current position
  //   5) PLAYER1_TAIL: player 1's tail's position
  //   6) PLAYER2_TAIL: player 2's tail's position
  //---------------------------------------------
  WALL (-3), EMPTY (0), PLAYER1 (1), PLAYER1_TAIL(-1), PLAYER2 (2), PLAYER2_TAIL(-2);
  
  //Declaration of instance variables
  //Represents each "thing" on the game arena depending on the value
  private int value;
  
  //---------------------------------------------------
  //  Cell (int v)
  //  Constructs Cell object with a value that represents one of the enumerated objects above.
  //---------------------------------------------------
  private Cell(int v)
  {
    value = v;
  }
  
  //------------------------------------------------------
  //  getValue ()
  //  Returns the value of the Cell object.
  //------------------------------------------------------
  public int getValue()
  {
    return value;
  }
  
}