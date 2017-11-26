import view.GUIView;

/**
 * Main class of UNO Game
 * 
 * It instantiates model, view and controller and makes the turn loop while the game hasn't ended.
 * 
 * @author eriveros
 *
 */
public class Main {

  public static void main(String[] args) {
    GUIView.launch(GUIView.class, args);
    }
    
}
