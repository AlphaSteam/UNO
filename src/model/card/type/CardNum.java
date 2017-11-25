package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Numeric card.
 * <p>
 * Effect:
 * <p>
 * Doesn't have any effect.
 * 
 * @author Sebastian
 *
 */
public class CardNum extends AbstractCard {
  public CardNum(COLOR color, Symbol symbol) {
    this.color = color;
    this.symbol = symbol;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    ctrl.showMessage("->Carta jugada: " + this.toString());
    ctrl.showMessage("Efecto : No tiene efecto");

  }

}
