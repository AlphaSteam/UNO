package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * ZA WARUDO card
 * <p>
 * Effect:
 * <p>
 * Stops time, so the player can use another card.
 * @author Sebastian
 *
 */
public class CardZAWARUDO extends AbstractCard {
  public CardZAWARUDO() {
    this.color = COLOR.NONE;
    this.symbol = Symbol.ZAWARUDO;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.StopTime();
    

  }

}
