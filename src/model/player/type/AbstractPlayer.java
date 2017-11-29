package model.player.type;

import java.util.ArrayList;
import java.util.HashMap;

import model.card.type.COLOR;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.card.type.Symbol;

/**
 * This class provides a skeletal implementation of the Player interface to minimize the effort
 * required to implement this interface.
 * 
 * @author Sebastian Alfaro
 * @version 1.4
 *
 */
public abstract class AbstractPlayer implements IPlayer {
  protected ArrayList<ICard> hand = new ArrayList<ICard>();
  protected boolean SaidUno = false;
  protected boolean human;

  @Override
  public void addToHand(ArrayList<ICard> hand) {
    for (ICard card : hand) {
      getHand().add(card);
    }

  }

  @Override
  public boolean hasWon() {
    if (getHandSize() == 0) {
      return true;
    } else
      return false;
  }

  @Override
  public int getHandSize() {
    return this.hand.size();
  }

  @Override
  public boolean hasOneCard() {
    if (this.getHandSize() == 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public ArrayList<ICard> getHand() {
    return this.hand;
  }

  @Override
  public void setSaidUNO(boolean status) {
    this.SaidUno = status;

  }

  @Override
  public boolean hasSaidUNO() {
    return this.SaidUno;
  }

  @Override
  public void removeCardFromHand(ICard card) {
    getHand().remove(card);

  }

  @Override
  public boolean needsToDrawCard(ICard currentCard, HashMap<COLOR, Integer> map) {
    for (int i = 0; i < this.getHandSize(); i++) {
      if (this.getCardFromHand(i).isPlayableOver(currentCard, map)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean HasCard(COLOR color, Symbol symbol) {
    for (int i = 0; i < this.getHandSize(); i++) {
      if (this.getCardFromHand(i).getSymbol() == symbol
          && this.getCardFromHand(i).getColor() == color) {
        return true;
      }
    }
    return false;

  }

  @Override
  public ICard getCardFromHand(int number) {
    if (number < this.hand.size()) {
      return getHand().get(number);
    } else {
      NullCard NC = new NullCard();
      return NC;
    }
  }

  @Override
  public boolean isHuman() {
    return this.human;
  }


}
