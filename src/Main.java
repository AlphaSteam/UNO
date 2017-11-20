import java.util.ArrayList;

import controller.ConsoleController;
import model.GameLogic;
import model.IGameLogic;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.player.IPlayerListBuilder;
import model.player.PlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import view.ConsoleView;

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
    ConsoleView view = new ConsoleView(game);
    ConsoleController ctrl = new ConsoleController(game, view);
    while (!game.hasEnded()) {
      ctrl.playTurn();
    }
    game.announceWinner(ctrl);
  }
}
