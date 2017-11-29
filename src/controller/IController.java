package controller;

import model.card.type.COLOR;
import model.player.type.IPlayer;

/**
 * Interface for representing a UNO Game Controller
 * 
 * @author eriveros
 *
 */
public interface IController {

  /**
   * Asks for a color to a player
   * 
   * @return a color
   */

  public COLOR askForColor();

  /**
   * Displays that one player has said UNO
   * 
   * @param LastPlayer player that said UNO
   */
  public void SayUNO(IPlayer LastPlayer);

  /**
   * Plays a turn
   * 
   */
  public void playTurn();

  /**
   * Updates the played card in the view.
   */
  void updatePlayedCard();

  /**
   * Annonunces Winner
   * 
   * @param player Player that winned.
   */
  void announceWinner(IPlayer player);
}
