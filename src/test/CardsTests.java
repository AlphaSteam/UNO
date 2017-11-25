package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.IController;
import controller.NullController;
import model.GameLogic;
import model.IGameLogic;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.card.type.CardComodin4;
import model.card.type.CardComodinColor;
import model.card.type.CardInvertir;
import model.card.type.CardNum;
import model.card.type.CardSaltar;
import model.card.type.COLOR;
import model.card.type.DrawCard;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class CardsTests {
  private ICardPile Deck;
  private IGameLogic game;
  private IController ctrl;
  IPlayer Player1;
  IPlayer Player2;
  IPlayer Player3;
  @Before
  public void setUp() {
    DeckBuilder DB = new DeckBuilder();
    DB.SetTestStrategy();
    Deck = DB.createDeck();
    IPlayerListBuilder playerBuilder = new PlayerListBuilder();
    Player1 = new RandomPlayer(1);
    Player2 = new RandomPlayer(2);
    Player3 = new RandomPlayer(3);
    playerBuilder.addPlayer(Player1);
    playerBuilder.addPlayer(Player2);
    playerBuilder.addPlayer(Player3);
    ArrayList<IPlayer> AL = playerBuilder.buildPlayerList();
    for (int i = 0; i < 30; i++) {
      Deck.pushCard(new CardNum(COLOR.GREEN, Symbol.ONE));
    }
    game = new GameLogic(AL, Deck);
    ctrl = new NullController(game);
    game.startTurn(ctrl);
  }

  @Test
  public void Comodin4Test() {
ICard Comodin4=new CardComodin4();
assertEquals(game.getCurrentPlayer(),Player1);
game.playCard(Comodin4, ctrl);
game.startTurn(ctrl);
game.startTurn(ctrl);
game.startTurn(ctrl);
assertEquals(game.getCurrentPlayer(),Player2);
assertEquals(game.getCurrentPlayer().getHandSize(),11);


  }

  @Test
  public void CardComodinColorTest() {
    ICard Comodin4=new CardComodinColor();
    game.playCard(Comodin4, ctrl);
  }

  @Test
  public void DrawCardTest() {
    ICard Draw=new DrawCard();
    assertEquals(game.getCurrentPlayer().getHandSize(), 7);
    game.playCard(Draw, ctrl);
    assertEquals(game.getCurrentPlayer().getHandSize(), 8);
  }

  @Test
  public void InvertDirectionCardTest() {
    ICard Invertir=new CardInvertir(COLOR.GREEN);
    assertEquals(game.getCurrentPlayer(),Player1);
    game.playCard(Invertir, ctrl);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player2);
    game.playCard(Invertir, ctrl);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
  }
  @Test
  public void SkipPlayerTest() {
    ICard Saltar=new CardSaltar(COLOR.GREEN);
    assertEquals(game.getCurrentPlayer(),Player1);
    game.playCard(Saltar, ctrl);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player1);
    
  }

}

