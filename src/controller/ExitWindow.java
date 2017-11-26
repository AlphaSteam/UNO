package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ExitWindow implements EventHandler<ActionEvent>{
 Stage stage;
public ExitWindow(Stage stage){
 this.stage=stage;
}

@Override
public void handle(ActionEvent event) {
  stage.close();
  event.consume();
  
}
  
}