package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Draw card.
 * <p>
 * Effect:
 * <p>
 * This card can't be discarded and it's effect is that the player draws one card.
 * 
 * @author Sebastian
 *
 */
public class DrawCard extends AbstractCard {
  public DrawCard() {
    this.color = COLOR.NONE;
    this.symbol = Symbol.NONE;
    this.discardable = false;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.drawOneCard(game.getCurrentPlayer());
  }

}
