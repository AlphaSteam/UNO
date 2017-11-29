package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import controller.GUIController;
import controller.LeftButtonHandler;
import controller.PlayerListHandler;
import controller.RightButtonHandler;
import controller.CardHandler;
import controller.ColorStatusHandler;
import controller.DeckHandler;
import controller.ExitWindow;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.GameLogic;
import model.IGameLogic;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

/**
 * Displays all the elements of GUI of the game.
 * 
 * @author Sebastian Alfaro
 *
 */
public class GUIView extends Application implements Observer {
  // Players
  private IPlayer CurrentPlayer;
  private IPlayer LastPlayer;
  private IPlayer NextPlayer;
  // Players in time
  private Text JugadorActualName = new Text(" ");
  private Text JugadorAnteriorName = new Text(" ");
  private Text JugadorSigName = new Text(" ");
  // Images
  private Image Human = new Image(GUIView.class.getResourceAsStream("/Images/HumanPortrait2.png"));
  private Image Robot = new Image(GUIView.class.getResourceAsStream("/Images/RobotPortrait.png"));
  private Image Discard = new Image(GUIView.class.getResourceAsStream("/Images/UNOCards/NULL.png"));
  private ImageView P1V = new ImageView(Human);
  private ImageView P2V = new ImageView(Human);
  private ImageView P3V = new ImageView(Robot);
  private ImageView DiscardView = new ImageView(Discard);
  // GameLogic
  private GameLogic game = (GameLogic) MakeGame();
  // Controller
  private GUIController ctrl = new GUIController(game, this);
  // Hand(HBox)
  private HBox hhand = new HBox();
  // Values
  private int X = 1920;
  private int Y = 1080;
  private double scaleSec = 0.7;
  private double scalePrim = 0.95;
  private int offset = -25;
  private int offsetY = 35;
  private int offsetYImg = 15;
  private double TurnScale = 1;
  private double CardScale = 0.6;
  private int HandOffsetY = -100;
  private double ButtonsScaleY = 1.2;
  private int FirstCardIndex = 0;
  // LastPlayerCards
  Text LastCards = new Text("x Cards");
  // CurrentPlayerCards
  Text CurrentCards = new Text("x Cards");
  // NextPlayerCards
  Text NextCards = new Text("x Cards");
  // DiscardCards Text
  Text DiscardText = new Text(game.getCardManager().sizeofDiscard() + "Cards");
  // DeckCards Text
  Text DeckText = new Text(game.getCardManager().getDrawableCardsNumber()
      - game.getCardManager().sizeofDiscard() + "Cards");



  @Override
  public void start(Stage primaryStage) {
    game.addObserver(this);

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
    // Text Jugador Actual Cards
    CurrentCards.setFont(Josefin);
    CurrentCards.setScaleX(0.5);
    CurrentCards.setScaleY(0.5);
    CurrentCards.setTextAlignment(TextAlignment.CENTER);
    CurrentCards.setTranslateY(-20);
    // Text JugadorAnteriorName = new Text(LastPlayer.toString());
    JugadorAnteriorName.setFont(Josefin);
    JugadorAnteriorName.setScaleX(0.5 * TurnScale);
    JugadorAnteriorName.setScaleY(0.5 * TurnScale);
    JugadorAnteriorName.setTranslateX(offset * -1 * (1 / TurnScale));
    // Text Jugador Anterior Cards
    LastCards.setFont(Josefin);
    LastCards.setScaleX(0.4);
    LastCards.setScaleY(0.4);
    LastCards.setTextAlignment(TextAlignment.CENTER);
    LastCards.setTranslateX(offset * -1);
    LastCards.setTranslateY(-20);
    // Text JugadorSigName = new Text(NextPlayer.toString());
    JugadorSigName.setFont(Josefin);
    JugadorSigName.setScaleX(0.5 * TurnScale);
    JugadorSigName.setScaleY(0.5 * TurnScale);
    JugadorSigName.setTranslateX(offset * (1 / TurnScale));
    // Text Jugador Siguiente Cards
    NextCards.setFont(Josefin);
    NextCards.setScaleX(0.4);
    NextCards.setScaleY(0.4);
    NextCards.setTextAlignment(TextAlignment.CENTER);
    NextCards.setTranslateX(offset * 1);
    NextCards.setTranslateY(-20);
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

    // DiscardText
    DiscardText.setFont(Font.loadFont(
        GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 30));
    // DeckText
    DeckText.setFont(Font.loadFont(
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
    Image Deck = new Image(GUIView.class.getResourceAsStream("/Images/UNOCards/Back.png"));
    ImageView DeckView = new ImageView(Deck);
    DeckView.setScaleX(CardScale);
    DeckView.setScaleY(CardScale);
    DeckView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
        new DeckHandler(game, ctrl, this));
    // LeftButton
    Image LeftButton = new Image(GUIView.class.getResourceAsStream("/Images/Buttons/Left.png"));
    ImageView LeftView = new ImageView(LeftButton);
    LeftView.setTranslateY(HandOffsetY);
    LeftView.setScaleY(ButtonsScaleY);
    LeftView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
        new LeftButtonHandler(this, game));
    // RightButton
    Image RightButton = new Image(GUIView.class.getResourceAsStream("/Images/Buttons/Right.png"));
    ImageView RightView = new ImageView(RightButton);
    RightView.setTranslateY(HandOffsetY);
    RightView.setScaleY(ButtonsScaleY);
    RightView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
        new RightButtonHandler(this, game));



