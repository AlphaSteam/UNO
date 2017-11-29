package model.card.deck;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.COLOR;
import model.card.type.CardNum;
import model.card.type.CardZAWARUDO;
import model.card.type.ICard;
import model.card.type.Symbol;

/**
 * Strategy that DeckBuilder can use to make a normal UNO deck, this consists of 108 cards, 76
 * Numeric cards, 24 action cards and 8 wild cards.
 * 
 * @author Sebastian
 *
 */
public class ZAWARUDODeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    {
      ICardPile Deck = new CardPile();

      for(int i=0;i<70;i++){
        ICard newCard3 = new CardZAWARUDO();
        Deck.pushCard(newCard3);
      }
      ICard card = new CardNum(COLOR.RED, Symbol.EIGHT);
      Deck.pushCard(card);
      return Deck;
    }
  }

}
