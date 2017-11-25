package model;

import java.util.ArrayList;
import java.util.Observable;

import controller.IController;
import model.card.CardPilesManager;
import model.card.ICardPile;
import model.card.ICardPilesManager;
import model.card.type.ICard;
import model.player.IPlayerManager;
import model.player.PlayerManager;
import model.player.type.IPlayer;
import view.GUIView;

/**
 * Represents the complete game logic.
 * 
 * @author Sebastian Alfaro
 * 
 */
public class GameLogic  extends AbstractGameLogic  {
  protected boolean ended = false;
  protected ICardPilesManager CardM;
  protected IPlayerManager PlayerM;
  protected int DrawWell = 0;

  public GameLogic(ArrayList<IPlayer> arrayList, ICardPile Deck) {
    
    this.PlayerM = new PlayerManager(arrayList);
    this.CardM = new CardPilesManager(Deck);
    for (IPlayer player : this.PlayerM.getPlayers()) {
      if (CardM.getDrawableCardsNumber() >= 7) {
        CardM.addCardsToPlayer(player, 20);
      }
    }
  }

  @Override
  public boolean hasEnded() {
    return ended;
    
  }

  @Override
  public IPlayer getCurrentPlayer() {
    return PlayerM.getCurrentPlayer();
  }

  @Override
  public IPlayer getLastPlayer() {
    return PlayerM.getLastPlayer();
  }

  @Override
  public IPlayer getNextPlayer() {
    return PlayerM.getNextPlayer();
  }

  @Override
  public ICard getCurrentPlayedCard() {
    return CardM.getCurrentPlayedCard();
  }

  @Override
  public void autoShoutUNO(IController ctrl) {
    if ((this.getCurrentPlayer().hasOneCard()) && (!this.getCurrentPlayer().hasSaidUNO())) {
      ctrl.showMessage("UNO!");
      getCurrentPlayer().setSaidUNO(true);
    }
  }

  @Override
  public void startTurn(IController ctrl) {
    PlayerM.startTurn();
    autoShoutUNO(ctrl);
    if (!this.isDrawWellEmpty()) {
      this.drawCardsFromWell(getCurrentPlayer(), ctrl);
      this.resetDrawWell();
      this.skipPlayer();
    }



  }

  @Override
  public void skipPlayer() {
    PlayerM.skipPlayer();

  }

  @Override
  public void addToDrawWell(int number) {
    this.DrawWell += number;

  }

  @Override
  public void resetDrawWell() {
    this.DrawWell = 0;

  }

  @Override
  public boolean isDrawWellEmpty() {
    return (this.DrawWell == 0);
  }

  @Override
  public void drawCardsFromWell(IPlayer player, IController ctrl) {
    this.CardM.addCardsToPlayer(player, DrawWell);

  }

  @Override
  public void invertDirection() {
    PlayerM.invertDirection();

  }

  @Override
  public boolean playCard(ICard playedCard, IController ctrl) {
    if (playedCard.isPlayableOver(CardM.getCurrentPlayedCard())) {
      this.getCurrentPlayer().removeCardFromHand(playedCard);
      playedCard.executeAction(this, ctrl);
      CardM.discard(playedCard);
      setChanged();
      notifyObservers(playedCard);
      if (getCurrentPlayer().getHandSize() == 0) {
        this.ended = true;
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public ICard drawOneCard(IPlayer player) {
    ICard Card = CardM.drawCard();
    ArrayList<ICard> AL = new ArrayList<ICard>();
    AL.add(Card);
    this.getCurrentPlayer().addToHand(AL);
    player.setSaidUNO(false);
    return Card;
  }

  @Override
  public void announceWinner(IController ctrl) {
    ctrl.showMessage("The Winner is: " + getCurrentPlayer());

  }

  @Override
  public ICardPilesManager getCardManager() {
   return this.CardM;
  }





}
