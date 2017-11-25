package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Wild Color change +4 card.
 * <p>
 * Effect:
 * <p>
 * Changes color to the one the player chooses and makes the next player draw 4 cards.
 * 
 * @author Sebastian
 *
 */
public class CardComodin4 extends AbstractCard {
  public CardComodin4() {
    this.color = COLOR.NONE;
    this.symbol = Symbol.WILD_DRAW_FOUR;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    this.color = game.getCurrentPlayer().selectColor(game, ctrl);
    game.addToDrawWell(4);
    ctrl.showMessage("->Carta jugada: " + this.toString());
    ctrl.showMessage(
        "Efecto: Cambia el color a eleccion y hace que el siguiente jugador saque 4 cartas");

  }

}
