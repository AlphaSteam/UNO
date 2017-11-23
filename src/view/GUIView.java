package view;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.GUIController;
import controller.CardHandler;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.GameLogic;
import model.IGameLogic;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.card.type.ICard;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class GUIView extends Application implements Observer {
  IPlayer CurrentPlayer;
  IPlayer LastPlayer;
  IPlayer NextPlayer;
  Text JugadorActualName = new Text(" ");
  Text JugadorAnteriorName = new Text(" ");
  Text JugadorSigName = new Text(" ");
  Image Human=new Image(GUIView.class.getResourceAsStream("/Images/HumanPortrait2.png"));
  Image Robot=new Image(GUIView.class.getResourceAsStream("/Images/RobotPortrait.png"));

  Image Discard = new Image(GUIView.class.getResourceAsStream("/Images/UNOCards/NULL.png"));
  ImageView P1V = new ImageView(Human);
  ImageView P2V = new ImageView(Human);
  ImageView P3V = new ImageView(Robot);
  ImageView DiscardView = new ImageView(Discard);
  IGameLogic game = MakeGame();
  HBox hhand = new HBox();
  int X = 1920;
  int Y = 1080;
  double scaleSec = 0.7;
  double scalePrim = 0.95;
  int offset = -25;
  int offsetY = 35;
  int offsetYImg = 15;
  double TurnScale = 1;
  double CardScale = 0.6;
  int HandOffsetY=-100;
  GUIController ctrl = new GUIController(game, this);
  @Override
  public void start(Stage primaryStage) {

    // Create root BorderPane
    BorderPane root = new BorderPane();
    // Creacion de Textos
    double StrokeJugadores = 1.5;
    // Fonts
    Font Josefin = Font
        .loadFont(GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 40);
    // NombreJugadores
    // Text JugadorActualName = new Text(CurrentPlayer.toString());
    JugadorActualName.setFont(Josefin);
    JugadorActualName.setScaleX(0.6 * TurnScale);
    JugadorActualName.setScaleY(0.6 * TurnScale);
    // Text JugadorAnteriorName = new Text(LastPlayer.toString());
    JugadorAnteriorName.setFont(Josefin);
    JugadorAnteriorName.setScaleX(0.5 * TurnScale);
    JugadorAnteriorName.setScaleY(0.5 * TurnScale);
    JugadorAnteriorName.setTranslateX(offset * -1 * (1 / TurnScale));
    // Text JugadorSigName = new Text(NextPlayer.toString());
    JugadorSigName.setFont(Josefin);
    JugadorSigName.setScaleX(0.5 * TurnScale);
    JugadorSigName.setScaleY(0.5 * TurnScale);
    JugadorSigName.setTranslateX(offset * (1 / TurnScale));
    // Jugador anterior
    Text JugadorAnteriorTxt = new Text("Last\nPlayer");
    JugadorAnteriorTxt.setFont(Josefin);
    JugadorAnteriorTxt.setX(100 * TurnScale);
    JugadorAnteriorTxt.setY(40 * TurnScale);
    JugadorAnteriorTxt.setFill(Color.WHITE);
    JugadorAnteriorTxt.setStrokeWidth(StrokeJugadores);
    JugadorAnteriorTxt.setStroke(Color.BLACK);
    JugadorAnteriorTxt.setTextAlignment(TextAlignment.CENTER);
    JugadorAnteriorTxt.setScaleX(scaleSec * TurnScale);
    JugadorAnteriorTxt.setScaleY(scaleSec * TurnScale);
    JugadorAnteriorTxt.setTranslateY(offsetY * (1 / TurnScale));
    JugadorAnteriorTxt.setTranslateX(offset * -1 * (1 / TurnScale));
    // Jugador actual
    Text JugadorActualTxt = new Text("Current\nPlayer");
    JugadorActualTxt.setFont(Josefin);
    JugadorActualTxt.setFill(Color.WHITE);
    JugadorActualTxt.setStrokeWidth(StrokeJugadores);
    JugadorActualTxt.setStroke(Color.BLACK);
    JugadorActualTxt.setTextAlignment(TextAlignment.CENTER);
    JugadorActualTxt.setScaleX(scalePrim * TurnScale);
    JugadorActualTxt.setScaleY(scalePrim * TurnScale);
    // Jugador siguiente
    Text JugadorSigTxt = new Text("Next\nPlayer");
    JugadorSigTxt.setFont(Josefin);
    JugadorSigTxt.setX(600);
    JugadorSigTxt.setY(40);
    JugadorSigTxt.setFill(Color.WHITE);
    JugadorSigTxt.setStrokeWidth(StrokeJugadores);
    JugadorSigTxt.setStroke(Color.BLACK);
    JugadorSigTxt.setTextAlignment(TextAlignment.CENTER);
    JugadorSigTxt.setScaleX(scaleSec * TurnScale);
    JugadorSigTxt.setScaleY(scaleSec * TurnScale);
    JugadorSigTxt.setTranslateY(offsetY + 2 * (1 / TurnScale));
    JugadorSigTxt.setTranslateX((offset - 2) * (1 / TurnScale));
    // DiscardCards Text
    Text DiscardText = new Text(game.getCardManager().sizeofDiscard() + "Cards");
    DiscardText.setFont(Font.loadFont(
        GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 30));


    // Create Images
    P1V.setScaleX(scaleSec * TurnScale);
    P1V.setScaleY(scaleSec * TurnScale);
    P1V.setTranslateY(offsetYImg * (1 / TurnScale));
    P1V.setTranslateX(offset * -1 * (1 / TurnScale));
    // P2(Current)
    P2V.setScaleX(scalePrim * TurnScale);
    P2V.setScaleY(scalePrim * TurnScale);
    // P3(Next)
    P3V.setScaleX(scaleSec * TurnScale);
    P3V.setScaleY(scaleSec * TurnScale);
    P3V.setTranslateY(offsetYImg * (1 / TurnScale));
    P3V.setTranslateX(offset * (1 / TurnScale));
    // DiscardImage
    DiscardView.setScaleX(CardScale);
    DiscardView.setScaleY(CardScale);
    // DeckImage
    Image Deck = new Image(GUIView.class.getResourceAsStream("/Images/UNOCards/Back.jpg"));
    ImageView DeckView = new ImageView(Deck);
    DeckView.setScaleX(CardScale);
    DeckView.setScaleY(CardScale);
    // Hbox for cards in hand
    
    hhand.setPadding(new Insets(15, 12, 15, 12));
    hhand.setAlignment(Pos.CENTER);
    root.setBottom(hhand);
    // Create GridPane
    GridPane gp = new GridPane();
    gp.setPadding(new Insets(10, 0, 10, 0));
    gp.setHgap(20);
    // Anterior
    gp.add(JugadorAnteriorTxt, 0, 0);
    gp.add(P1V, 0, 1);
    gp.add(JugadorAnteriorName, 0, 2);
    GridPane.setHalignment(JugadorAnteriorTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorAnteriorName, HPos.CENTER);
    GridPane.setHalignment(P1V, HPos.CENTER);
    // Actual
    gp.add(JugadorActualTxt, 1, 0);
    gp.add(P2V, 1, 1);
    gp.add(JugadorActualName, 1, 2);
    GridPane.setHalignment(JugadorActualTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorActualName, HPos.CENTER);
    GridPane.setHalignment(P2V, HPos.CENTER);
    // Siguiente
    gp.add(JugadorSigTxt, 2, 0);
    gp.add(P3V, 2, 1);
    gp.add(JugadorSigName, 2, 2);
    GridPane.setHalignment(JugadorSigTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorSigName, HPos.CENTER);
    GridPane.setHalignment(P3V, HPos.CENTER);
    gp.setAlignment(Pos.CENTER);
    root.setTop(gp);

    // Create VBox
    VBox vbox = new VBox();

    // Create GridPane Two
    GridPane gp2 = new GridPane();
    gp2.setPadding(new Insets(10, 0, 10, 0));
    gp2.add(DiscardView, 0, 0);
    gp2.add(DeckView, 1, 0);
    gp2.add(DiscardText, 0, 1);
    GridPane.setHalignment(DiscardText, HPos.CENTER);
    gp2.setAlignment(Pos.TOP_CENTER);
    RowConstraints cc = new RowConstraints();
    cc.setMaxHeight(300);
    RowConstraints cc2 = new RowConstraints();
    cc2.setMaxHeight(DiscardView.getScaleX());
    gp2.getRowConstraints().addAll(cc, cc2);
    root.setCenter(gp2);
    gp2.setGridLinesVisible(true);
    gp.setGridLinesVisible(true);

    // Create Scene
    Scene scene = new Scene(root, X, Y);
    scene.setFill(Color.DARKCYAN);
    primaryStage.setMinWidth(X);
    primaryStage.setMinHeight(Y);
    primaryStage.setMaximized(true);
    primaryStage.setTitle("JavaUNO");
    primaryStage.setScene(scene);
    
    
    ctrl.playTurn();
    primaryStage.show();
    
  }

  public static void main(String args[]) {

    launch(args);
    // ConsoleView view = new ConsoleView(game);
    // ConsoleController ctrl = new ConsoleController(game, view);

  }

  public static IGameLogic MakeGame() {
    DeckBuilder DB = new DeckBuilder();
    ICardPile Deck = DB.createDeck();
    IPlayerListBuilder playerBuilder = new PlayerListBuilder();
    IPlayer Player1 = new HumanPlayer(1);
    IPlayer PlayerR1 = new RandomPlayer(2);
    IPlayer PlayerR2 = new RandomPlayer(3);
    IPlayer PlayerR3 = new RandomPlayer(4);
    playerBuilder.addPlayer(Player1);
    playerBuilder.addPlayer(PlayerR1);
    playerBuilder.addPlayer(PlayerR2);
    playerBuilder.addPlayer(PlayerR3);
    ArrayList<IPlayer> AL = playerBuilder.buildPlayerList();
    IGameLogic game = new GameLogic(AL, Deck);
    return game;
  }

  public void updatePlayedCard() {
    DiscardView.setImage(new Image(GUIView.class
        .getResourceAsStream("/Images/UNOCards/" + game.getCurrentPlayedCard().getColor() + "/"
            + game.getCurrentPlayedCard().getSymbol() + ".png")));
  }

  public void updateCurrentStatus() {
    LastPlayer = game.getLastPlayer();
    CurrentPlayer = game.getCurrentPlayer();
    NextPlayer = game.getNextPlayer();
    // P1(Last)
    if (LastPlayer.isHuman()) {
      P1V.setImage(Human);
    } else {
      P1V.setImage(Robot);
    }
    // P2(Current)
    if (CurrentPlayer.isHuman()) {
      P2V.setImage(Human);
    } else {
      P2V.setImage(Robot);
    }
    // P3(Next)
    if (NextPlayer.isHuman()) {
      P3V.setImage(Human);
    } else {
      P3V.setImage(Robot);
    }

    JugadorActualName.setText(CurrentPlayer.toString());
    JugadorSigName.setText(NextPlayer.toString());
    JugadorAnteriorName.setText(LastPlayer.toString());
      hhand.getChildren().clear();
   // Cards in hand
       if (CurrentPlayer.isHuman()) {
       for (int i = 0; i < game.getCurrentPlayer().getHandSize(); i++) {
         ICard Card=game.getCurrentPlayer().getCardFromHand(i);
       Image CardImage = new Image(GUIView.class.getResourceAsStream(
       "/Images/UNOCards/" + Card.getColor() + "/"
       + Card.getSymbol() + ".png"));
       ImageView CardView = new ImageView(CardImage);
       CardView.setScaleX(CardScale);
       CardView.setScaleY(CardScale);
       CardView.setTranslateY(HandOffsetY);
       CardView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,new CardHandler(Card,game,ctrl) );
   
       hhand.getChildren().add(CardView);
       }
      
       }
       else{
         for (int i = 0; i < game.getCurrentPlayer().getHandSize(); i++) {
           Image Card = new Image(GUIView.class.getResourceAsStream(
           "/Images/UNOCards/NULL.png"));
           ImageView CardView = new ImageView(Card);
           CardView.setScaleX(CardScale);
           CardView.setScaleY(CardScale);
           CardView.setTranslateY(HandOffsetY);
           hhand.getChildren().add(CardView);
           }
        
       }
      
    }

  
  

  @Override
  public void update(Observable o, Object arg) {
    GUIController ctrl = (GUIController) arg;
    ctrl.playTurn();

  }

}
