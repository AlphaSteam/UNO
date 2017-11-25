package model.player.type;

import controller.IController;
import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.DrawCard;
import model.card.type.ICard;

/**
 * Human player, this type of player is controlled by a human through the console.
 * 
 * @author Sebastian
 *
 */
public class HumanPlayer extends AbstractPlayer {
  protected int i;

  public HumanPlayer(int i) {
    this.i = i;
    this.human=true;
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    int num = ctrl.AskForCardFromHand(this);
    if (num < game.getCurrentPlayer().getHandSize()) {
      return this.getCardFromHand(num);
    } else {
      return new DrawCard();

    }

  }

  @Override
  public String toString() {
    String result = "Player " + this.i;
    return result;
  }

  @Override
  public COLOR selectColor(IGameLogic game, IController ctrl) {
    return ctrl.askForColor();
  }

}
