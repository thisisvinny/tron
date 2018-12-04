//-----------------------------------------------
//  State.java       Authors: Davon G. & Vincent Y.
//
//  Enumerated type: State
//  All the possible game states for this Tron game.
//-----------------------------------------------

public enum State 
{
  //---------------------------------------------------------
  //  Three possible game states:
  //   1) INIT: beginning state, game is setup, start menu shown
  //   2) PLAY: state where game is being played
  //   3) GAME_OVER: game is over
  //---------------------------------------------------------
  INIT, PLAY, GAME_OVER;
  
  //Declaration of instance variables
  //Represents the games current state
  private static State state;
  
  //---------------------------------
  //  getState ()
  //  Returns the state of the game.
  //---------------------------------
  public static State getState()
  {
    return state;
  }
  
  //---------------------------------
  //  setState (State s)
  //  Sets the state of the game.
  //---------------------------------
  public static void setState(State s)
  {
    state = s;
    Tron.println("State set to: " + s);
  }
  
}