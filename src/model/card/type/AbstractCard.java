package model.card.type;

import java.util.HashMap;

/**
 * This class provides a skeletal implementation of the Card interface to minimize the effort
 * required to implement this interface.
 * 
 * @author Sebastian Alfaro
 * 
 *
 */
public abstract class AbstractCard implements ICard {
  protected COLOR color;
  protected Symbol symbol;
  protected boolean discardable;
  @Override
  public boolean isPlayableOver(ICard otherCard, HashMap<COLOR,Integer> map) {
    if(map.containsKey(getColor())){
      return false;
    }
    if( otherCard.getSymbol()==Symbol.BAN ){
      
    }
    if ((otherCard.getColor() == this.color) || (otherCard.getSymbol() == this.symbol)
        || (this.color == COLOR.NONE)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isFirstPlayable() {
    if (this.color != COLOR.NONE) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public COLOR getColor() {
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
