package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import view.GUIView;

public class PlayerListHandler implements EventHandler<Event>{
  GUIView view;
public PlayerListHandler(GUIView view){
  this.view=view;
}
@Override
public void handle(Event event) {
   view.PlayerList();
   event.consume();
   
 }
  
}
