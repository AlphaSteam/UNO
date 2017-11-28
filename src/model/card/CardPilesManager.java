package model.card;

import java.util.ArrayList;
import java.util.HashMap;

import model.card.type.COLOR;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.player.type.IPlayer;

public class CardPilesManager implements ICardPilesManager {
  protected ICardPile Deck;
  protected ICardPile Discard = new CardPile();
  protected HashMap<COLOR,Integer> Banned = new HashMap<>();

  public CardPilesManager(ICardPile deck) {
    this.Deck = deck;
    Deck.shuffle();
    ICard C = Deck.popCard();
    while (!C.isFirstPlayable()) {
      Deck.pushCard(C);
      Deck.shuffle();
      C = Deck.popCard();

    }
    discard(C);
  }

  @Override
  public void rebuildDeck() {
    for (int i = 0; i < Discard.getSize(); i++) {
      Deck.pushCard(Discard.popCard());
    }
    Deck.shuffle();

  }

  @Override
  public ICard drawCard() {
    if (this.Deck.getSize() > 1) {
      return Deck.popCard();
    } else {
      rebuildDeck();
      if (this.Deck.getSize() > 1) {
        return Deck.popCard();
      } else {
        return new NullCard();
      }
    }
  }

  @Override
  public int getDrawableCardsNumber() {
    return Deck.getSize() + Discard.getSize() - 1;
  }

  @Override
  public ArrayList<ICard> drawCards(int cardsNumber) {
    ArrayList<ICard> Cards = new ArrayList<ICard>();
    if (cardsNumber > this.Deck.getSize()) {
      this.rebuildDeck();
    }
    if (cardsNumber > this.getDrawableCardsNumber()) {
      cardsNumber = this.getDrawableCardsNumber();
    }
    for (int i = 0; i < cardsNumber; i++) {
      Cards.add(drawCard());
    }
    return Cards;
  }

  @Override
  public ICard getCurrentPlayedCard() {
    return Discard.peekCard();
  }

  @Override
  public void discard(ICard newCard) {
    if (newCard.isDiscardable())
      Discard.pushCard(newCard);

  }

  @Override
  public ArrayList<ICard> addCardsToPlayer(IPlayer player, int number) {
    ArrayList<ICard> Cards = drawCards(number);
    player.addToHand(Cards);
    return Cards;
  }

  @Override
  public int sizeofDiscard() {
    return Discard.getSize();
  }

  @Override
  public HashMap<COLOR, Integer> getBannedColors() {
    return this.Banned;
  }

  @Override
  public void addBannedColor(COLOR color, int i) {
    this.getBannedColors().put(color,i);
    
  }

}
