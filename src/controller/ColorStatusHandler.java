package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import view.GUIView;

public class ColorStatusHandler implements EventHandler<Event>{
  GUIView view;
public ColorStatusHandler(GUIView view){
  this.view=view;
}
@Override
public void handle(Event event) {
   view.ColorStatus();
   event.consume();
   
 }
  
}
