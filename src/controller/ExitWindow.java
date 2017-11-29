package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Handles exiting the window after clicking the button this handle is attached to.
 * 
 * @author Sebastian Alfaro
 *
 */
public class ExitWindow implements EventHandler<ActionEvent> {
  Stage stage;

  public ExitWindow(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void handle(ActionEvent event) {
    stage.close();
    event.consume();

  }

}
