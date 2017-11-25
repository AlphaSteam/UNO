package controller;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.IGameLogic;
import model.card.type.Color;
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

  @Override
  public Color askForColor() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int AskForCardFromHand(IPlayer player) {
    return 0;

  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void playTurn() {
    this.game.startTurn(this);
    System.out.println(game.getCurrentPlayer());
    System.out.println(game.getCurrentPlayedCard());
    updatePlayedCard();
    view.updateCurrentStatus();
    PauseTransition pause = new PauseTransition(
        Duration.seconds(1)
    );
    pause.setOnFinished(event -> {
      updatePlayedCard();
      view.updateCurrentStatus();
    });
    pause.play();
    
    IPlayer CurrentPlayer = game.getCurrentPlayer();
    boolean Human=CurrentPlayer.isHuman();
    if (!Human) {
          
      System.out.println("BEEP BOP");
      // System.out.println("Empezo a esperar");
      // System.out.println("Termino");
       boolean played=false;
       while (played==false) {
       //GUIController.Wait();
      ICard card = game.getCurrentPlayer().getCardToPlay(game, this);
       played= game.playCard(card, this);
       }
    }

  }

  public static void Wait() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void updatePlayedCard() {
    view.updatePlayedCard();

  }


}