    // Hbox for cards in hand
    hhand.setPadding(new Insets(15, 12, 15, 12));

    // Create GridPane(TOP)
    GridPane gp = new GridPane();
    gp.setPadding(new Insets(10, 0, 10, 0));
    gp.setHgap(20);
    // Anterior
    gp.add(JugadorAnteriorTxt, 0, 0);
    gp.add(P1V, 0, 1);
    gp.add(JugadorAnteriorName, 0, 2);
    gp.add(LastCards, 0, 3);
    GridPane.setHalignment(JugadorAnteriorTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorAnteriorName, HPos.CENTER);
    GridPane.setHalignment(P1V, HPos.CENTER);
    GridPane.setHalignment(LastCards, HPos.CENTER);
    // Actual
    gp.add(JugadorActualTxt, 1, 0);
    gp.add(P2V, 1, 1);
    gp.add(JugadorActualName, 1, 2);
    gp.add(CurrentCards, 1, 3);
    GridPane.setHalignment(JugadorActualTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorActualName, HPos.CENTER);
    GridPane.setHalignment(P2V, HPos.CENTER);
    GridPane.setHalignment(CurrentCards, HPos.CENTER);
    // Siguiente
    gp.add(JugadorSigTxt, 2, 0);
    gp.add(P3V, 2, 1);
    gp.add(JugadorSigName, 2, 2);
    gp.add(NextCards, 2, 3);
    GridPane.setHalignment(JugadorSigTxt, HPos.CENTER);
    GridPane.setHalignment(JugadorSigName, HPos.CENTER);
    GridPane.setHalignment(P3V, HPos.CENTER);
    GridPane.setHalignment(NextCards, HPos.CENTER);
    gp.setAlignment(Pos.CENTER);
    // gp.setGridLinesVisible(true);
    root.setTop(gp);


    // Create GridPane Two(Center)
    GridPane gp2 = new GridPane();
    gp2.setPadding(new Insets(10, 0, 10, 0));
    // gp2.add(playersView, 0,0);
    gp2.add(DiscardView, 0, 0);
    // gp2.addColumn(10, DeckView);
    gp2.add(DeckView, 1, 0);
    gp2.add(DiscardText, 0, 1);
    gp2.add(DeckText, 1, 1);
    GridPane.setHalignment(DiscardText, HPos.CENTER);
    GridPane.setHalignment(DeckText, HPos.CENTER);
    gp2.setAlignment(Pos.TOP_CENTER);
    RowConstraints cc = new RowConstraints();
    cc.setMaxHeight(300);
    RowConstraints cc2 = new RowConstraints();
    cc2.setMaxHeight(DiscardView.getScaleX());
    gp2.getRowConstraints().addAll(cc, cc2);
    // gp2.setGridLinesVisible(true);
    gp2.setTranslateX(230);

