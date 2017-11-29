package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * BAN card.
 * <p>
 * Effect:
 * <p>
 * Bans the color for three turns(That color cannot be played, or chose in a color prompt).
 * 
 * @author Sebastian Alfaro
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
