package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import model.card.type.ICard;
import model.player.type.IPlayer;

public class DeckHandler implements EventHandler<Event>{
  ICard Card;
  IGameLogic Game;
  GUIController ctrl;
public DeckHandler(IGameLogic game,GUIController ctrl){
  this.Game=game;
  this.ctrl=ctrl;
}
@Override
public void handle(Event event) {
  IPlayer CurrentPlayer=Game.getCurrentPlayer();
   Game.drawOneCard(CurrentPlayer);
   event.consume();
   
 }
  
}
