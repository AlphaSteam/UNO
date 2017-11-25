package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * +2 Card.
 * <p>
 * Effect:
 * <p>
 * Makes the next player draw 2 cards.
 * 
 * @author Sebastian
 *
 */
public class CardRobar2 extends AbstractCard {
  public CardRobar2(COLOR color) {
    this.color = color;
    this.symbol = Symbol.DRAW_TWO;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.addToDrawWell(2);
    ctrl.showMessage("->Carta jugada: " + this.toString());
    ctrl.showMessage("Efecto : Hace que el siguiente jugador saque 2 cartas");
  }

}
