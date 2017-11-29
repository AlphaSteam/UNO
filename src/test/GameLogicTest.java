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
import model.card.type.CardNum;
import model.card.type.CardRobar2;
import model.card.type.COLOR;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;

public class GameLogicTest {
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
    Player1 = new HumanPlayer(1);
    Player2 = new HumanPlayer(2);
    Player3 = new HumanPlayer(3);
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
  public void playCardTest() {

    ICard Numero2 = new CardNum(COLOR.GREEN, Symbol.ONE);
    assertTrue(game.playCard(Numero2, ctrl));
    ICard Numero3 = new CardNum(COLOR.RED, Symbol.EIGHT);
    assertFalse(game.playCard(Numero3, ctrl));


  }

  @Test
  public void DrawOneCardTest() {
    assertEquals(game.getCurrentPlayer().getHandSize(), 7);
    game.drawOneCard(game.getCurrentPlayer());
    assertEquals(game.getCurrentPlayer().getHandSize(), 8);
  }

  @Test
  public void GetCurrentPlayedCardTest() {
    assertNotNull(game.getCurrentPlayedCard());
  }

  @Test
  public void IsDrawWellEmptyTest() {
    assertTrue(game.isDrawWellEmpty());
    game.playCard(new CardRobar2(COLOR.GREEN), ctrl);
    assertFalse(game.isDrawWellEmpty());

  }

  @Test
  public void drawCardsFromWellTest() {
    game.playCard(new CardRobar2(COLOR.GREEN), ctrl);
    game.drawCardsFromWell(game.getCurrentPlayer(), ctrl);
    assertEquals(game.getCurrentPlayer().getHandSize(), 9);
  }

  @Test
  public void ResetDrawWellTest() {
    game.resetDrawWell();
    assertTrue(game.isDrawWellEmpty());
  }

  @Test
  public void AutoShoutUNOTest() {
    assertEquals(game.getCurrentPlayer().getHandSize(), 7);
    game.autoShoutUNO(ctrl);
    assertFalse(game.getCurrentPlayer().hasSaidUNO());
    for (int i = 0; i < 6; i++) {
      game.getCurrentPlayer().removeCardFromHand(game.getCurrentPlayer().getCardFromHand(0));
    }
    assertEquals(game.getCurrentPlayer().getHandSize(), 1);
    game.autoShoutUNO(ctrl);
    assertTrue(game.getCurrentPlayer().hasSaidUNO());
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(new CardNum(COLOR.BLUE, Symbol.DRAW_TWO));
    game.getCurrentPlayer().addToHand(hand);
    assertEquals(game.getCurrentPlayer().getHandSize(), 2);
    game.autoShoutUNO(ctrl);
    assertTrue(game.getCurrentPlayer().hasSaidUNO());
  }

  @Test
  public void InvertDirectionTest() {
    assertEquals(game.getCurrentPlayer(),Player1);
    game.invertDirection();
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player2);
    game.invertDirection();
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
  }
  @Test
  public void SkipPlayerTest() {
    assertEquals(game.getCurrentPlayer(),Player1);
    game.skipPlayer();
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player3);
    game.startTurn(ctrl);
    assertEquals(game.getCurrentPlayer(),Player1);
    
  }
  @Test
  public void hasEndedTest() {
    for (int i = 0; i <6 ; i++) {
      game.getCurrentPlayer().removeCardFromHand(game.getCurrentPlayer().getCardFromHand(0));
    }
    assertTrue(game.getCurrentPlayer().hasOneCard());
    assertFalse(game.hasEnded());
    game.playCard(game.getCurrentPlayer().getCardFromHand(0), ctrl);
    assertTrue(game.hasEnded());
  }
  
  @Test
  public void StartTurn(){
    game.playCard(new CardRobar2(COLOR.GREEN), ctrl);
    assertFalse(game.isDrawWellEmpty());
    game.startTurn(ctrl);
    assertEquals(Player2.getHandSize(),9);
  }
  @Test
  public void AnnounceWinner(){
    game.announceWinner(ctrl,game.getLastPlayer());
  }
}

