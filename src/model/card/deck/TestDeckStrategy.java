package model.card.deck;

import model.card.CardPile;
import model.card.ICardPile;

/**
 * Strategy that DeckBuilder can use to make a test UNO deck, this deck doesn't come with cards, the
 * yhave to be added later.
 * 
 * @author Sebastian
 *
 */
public class TestDeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    ICardPile Deck = new CardPile();
    return Deck;

  }

}
