package controller;

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
    updatePlayedCard();
    view.updateCurrentStatus();
    IPlayer CurrentPlayer = game.getCurrentPlayer();
    boolean Human = CurrentPlayer.isHuman();
    if (!Human) {
      boolean played = false;
      view.WaitAlert(1);
      while (played == false) {
        ICard card = game.getCurrentPlayer().getCardToPlay(game, this);
        played = game.playCard(card, this);
      }
      
    }

  }


  @Override
  public void updatePlayedCard() {
    view.updatePlayedCard();

  }
  @Override
  public void announceWinner() {
   view.WinnerAlert();
    
  }


}
