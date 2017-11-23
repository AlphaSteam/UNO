package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import model.card.type.ICard;

public class CardHandler implements EventHandler<Event>{
  ICard Card;
  IGameLogic Game;
  GUIController ctrl;
public CardHandler(ICard card,IGameLogic game,GUIController ctrl){
  this.Card=card;
  this.Game=game;
  this.ctrl=ctrl;
}
@Override
public void handle(Event event) {
   System.out.println("Jugando");
   
   if(Game.playCard(Card,ctrl)){
   ctrl.playTurn();
   }
 }
  
}
