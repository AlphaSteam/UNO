package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Wild Color change card.
 * <p>
 * Effect:
 * <p>
 * Changes the color to the one that the player chooses.
 * 
 * @author Sebastian Alfaro
 *
 */
public class CardComodinColor extends AbstractCard {
  public CardComodinColor() {
    this.color = COLOR.NONE;
    this.symbol = Symbol.WILD;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    this.color = game.getCurrentPlayer().selectColor(game, ctrl);
  }

}
