package controller;

import java.util.Observable;
import java.util.Scanner;

import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.type.IPlayer;
import view.ConsoleView;

/**
 * A controller which use standard input from console.
 * 
 * @author eriveros
 *
 */
public class ConsoleController extends Observable implements IController {

  IGameLogic game;
  ConsoleView view;
  Scanner in;
  protected boolean cardPlayed = false;

  /**
   * Controller constructor. Initializes model, view, and input method. Also, it plays the card in
   * discard pile.
   * 
   * @param game GameLogic of the game.
   * @param view ConsoleView of the game.
   */
  public ConsoleController(IGameLogic game, ConsoleView view) {
    this.view = view;
    this.game = game;
    this.in = new Scanner(System.in);
    showMessage("¡Bienvenido a JavaUNO!");
    game.getCurrentPlayedCard().executeAction(game, this);
  }

  @Override
  public void playTurn() {
    game.startTurn(this);
    updatePlayedCard();
    view.updateCurrentStatus();
    IPlayer currentPlayer = game.getCurrentPlayer();
    this.cardPlayed = false;
    while (!this.cardPlayed) {
      ICard card = currentPlayer.getCardToPlay(game, this,game.getBannedColors());
      cardPlayed = game.playCard(card, this);
    }
  }

  @Override
  public COLOR askForColor() {
    view.showMessage("Elige un color:");
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
  public int AskForCardFromHand(IPlayer player) {
    int num = -1;
    view.showPlayerHand(player);
    view.showMessage(
        "" + player.getHandSize() + ") Robar una carta del mazo e intentar jugarla si es posible.");
    while (num < 0 || num > player.getHandSize()) {
      view.showMessage(
          "Por favor, ingresar un número entre el 0 y el " + player.getHandSize() + ".");
      num = in.nextInt();
    }
    return num;
  }

  @Override
  public void showMessage(String message) {
    view.showMessage(message);
  }

  @Override
  public void updatePlayedCard() {
    view.updatePlayedCard();
  }

  @Override
  public void SayUNO() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void announceWinner() {
    // TODO Auto-generated method stub
    
  }



}
