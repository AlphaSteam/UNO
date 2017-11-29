package model.card.type;

import java.util.HashMap;

import controller.IController;
import model.IGameLogic;

/**
 * Interface for defining UNO Cards.
 * 
 * @author eriveros
 *
 */
public interface ICard {

  /**
   * It defines if a card is playable over another card.
   * 
   * @param otherCard The card below this card.
   * @param map list of banned colors with the turns left until unbanned.
   * @return True if the card is playable over the other one. False otherwise.
   */
  boolean isPlayableOver(ICard otherCard, HashMap<COLOR, Integer> map);

  /**
   * Returns true if the card is able to be the first card played on a game.
   * 
   * @return True if the above condition is true. False otherwise.
   */
  boolean isFirstPlayable();

  /**
   * Returns the color of the card
   * 
   * @return the card's color
   */
  COLOR getColor();

  /**
   * Returns the symbol in the card (Number or action in special colored cards)
   * 
   * @return card's symbol.
   */
  Symbol getSymbol();

  /**
   * Executes the action of the card and notifies it was played to the controller.
   * 
   * @param game Game Logic object
   * @param ctrl Controller used by the game.
   */
  void executeAction(IGameLogic game, IController ctrl);

  /**
   * Returns true if the card is discardable on the discard pile.
   * 
   * @return true if the card is discardable on the discard pile.
   */
  boolean isDiscardable();

}
