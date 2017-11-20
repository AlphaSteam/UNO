package model.player;

import java.util.ArrayList;

import model.player.type.IPlayer;

/**
 * Implementation of the PlayerListBuilder Interface.
 * 
 * @author Sebastian
 *
 */
public class PlayerListBuilder implements IPlayerListBuilder {
  protected ArrayList<IPlayer> PList = new ArrayList<IPlayer>();
  protected ArrayList<IPlayer> PList2 = new ArrayList<IPlayer>();

  @Override
  public void addPlayer(IPlayer player) {
    PList.add(player);

  }

  @Override
  public ArrayList<IPlayer> buildPlayerList() {
    PList2 = PList;
    PList = new ArrayList<IPlayer>();
    return PList2;
  }

}
