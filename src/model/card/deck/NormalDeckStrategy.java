package model.card.deck;

import java.util.Arrays;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.CardComodin4;
import model.card.type.CardComodinColor;
import model.card.type.CardInvertir;
import model.card.type.CardNum;
import model.card.type.CardRobar2;
import model.card.type.CardSaltar;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.card.type.Symbol;

/**
 * Strategy that DeckBuilder can use to make a normal UNO deck, this consists of 108 cards, 76
 * Numeric cards, 24 action cards and 8 wild cards.
 * 
 * @author Sebastian
 *
 */
public class NormalDeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    {
      ICardPile Deck = new CardPile();
      for (COLOR col : COLOR.getColors()) {
        for (Symbol sym : Symbol.getNumeric()) {
          ICard newCard = new CardNum(col, sym);
          Deck.pushCard(newCard);
        }
      }
      for (COLOR col : COLOR.getColors()) {
        for (Symbol sym : Arrays.copyOfRange(Symbol.values(), 1, 10)) {
          ICard newCard = new CardNum(col, sym);
          Deck.pushCard(newCard);
        }
      }
      for (COLOR col : COLOR.getColors()) {
        ICard newCard = new CardSaltar(col);
        ICard newCard2 = new CardSaltar(col);
        Deck.pushCard(newCard);
        Deck.pushCard(newCard2);
      }
      for (COLOR col : COLOR.getColors()) {
        ICard newCard = new CardInvertir(col);
        ICard newCard2 = new CardInvertir(col);
        Deck.pushCard(newCard);
        Deck.pushCard(newCard2);
      }
      for (COLOR col : COLOR.getColors()) {
        ICard newCard = new CardRobar2(col);
        ICard newCard2 = new CardRobar2(col);
        Deck.pushCard(newCard);
        Deck.pushCard(newCard2);
      }
      for (int i = 0; i < 4; i++) {
        ICard newCard = new CardComodinColor();
        Deck.pushCard(newCard);
      }
      for (int i = 0; i < 4; i++) {
        ICard newCard = new CardComodin4();
        Deck.pushCard(newCard);

      }
      return Deck;
    }
  }

}
