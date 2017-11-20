package test;
import org.junit.Test;

import controller.IController;
import controller.NullController;
import model.GameLogic;
import model.IGameLogic;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.card.type.CardNum;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

public class RandomPlayerTest {
  private ICardPile Deck;
  private IGameLogic game;
  private IController ctrl;
  IPlayer Player1;
  IPlayer Player2;
  IPlayer Player3;
  ArrayList <ICard> hand;
  @Before
  public void setUp() {
    DeckBuilder DB = new DeckBuilder();
    DB.SetTestStrategy();
    Deck = DB.createDeck();
    IPlayerListBuilder playerBuilder = new PlayerListBuilder();
    Player1 = new RandomPlayer(1);
    Player2 = new RandomPlayer(2);
    Player3 = new HumanPlayer(3);
    playerBuilder.addPlayer(Player1);
    playerBuilder.addPlayer(Player2);
    playerBuilder.addPlayer(Player3);
    hand=new ArrayList<ICard>();
    ArrayList<IPlayer> AL = playerBuilder.buildPlayerList();
    for (int i = 0; i < 3; i++) {
      Deck.pushCard(new CardNum(Color.GREEN, Symbol.ONE));
    }
    for (int i = 0; i < 3; i++) {
      hand.add(new CardNum(Color.GREEN, Symbol.ONE));
    }
    hand.add(new CardNum(Color.RED, Symbol.FOUR));
    game = new GameLogic(AL, Deck);
    ctrl = new NullController(game);
    game.startTurn(ctrl);
  }
  @Test
  public void getCardToPlayTest(){
    ICard CartaDistinta=new CardNum(Color.YELLOW, Symbol.SEVEN);
    Player1.addToHand(hand);
    game.playCard(CartaDistinta, ctrl);
    Player1.getCardToPlay(game, ctrl);
    game.playCard(new CardNum(Color.GREEN, Symbol.ONE), ctrl);
    Player1.getCardToPlay(game, ctrl);
    Player2.getCardToPlay(game, ctrl);
  }
  @Test
  public void getColor(){
    assertNotNull( Player1.selectColor(game, ctrl));
  }
}
