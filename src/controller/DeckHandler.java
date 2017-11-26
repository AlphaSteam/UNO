package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import model.card.type.ICard;
import model.player.type.IPlayer;
import view.GUIView;

public class DeckHandler implements EventHandler<Event>{
  ICard Card;
  IGameLogic Game;
  GUIController ctrl;
  GUIView view;
public DeckHandler(IGameLogic game,GUIController ctrl,GUIView view){
  this.Game=game;
  this.ctrl=ctrl;
  this.view=view;
}
@Override
public void handle(Event event) {
  IPlayer CurrentPlayer=Game.getCurrentPlayer();
  
  if(CurrentPlayer.needsToDrawCard(Game.getCurrentPlayedCard())){
    Game.drawOneCard(CurrentPlayer);
    view.updateCurrentStatus();
  }
   
   event.consume();
   
 }
  
}
