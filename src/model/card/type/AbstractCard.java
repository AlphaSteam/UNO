package model.card.type;

/**
 * This class provides a skeletal implementation of the Card interface to minimize the effort
 * required to implement this interface.
 * 
 * @author Sebastian Alfaro
 * 
 *
 */
public abstract class AbstractCard implements ICard {
  protected Color color;
  protected Symbol symbol;
  protected boolean discardable;

  @Override
  public boolean isPlayableOver(ICard otherCard) {
    if ((otherCard.getColor() == this.color) || (otherCard.getSymbol() == this.symbol)
        || (this.color == Color.NONE)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isFirstPlayable() {
    if (this.color != Color.NONE) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Symbol getSymbol() {
    return symbol;
  }

  @Override
  public boolean isDiscardable() {
    return discardable;
  }

  @Override
  public String toString() {
    String result = symbol.getName() + " " + color.getName();
    return result;
  }

}
