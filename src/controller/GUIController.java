package controller;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.type.IPlayer;
import view.GUIView;

public class GUIController implements IController {
  IGameLogic game;
  GUIView view;

  public GUIController(IGameLogic game, GUIView view) {
    this.game = game;
    this.view = view;
    game.getCurrentPlayedCard().executeAction(game, this);
  }
  public void SayUNO(){
    view.UNOAlert();
  }

  @Override
  public COLOR askForColor() {
    return view.ChooseColorAlert();
  }

  @Override
  public int AskForCardFromHand(IPlayer player) {
    return 0;

  }

  
  @Override
  public void showMessage(String message) {
    return;

  }

  @Override
  public void playTurn() {
    this.game.startTurn(this);
    System.out.println(game.getCurrentPlayer());
    System.out.println(game.getCurrentPlayedCard());
    updatePlayedCard();
    view.updateCurrentStatus();
    PauseTransition pause = new PauseTransition(Duration.seconds(1));
    pause.setOnFinished(event -> {
      updatePlayedCard();
      view.updateCurrentStatus();
    });
    pause.play();

    IPlayer CurrentPlayer = game.getCurrentPlayer();
    boolean Human = CurrentPlayer.isHuman();
    if (!Human) {

      System.out.println("BEEP BOP");
      // System.out.println("Empezo a esperar");
      // System.out.println("Termino");
      boolean played = false;
      while (played == false) {
        // GUIController.Wait();
        ICard card = game.getCurrentPlayer().getCardToPlay(game, this);
        played = game.playCard(card, this);
      }
    }

  }


  @Override
  public void updatePlayedCard() {
    view.updatePlayedCard();

  }


}