    // Create AnchorPane
    AnchorPane center = new AnchorPane(gp2);
    AnchorPane.setRightAnchor(gp2, (double) 730);
    root.setCenter(center);
    center.mouseTransparentProperty().set(false);



    // Create Grid 3
    GridPane gp3 = new GridPane();
    gp3.add(LeftView, 0, 0);
    gp3.add(hhand, 1, 0);
    gp3.add(RightView, 2, 0);
    // gp3.setGridLinesVisible(true);
    gp3.setAlignment(Pos.CENTER);
    root.setBottom(gp3);

    // PlayerListIcon
    Image players = new Image(GUIView.class.getResourceAsStream("/Images/Players.png"));
    ImageView playersView = new ImageView(players);
    playersView.setTranslateX(230);
    playersView.setTranslateY(60);
    // playersView.setScaleX(1.2);
    // playersView.setScaleY(1.2);
    playersView.pickOnBoundsProperty().set(true);
    playersView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
        new PlayerListHandler(this));
    root.setLeft(playersView);

    // BanColors Icon
    Image bans = new Image(GUIView.class.getResourceAsStream("/Images/Bcolors.png"));
    ImageView bansView = new ImageView(bans);
    bansView.setTranslateX(-270);
    bansView.setTranslateY(10);
    bansView.pickOnBoundsProperty().set(true);
    bansView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
        new ColorStatusHandler(this));
    root.setRight(bansView);

    // Create Scene
    Scene scene = new Scene(root, X, Y);
    Color c = Color.web("#2B2B2B", 0.7);
    scene.setFill(c);
    Image bg = new Image(GUIView.class.getResourceAsStream("/Images/Background/bg.png"));
    BackgroundImage bgImg = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
    Background bgf = new Background(bgImg);
    root.setBackground(bgf);
    primaryStage.setMinWidth(X);
    primaryStage.setMinHeight(Y);
    // primaryStage.setResizable(false);
    primaryStage.setMaximized(true);
    // primaryStage.setFullScreen(true);
    primaryStage.setTitle("JavaUNO GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    ctrl.playTurn();


  }

  public static void main(String args[]) {
    launch(args);
  }

  public int GetFirstCardIndex() {
    return this.FirstCardIndex;
  }

  public void SetFirstCardIndex(int x) {
    this.FirstCardIndex = x;
  }

  public void IncrementCardIndex(int x) {
    this.FirstCardIndex += x;
  }

  public void DecrementCardIndex(int x) {
    this.FirstCardIndex -= x;
  }

  public void PlayError() {
    // root(Group)
    Group root = new Group();
    // Scene
    Scene scene = new Scene(root, 460, 200);
    // Creating line object
    Line line = new Line();
    line.setStartX(0);
    line.setStartY(80);
    line.setEndX(700);
    line.setEndY(80);

    // Image (i)
    Image x = new Image(GUIView.class.getResourceAsStream("/Images/InfoIcons/x.png"));
    ImageView xView = new ImageView(x);
    xView.setLayoutX(360);
    xView.setLayoutY(0);
    xView.setScaleX(0.6);
    xView.setScaleY(0.6);
    // Creating Text
    Text action = new Text("Error");
    action.setY(50);
    action.setX(30);
    action.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 22));
    // Choose text
    Text choose = new Text("You can't play that card right now.");
    choose.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 17));
    choose.setY(141);
    choose.setX(30);

    // Select Button
    Button ok = new Button("OK");
    ok.setLayoutX(380);
    ok.setLayoutY(170);
    ok.setStyle("-fx-padding: 5 12 5 12;");



    // Stage
    root.getChildren().addAll(line, action, choose, ok, xView);
    Stage stage = new Stage();
    // Close Stage with select button
    ok.setOnAction(new ExitWindow(stage));
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();

  }

  public void PlayerList() {
    // root (ScrollPane)
    ScrollPane root = new ScrollPane();
    root.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    root.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    // Font
    Font Josefin = Font
        .loadFont(GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 30);
    // Group
    Group group = new Group();
    ArrayList<IPlayer> players = game.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      // Hbox that contains each player
      HBox hbox = new HBox();
      // Player
      IPlayer player = players.get(i);
      // Player Portrait
      Image playerImage = Human;
      ImageView playerImgView = new ImageView(playerImage);
      if (player.isHuman()) {
        playerImgView.setImage(Human);
      } else {
        playerImgView.setImage(Robot);
      }
      playerImgView.setScaleX(0.4);
      playerImgView.setScaleY(0.4);
      // playerImgView.setY(i*75);
      // Player Name
      Text playerTxt = new Text(player.toString());
      playerTxt.setFont(Josefin);

      hbox.getChildren().addAll(playerImgView, playerTxt);
      hbox.setTranslateY(100 * i);
      hbox.setAlignment(Pos.CENTER);
      // Player Number of cards
      Text nCards = new Text(" " + player.getHandSize() + " cards");
      nCards.setFont(Josefin);
      nCards.setY(hbox.getTranslateY() + 86);
      nCards.setX(340);
      // nCards.setX(140);

      group.getChildren().addAll(hbox, nCards);

    }
    group.setTranslateY(-10);
    group.setTranslateX(-30);
    // Scene
    root.setContent(group);
    Scene scene = new Scene(root, 500, 700);
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();
  }

  public void ColorStatus() {
    // root (ScrollPane)
    ScrollPane root = new ScrollPane();
    root.setHbarPolicy(ScrollBarPolicy.NEVER);
    root.setVbarPolicy(ScrollBarPolicy.NEVER);
    // Font
    Font Josefin = Font
        .loadFont(GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 20);
    // Group
    Group group = new Group();
    COLOR[] colors = new COLOR[4];
    colors[0] = COLOR.RED;
    colors[1] = COLOR.GREEN;
    colors[2] = COLOR.BLUE;
    colors[3] = COLOR.YELLOW;
    for (int i = 0; i < colors.length; i++) {
      // Hbox that contains each player
      HBox hbox = new HBox();
      // Color
      COLOR color = colors[i];
      // Color Name
      Text colorName = new Text(color.toString());
      colorName.setFont(Josefin);
      hbox.getChildren().addAll(colorName);
      hbox.setTranslateY(100 * (i + 1));
      hbox.setTranslateX(200);
      // hbox.setAlignment(Pos.CENTER);

      // Color Status texts
      Text status = new Text("");
      Text turns = new Text("");
      if (game.getBannedColors().containsKey(color)) {
        status.setText("BANNED");
        status.setFont(Josefin);
        status.setY(hbox.getTranslateY() + 17);
        status.setX(340);
        status.setFill(Color.RED);
        // status.setX(140);

        // Turns left until DesBanned
        turns.setText((game.getBannedColors().get(color) + 1) + " Turns left");
        Font turnsFont = Font.loadFont(
            GUIView.class.getResourceAsStream("/Fonts/JoseficSans/JosefinSans-Bold.ttf"), 30);
        turns.setFont(turnsFont);
        turns.setY(hbox.getTranslateY() + 17);
        turns.setX(540);
        group.getChildren().addAll(hbox, status, turns);
      } else {
        status.setText("NOT BANNED");
        status.setFont(Josefin);
        status.setY(hbox.getTranslateY() + 17);
        status.setX(340);
        status.setFill(Color.LIMEGREEN);
        // status.setX(140);
        group.getChildren().addAll(hbox, status);
      }
    }
    group.setTranslateY(30);
    group.setTranslateX(30);
    // Scene
    root.setContent(group);
    Scene scene = new Scene(root, 600, 400);
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();
  }

  public COLOR ChooseColorAlert() {

    // root(Group)
    Group root = new Group();
    // Scene
    Scene scene = new Scene(root, 460, 200);
    // Creating line object
    Line line = new Line();
    line.setStartX(0);
    line.setStartY(80);
    line.setEndX(700);
    line.setEndY(80);

    // Image (i)
    Image i = new Image(GUIView.class.getResourceAsStream("/Images/InfoIcons/i.png"));
    ImageView iView = new ImageView(i);
    iView.setLayoutX(360);
    iView.setLayoutY(0);
    iView.setScaleX(0.6);
    iView.setScaleY(0.6);
    // Creating Text
    Text action = new Text("Action Required");
    action.setY(50);
    action.setX(30);
    action.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 22));
    // Choose text
    Text choose = new Text("Choose new color:");
    choose.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 17));
    choose.setY(141);
    choose.setX(30);

    // ChoiceBox for colors
    ChoiceBox<String> colorBox = new ChoiceBox<String>();
    colorBox.getItems().addAll("Red", "Green", "Blue", "Yellow");
    if (game.getBannedColors().containsKey(COLOR.RED)) {
      colorBox.getItems().remove("Red");
    }
    if (game.getBannedColors().containsKey(COLOR.GREEN)) {
      colorBox.getItems().remove("Green");
    }
    if (game.getBannedColors().containsKey(COLOR.BLUE)) {
      colorBox.getItems().remove("Blue");
    }
    if (game.getBannedColors().containsKey(COLOR.YELLOW)) {
      colorBox.getItems().remove("Yellow");
    }

    colorBox.setLayoutX(200);
    colorBox.setLayoutY(120);
    colorBox.setMinWidth(180);
    colorBox.getSelectionModel().select(0);

    // Select Button
    Button select = new Button("Select");
    select.setLayoutX(380);
    select.setLayoutY(170);
    select.setStyle("-fx-padding: 5 12 5 12;");

    // Stage
    root.getChildren().addAll(line, action, choose, colorBox, select, iView);
    Stage stage = new Stage();
    // Close Stage with select button
    select.setOnAction(new ExitWindow(stage));
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();
    return COLOR.valueOf(colorBox.getValue().toUpperCase());
  }

  public void UNOAlert(IPlayer player) {
    // root(Group)
    Group root = new Group();
    // Scene
    Scene scene = new Scene(root, 460, 200);
    // Creating line object
    Line line = new Line();
    line.setStartX(0);
    line.setStartY(80);
    line.setEndX(700);
    line.setEndY(80);

    // Image (i)
    Image i = new Image(GUIView.class.getResourceAsStream("/Images/InfoIcons/i.png"));
    ImageView iView = new ImageView(i);
    iView.setLayoutX(360);
    iView.setLayoutY(0);
    iView.setScaleX(0.6);
    iView.setScaleY(0.6);
    // Creating Text
    Text action = new Text("¡UNO!");
    action.setY(50);
    action.setX(30);
    action.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 26));
    // Choose text
    Text choose = new Text(player + " said UNO!");
    choose.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 17));
    choose.setY(141);
    choose.setX(30);

    // Select Button
    Button ok = new Button("OK");
    ok.setLayoutX(380);
    ok.setLayoutY(170);
    ok.setStyle("-fx-padding: 5 12 5 12;");



    // Stage
    root.getChildren().addAll(line, action, choose, ok, iView);
    Stage stage = new Stage();
    // Close Stage with select button
    ok.setOnAction(new ExitWindow(stage));
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();

  }

  public void WinnerAlert(IPlayer player) {

    // root(Group)
    Group root = new Group();
    // Scene
    Scene scene = new Scene(root, 460, 200);
    // Creating line object
    Line line = new Line();
    line.setStartX(0);
    line.setStartY(80);
    line.setEndX(700);
    line.setEndY(80);

    // Image (i)
    Image i = new Image(GUIView.class.getResourceAsStream("/Images/InfoIcons/Winner.png"));
    ImageView iView = new ImageView(i);
    iView.setLayoutX(220);
    iView.setLayoutY(-10);
    iView.setScaleX(0.4);
    iView.setScaleY(0.4);
    // Creating Text
    Text action = new Text("WINNER!");
    action.setY(50);
    action.setX(30);
    action.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 26));
    // Choose text
    Text choose = new Text(player + " wins the game!\nCongratulations :D");
    choose.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 17));
    choose.setY(141);
    choose.setX(30);

    // Stage
    root.getChildren().addAll(line, action, choose, iView);
    Stage stage = new Stage();
    // Close Stage with select button
    stage.setResizable(false);
    stage.setTitle("JavaUNO GUI");
    stage.setScene(scene);
    stage.setOnCloseRequest(e -> Platform.exit());
    Color c = Color.web("#2B2B2B", 0);
    scene.setFill(c);
    stage.showAndWait();


  }

  public void WaitAlert(double i) {
    try {
      Stage stage = new Stage();
      stage.initStyle(StageStyle.TRANSPARENT);
      stage.toBack();
      // Wait
      PauseTransition delay = new PauseTransition(Duration.seconds(i));
      delay.setOnFinished(event -> stage.close());
      delay.play();
      stage.showAndWait();
    } catch (Exception e) {
      System.out.println("");
    }

  }

  public static IGameLogic MakeGame() {
    DeckBuilder DB = new DeckBuilder();
    // DB.SetBanStrategy();
    // DB.SetDIOStrategy();
    ICardPile Deck = DB.createDeck();
    IPlayerListBuilder playerBuilder = new PlayerListBuilder();
    IPlayer Player1 = new HumanPlayer("Seba");
    IPlayer PlayerR1 = new RandomPlayer("R2D2");
    IPlayer PlayerR3 = new RandomPlayer("Bender");
    IPlayer PlayerR4 = new RandomPlayer("UNOBOT");
    IPlayer franco = new HumanPlayer("Franco");
    IPlayer juan = new HumanPlayer("Juan");
    IPlayer marco = new HumanPlayer("Marco");
    IPlayer matilde = new HumanPlayer("Matilde");
    playerBuilder.addPlayer(PlayerR1);
    playerBuilder.addPlayer(franco);
    playerBuilder.addPlayer(juan);
    playerBuilder.addPlayer(marco);
    playerBuilder.addPlayer(matilde);
    playerBuilder.addPlayer(Player1);
    playerBuilder.addPlayer(PlayerR3);
    playerBuilder.addPlayer(PlayerR4);
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
    LastCards.setText(LastPlayer.getHandSize() + " cards");
    CurrentCards.setText(CurrentPlayer.getHandSize() + " cards");
    NextCards.setText(NextPlayer.getHandSize() + " cards");
    DiscardText.setText(game.getCardManager().sizeofDiscard() + "Cards");
    DeckText.setText(game.getCardManager().getDrawableCardsNumber()
        - game.getCardManager().sizeofDiscard() + "Cards");
    hhand.getChildren().clear();
    hhand.setMinWidth(1790);
    // Cards in hand
    if (CurrentPlayer.isHuman()) {
      for (int i = this.FirstCardIndex; i < Math.min(game.getCurrentPlayer().getHandSize(),
          this.FirstCardIndex + 7); i++) {
        ICard Card = game.getCurrentPlayer().getCardFromHand(i);
        Image CardImage = new Image(GUIView.class.getResourceAsStream(
            "/Images/UNOCards/" + Card.getColor() + "/" + Card.getSymbol() + ".png"));
        ImageView CardView = new ImageView(CardImage);
        CardView.setScaleX(CardScale);
        CardView.setScaleY(CardScale);
        CardView.setTranslateY(HandOffsetY);
        CardView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
            new CardHandler(Card, game, ctrl));

        hhand.getChildren().add(CardView);
      }

    } else {
      for (int i = 0; i < game.getCurrentPlayer().getHandSize(); i++) {
        Image Card = new Image(GUIView.class.getResourceAsStream("/Images/UNOCards/NULL.png"));
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
    if ((Boolean) arg) {
      this.FirstCardIndex = 0;
      if (game.getNextPlayer().hasWon()) {
        this.WinnerAlert(CurrentPlayer);
        Platform.exit();
        System.exit(0);
      }
      ctrl.playTurn();
    } else {
      if (this.CurrentPlayer.isHuman()) {
        this.PlayError();
      }
    }

  }

}
