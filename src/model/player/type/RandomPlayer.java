package model.player.type;

import java.util.ArrayList;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.DrawCard;
import model.card.type.ICard;

/**
 * Random player, this type of player chooses his cards through random number generation.
 * 
 * @author Sebastian
 *
 */
public class RandomPlayer extends AbstractPlayer {
  int i = 0;
  ArrayList<ICard> Playable = new ArrayList<ICard>();

  public RandomPlayer(int i) {
    this.i = i;
    this.human=false;
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    if (!this.needsToDrawCard(game.getCurrentPlayedCard())) {
      for (int j = 0; j < this.getHandSize(); j++) {
        if (this.getCardFromHand(j).isPlayableOver(game.getCurrentPlayedCard())) {
          Playable.add(getCardFromHand(j));
        }
      }
      return Playable.get((int) (Math.random() * Playable.size() - 2) + 1);
    } else {
      return new DrawCard();
    }

  }

  @Override
  public String toString() {
    String result = "Jugador " + this.i;
    return result;
  }

  @Override
  public Color selectColor(IGameLogic game, IController ctrl) {
    int C = (int) ((Math.random() * 4) + 1);
    if (C == 1) {
      return Color.BLUE;
    } else if (C == 2) {
      return Color.GREEN;
    } else if (C == 3) {
      return Color.RED;
    } else {
      return Color.YELLOW;
    }
  }

}
