package model.card.deck;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.COLOR;
import model.card.type.CardNum;
import model.card.type.CardZAWARUDO;
import model.card.type.ICard;
import model.card.type.Symbol;

/**
 * Strategy that DeckBuilder can use to make a normal UNO deck, this consists of 70 ZAWARUDO Cards
 * and one Numeric Card.
 * 
 * @author Sebastian Alfaro
 *
 */
public class ZAWARUDODeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    {
      ICardPile Deck = new CardPile();

      for (int i = 0; i < 70; i++) {
        ICard newCard3 = new CardZAWARUDO();
        Deck.pushCard(newCard3);
      }
      ICard card = new CardNum(COLOR.RED, Symbol.EIGHT);
      Deck.pushCard(card);
      return Deck;
    }
  }

}
