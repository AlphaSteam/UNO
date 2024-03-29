package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Reverse card.
 * <p>
 * Effect:
 * <p>
 * Changes the direction of the game (Instead of the player that would play after you, play the one
 * before you and so on, the direction doesn't change again until another reverse card is played).
 * 
 * @author Sebastian Alfaro
 *
 */
public class CardInvertir extends AbstractCard {
  public CardInvertir(COLOR color) {
    this.color = color;
    this.symbol = Symbol.INVERT;
    this.discardable = true;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    game.invertDirection();
 

  }

}
