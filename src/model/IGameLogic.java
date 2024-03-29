package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.IController;
import model.card.ICardPilesManager;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.type.IPlayer;

/**
 * Represents the complete game logic.
 * 
 * @author eriveros
 *
 */
public interface IGameLogic {
  /**
   * returns the CardPilesManager
   * 
   * @return CardPilesManager
   */
  public ICardPilesManager getCardManager();

  /**
   * Returns true if the game has ended. False otherwise.
   * 
   * @return true if game has ended.
   */
  boolean hasEnded();

  /**
   * Returns the current player.
   * 
   * @return current player
   */
  IPlayer getCurrentPlayer();

  /**
   * Returns the current played card.
   * 
   * @return current played card
   */
  ICard getCurrentPlayedCard();

  /**
   * Returns the last player
   * 
   * @return last player
   */
  IPlayer getLastPlayer();

  /**
   * Returns the next player
   * 
   * @return next player
   */
  IPlayer getNextPlayer();

  /**
   * Allows to autoshout UNO if a player has only one card.
   * 
   * @param ctrl ConsoleController that controles what happens each turn in the game.
   */
  void autoShoutUNO(IController ctrl);

  /**
   * Allows to autoshout UNO if a player has only one card after he played a ZAWARUDO card.
   * 
   * @param ctrl ConsoleController that controles what happens each turn in the game.
   */
  void shoutUNONOW(IController ctrl);

  /**
   * Prepares the turn to be initiated, changing the actual player, shouting UNO automatically,
   * assigning cards to players if the Draw Well is not empty
   * 
   * @param ctrl Controller used by the game.
   */
  void startTurn(IController ctrl);

  /**
   * Skips the next player's turn.
   */
  void skipPlayer();

  /**
   * Stops time, letting the player play another card.
   */
  void StopTime();

  /**
   * Adds cards to the draw well.
   * 
   * @param number Number of cards to be added to the DrawWell.
   */
  void addToDrawWell(int number);

  /**
   * Sets the number of cards in the draw well to zero.
   */
  void resetDrawWell();

  /**
   * Returns true if the draw well is empty.
   * 
   * @return true if the draw well is empty.
   */
  boolean isDrawWellEmpty();

  /**
   * Draws cards from well and announces it to the controller.
   * 
   * @param player player which is drawing the cards
   * @param ctrl controller used by the game.
   */
  void drawCardsFromWell(IPlayer player, IController ctrl);

  /**
   * Inverts the direction of the game
   */
  void invertDirection();

  /**
   * Plays a card, executing its action and putting it in the discard pile.
   * 
   * @param playedCard card played by the player.
   * @param ctrl controller of the game
   * @return true if the card was played.
   */
  boolean playCard(ICard playedCard, IController ctrl);

  /**
   * Draws one card from the deck. Also sets some state variables.
   * 
   * @param player Player that is playing the card.
   * @return the card being played.
   */
  ICard drawOneCard(IPlayer player);

  /**
   * Announces the winner of the game, if there's any.
   * 
   * @param ctrl Controller of the game.
   * @param player Player that won.
   */
  void announceWinner(IController ctrl, IPlayer player);

  /**
   * Returns the list of players of the game
   * 
   * @return the list of players of the game
   */
  ArrayList<IPlayer> getPlayers();

  /**
   * Bans a color for the next three turns
   * 
   * @param color String corresponding to the color that will be banned.
   */
  void BanColor(String color);

  /**
   * Updates the number of turns left for each banned color.
   */
  void UpdateBans();

  /**
   * Returns list of banned colors.
   * 
   * @return banned colors.
   */
  public HashMap<COLOR, Integer> getBannedColors();

}
