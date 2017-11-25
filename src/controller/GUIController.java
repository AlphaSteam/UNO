package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
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
//    List<String> choices = new ArrayList<>();
//    choices.add("Red");
//    choices.add("Blue");
//    choices.add("Green");
//    ChoiceDialog<String> dialog = new ChoiceDialog<>("Red", choices);
//    DialogPane dialogPane = dialog.getDialogPane();
//    dialogPane.getStylesheets().add(
//        getClass().getResource("myDialogs.css").toExternalForm());
//     dialogPane.getStyleClass().add("myDialog");
//    dialog.setTitle("Color Selection");
//    dialog.setHeaderText("Action Required");
//    dialog.setContentText("Choose new color:");
//    Optional<String> result = dialog.showAndWait();
//    
//    if (result.isPresent()){
//     return Color.valueOf(result.get().toUpperCase());
//  }
//    return null;
    Stage stage = new Stage();
    Group root=new Group();
    stage.setTitle("Color Selection");
    Scene scene=new Scene(root, 450, 450)
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0.7);
    scene.setFill(c);
    
    
    stage.show();
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
