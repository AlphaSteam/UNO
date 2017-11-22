package controller;

import java.util.Scanner;

import model.IGameLogic;
import model.card.type.Color;
import model.player.type.IPlayer;
import view.GUIView;

public class GUIController implements IController{
  IGameLogic game;
  GUIView view;
  
  public GUIController(IGameLogic game,GUIView view){
    this.game=game;
    this.view=view;
  }
  @Override
  public Color askForColor() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int AskForCardFromHand(IPlayer player) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void playTurn() {
    this.game.startTurn(this);
    
  }

  @Override
  public void updatePlayedCard() {
    // TODO Auto-generated method stub
    
  }

}
