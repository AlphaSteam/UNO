package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Skip card.
 * <p>
 * Effect:
 * <p>
 * Skips the turn of the next player.
 * 
 * @author Sebastian Alfaro
 *
 */
public class CardSaltar extends AbstractCard {
  public CardSaltar(COLOR color) {
    this.color = color;
    this.symbol = Symbol.SKIP;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.skipPlayer();
    game.autoShoutUNO(ctrl);


  }

}
