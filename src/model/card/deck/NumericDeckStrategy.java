package model.card.deck;

import java.util.Arrays;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.CardNum;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;

/**
 * Strategy that DeckBuilder can use to make a numeric UNO deck, this consists of 76 Numeric cards.
 * 
 * @author Sebastian
 *
 */
public class NumericDeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    ICardPile Deck = new CardPile();
    for (Color col : Color.getColors()) {
      for (Symbol sym : Symbol.getNumeric()) {
        ICard newCard = new CardNum(col, sym);
        Deck.pushCard(newCard);
      }
    }
    for (Color col : Color.getColors()) {
      for (Symbol sym : Arrays.copyOfRange(Symbol.values(), 1, 10)) {
        ICard newCard = new CardNum(col, sym);
        Deck.pushCard(newCard);
      }
    }
    return Deck;
  }


}
