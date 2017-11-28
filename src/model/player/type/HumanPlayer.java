package model.player.type;

import java.util.HashMap;

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
  protected String s;
  protected boolean Numbered;

  public HumanPlayer(int i) {
    this.i = i;
    this.human = true;
    this.Numbered = true;
  }

  public HumanPlayer(String s) {
    if (s.length() > 8) {
      s = s.substring(0, 8);
    }
    this.human = true;
    this.s = s;
    this.Numbered = false;
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl,HashMap<COLOR,Integer> map) {
    int num = ctrl.AskForCardFromHand(this);
    if (num < game.getCurrentPlayer().getHandSize()) {
      return this.getCardFromHand(num);
    } else {
      return new DrawCard();

    }

  }

  @Override
  public String toString() {
    String result;
    if (this.Numbered) {
      result = "Player " + this.i;
    } else {
      result = this.s;
    }
    return result;
  }

  @Override
  public COLOR selectColor(IGameLogic game, IController ctrl) {
    return ctrl.askForColor();
  }

}
