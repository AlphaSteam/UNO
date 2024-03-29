package controller;

import java.util.Scanner;

import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.type.IPlayer;

/**
 * A controller that doesn't print anything on screen.
 * 
 * @author Sebastian Alfaro
 *
 */
public class NullController implements IController {

  IGameLogic game;
  Scanner in;
  protected boolean cardPlayed = false;

  /**
   * Controller constructor. Initializes model, and input method. Also, it plays the card in discard
   * pile.
   * 
   * @param game GameLogic of the game.
   * 
   */
  public NullController(IGameLogic game) {
    this.game = game;
  }

  @Override
  public void playTurn() {
    game.startTurn(this);
    IPlayer currentPlayer = game.getCurrentPlayer();
    this.cardPlayed = false;
    while (!this.cardPlayed) {
      ICard card = currentPlayer.getCardToPlay(game, this, game.getBannedColors());
      cardPlayed = game.playCard(card, this);
    }
  }

  @Override
  public COLOR askForColor() {
    int i = 0;
    for (COLOR color : COLOR.getColors()) {
      System.out.println("" + i + ") " + color.getName());
      i++;
    }
    int num = -1;
    while (num < 0 || num >= 4) {
      System.out.println("Por favor, ingresar un número entre el 0 y el 3.");
      num = in.nextInt();
    }
    return COLOR.getColors()[num];
  }


  @Override
  public void updatePlayedCard() {
    return;
  }

  @Override
  public void SayUNO(IPlayer LastPlayer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void announceWinner(IPlayer player) {
    // TODO Auto-generated method stub

  }



}
