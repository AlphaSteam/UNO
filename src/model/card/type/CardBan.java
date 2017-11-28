package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Skip card.
 * <p>
 * Effect:
 * <p>
 * Skips the turn of the next player.
 * @author Sebastian
 *
 */
public class CardBan extends AbstractCard {
  public CardBan(COLOR color) {
    this.color = color;
    this.symbol = Symbol.BAN;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.BanColor(color.toString());
    game.autoShoutUNO(ctrl);

  }

}
