package model.card;

import java.util.ArrayList;
import java.util.HashMap;

import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.type.IPlayer;

/**
 * Interface of the Manager of the different piles of cards.
 * 
 * @author eriveros
 *
 */
public interface ICardPilesManager {
  /**
   * Returns the size of the discard pile.
   * @return size of the discard pile.
   */
  int sizeofDiscard();
  /**
   * Regenerates a deck using older discard pile cards.
   */
  void rebuildDeck();

  /**
   * Returns a card from the deck.
   * 
   * @return a Card from the deck.
   */
  ICard drawCard();

  /**
   * Returns the number of cards available to be drawn (Deck + Discard - the card that is played
   * right now)
   * 
   * @return number of cards available to be drawn
   */
  int getDrawableCardsNumber();

  /**
   * Returns an array with the first cardsNumber cards from the deck.
   * 
   * @param cardsNumber Number of cards to be drawn.
   * @return an array with cardsNumber cards.
   */
  ArrayList<ICard> drawCards(int cardsNumber);

  /**
   * Returns the card which is at top of discard pile right now.
   * 
   * @return top card of discard pile.
   */
  ICard getCurrentPlayedCard();

  /**
   * Puts a card on the discard pile.
   * 
   * @param newCard card to be put on discard pile.
   */
  void discard(ICard newCard);

  /**
   * Adds number of cards to player's hand.
   * 
   * @param player player receiving the cards.
   * @param number number of cards to be drawn.
   * @return ArrayList with the cards added to player.
   */
  ArrayList<ICard> addCardsToPlayer(IPlayer player, int number);
  /**
   * Returns a list of banned colors.
   * 
   * @return list of banned colors.
   */
  HashMap<COLOR, Integer> getBannedColors();
  /**
   * Adds a color to the banned Colors.
   * 
   */
  void addBannedColor(COLOR color);
}
