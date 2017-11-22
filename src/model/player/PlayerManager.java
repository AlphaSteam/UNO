package model.player;

import java.util.ArrayList;

import model.player.type.IPlayer;

/**
 * Implementation of the PlayerListManager Interface.
 * 
 * @author Sebastian
 *
 */
public class PlayerManager implements IPlayerManager {
  protected Direction Dir = Direction.COUNTERCLOCKWISE;
  protected IPlayerListBuilder PB;
  protected ArrayList<IPlayer> players;
  protected int index = -1;

  /**
   * Default constructor for PlayerManager.
   * 
   * @param players List of players to be managed.
   */
  public PlayerManager(ArrayList<IPlayer> players) {
    this.players = players;
  }

  @Override
  public IPlayer getCurrentPlayer() {
    return players.get(index);
  }
  @Override
  public IPlayer getLastPlayer() {
    if (this.Dir == Direction.COUNTERCLOCKWISE) {
      if (index < 0) {
        return(players.get(index-1));
      } else {
        return(players.get(players.size()-1));
      }
    } else {
      if (index < getPlayers().size() - 1 ) {
        return(players.get(index+1));
      } else {
        return(players.get(0));
      }
    }
  }

  @Override
  public IPlayer getNextPlayer() {
    if (this.Dir == Direction.COUNTERCLOCKWISE) {
      if (index < getPlayers().size() - 1) {
        return(players.get(index+1));
      } else {
        return(players.get(0));
      }
    } else {
      if (index > 0) {
        return(players.get(index-1));
      } else {
        return(players.get(players.size()-1));
      }
    }
  }

  @Override
  public ArrayList<IPlayer> getPlayers() {
    return players;
  }

  @Override
  public void invertDirection() {
    if (Dir == Direction.CLOCKWISE) {
      Dir = Direction.COUNTERCLOCKWISE;
    } else {
      Dir = Direction.CLOCKWISE;
    }

  }



  @Override
  public void startTurn() {
    index=this.players.indexOf(getNextPlayer());
  }

  @Override
  public void skipPlayer() {
this.startTurn();

  }

  

}
