package model.card;

import java.util.Collections;
import java.util.Stack;

import model.card.type.ICard;

public class CardPile implements ICardPile {
  protected Stack<ICard> Pile = new Stack<ICard>();

  public CardPile() {

  }

  @Override
  public int getSize() {
    return Pile.size();
  }

  @Override
  public ICard pushCard(ICard newCard) {
    Pile.push(newCard);
    return newCard;
  }

  @Override
  public ICard popCard() {
    return Pile.pop();
  }

  @Override
  public ICard peekCard() {
    return Pile.peek();
  }

  @Override
  public void shuffle() {
    Collections.shuffle(Pile);

  }

  @Override
  public boolean isEmpty() {
    return Pile.isEmpty();
  }

  @Override
  public void pushCards(ICardPile otherPile) {
    while (!otherPile.isEmpty()) {
      Pile.push(otherPile.popCard());
    }

  }

}
