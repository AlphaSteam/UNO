package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import view.GUIView;

/**
 * Handles the color selection prompt when playing a WILD card with this property.
 * 
 * @author Sebastian Alfaro
 *
 */
public class ColorStatusHandler implements EventHandler<Event> {
  GUIView view;

  public ColorStatusHandler(GUIView view) {
    this.view = view;
  }

  @Override
  public void handle(Event event) {
    view.ColorStatus();
    event.consume();

  }

}
