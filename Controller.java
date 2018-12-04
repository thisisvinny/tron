//------------------------------------------------
//  Controller.java       Authors: Davon G. & Vincent Y.
//
//  Controller for the game. Controls movements of players.
//------------------------------------------------

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener 
{
  //Gets the game arena
  private Grid d = Tron.getGrid();
  
  //----------------------------------------
  //  keyPressed (KeyEvent e)
  //   1) Player1 key map: up, down, left, right keys
  //   2) Player2 key map: w, a, s, d keys
  //----------------------------------------
  public void keyPressed(KeyEvent e) 
  {
    int key = e.getKeyCode();
    if (State.getState() == State.PLAY) 
    {
      if (key == KeyEvent.VK_UP)
        d.setPlayer1D(1);
      else if (key == KeyEvent.VK_RIGHT)
        d.setPlayer1D(2);
      else if (key == KeyEvent.VK_DOWN)
        d.setPlayer1D(3);
      else if (key == KeyEvent.VK_LEFT)
        d.setPlayer1D(4);
      else if (key == KeyEvent.VK_W)
        d.setPlayer2D(1);
      else if (key == KeyEvent.VK_D)
        d.setPlayer2D(2);
      else if (key == KeyEvent.VK_S)
        d.setPlayer2D(3);
      else if (key == KeyEvent.VK_A)
        d.setPlayer2D(4);
    } 
    else if (State.getState() == State.INIT)
    {
      if (key == KeyEvent.VK_ENTER)
        State.setState(State.PLAY);
    } 
    else if (State.getState() == State.GAME_OVER)
    {
      if (key == KeyEvent.VK_R) {
        d.reset();
        State.setState(State.PLAY);
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}
  
}