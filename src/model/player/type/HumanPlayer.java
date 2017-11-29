package model.player.type;

import java.util.HashMap;

import controller.IController;
import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.card.type.NullCard;

/**
 * Human player, this type of player is controlled by a human through the console.
 * 
 * @author Sebastian Alfaro
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
  public ICard getCardToPlay(IGameLogic game, IController ctrl, HashMap<COLOR, Integer> map) {
    return new NullCard();

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
