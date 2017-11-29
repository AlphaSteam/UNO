package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IGameLogic;
import view.GUIView;

/**
 * Handles moving to the left in the player's hand by clicking the left button.
 * 
 * @author Sebastian Alfaro
 *
 */
public class LeftButtonHandler implements EventHandler<Event> {
  GUIView view;
  IGameLogic game;

  public LeftButtonHandler(GUIView view, IGameLogic game) {
    this.view = view;
    this.game = game;
  }

  @Override
  public void handle(Event event) {
    if (view.GetFirstCardIndex() > 0) {
      view.DecrementCardIndex(1);
      view.updateCurrentStatus();

    }
    event.consume();
  }

}
