package model.card.deck;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.COLOR;
import model.card.type.CardBan;
import model.card.type.ICard;

/**
 * Strategy that DeckBuilder can use to make a normal UNO deck, this consists of 108 cards, 76
 * Numeric cards, 24 action cards and 8 wild cards.
 * 
 * @author Sebastian
 *
 */
public class BanDeckStrategy implements IDeckStrategy {

  @Override
  public ICardPile createDeck() {
    {
      ICardPile Deck = new CardPile();

      for (COLOR col : COLOR.getColors()) {
        ICard newCard = new CardBan(col);
        ICard newCard2 = new CardBan(col);
        Deck.pushCard(newCard);
        Deck.pushCard(newCard2);
        for(int i=0;i<80;i++){
          ICard newCard3 = new CardBan(col);
          Deck.pushCard(newCard3);
        }
      }
      return Deck;
    }
  }

}
