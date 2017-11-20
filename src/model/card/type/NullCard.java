package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Null Card
 * <p>
 * Effect:
 * <p>
 * This card can't be discarded and it's used for the Null Pattern in testing.
 * 
 * @author Sebastian
 *
 */
public class NullCard extends AbstractCard {
  public NullCard() {
    this.color = Color.NONE;
    this.symbol = Symbol.NONE;
    this.discardable = false;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {

  }

}
