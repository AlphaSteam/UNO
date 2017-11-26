package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import view.GUIView;

public class PlayerListHandler implements EventHandler<Event>{
  GUIView view;
public PlayerListHandler(GUIView view){
  this.view=view;
}
@Override
public void handle(Event event) {
  System.out.println("Player's List");
   view.PlayerList();
   event.consume();
   
 }
  
}
