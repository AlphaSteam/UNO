package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import view.GUIView;

public class RightButtonHandler implements EventHandler<Event> {
  GUIView view;
  IGameLogic game;
  public RightButtonHandler(GUIView view, IGameLogic game){
    this.view=view;
    this.game=game;
  }
  @Override
  public void handle(Event event) {
    if(view.GetFirstCardIndex()<game.getCurrentPlayer().getHand().size()-7){
    view.IncrementCardIndex(1);
    view.updateCurrentStatus();
    }
    event.consume();
    
  }

}
