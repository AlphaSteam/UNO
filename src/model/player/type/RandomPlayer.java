package model.player.type;

import java.util.ArrayList;
import java.util.HashMap;

import controller.IController;
import model.IGameLogic;
import model.card.type.COLOR;
import model.card.type.DrawCard;
import model.card.type.ICard;

/**
 * Random player, this type of player chooses his cards through random number generation.
 * 
 * @author Sebastian
 *
 */
public class RandomPlayer extends AbstractPlayer {
  private int i = 0;
  private boolean Numbered;
  private String s;
  private ArrayList<ICard> Playable = new ArrayList<ICard>();

  public RandomPlayer(int i) {
    this.i = i;
    this.human=false;
    this.Numbered=true;
  }
  public RandomPlayer(String s) {
    if (s.length() > 8) {
      s = s.substring(0, 8);
    }
    this.s=s;
    this.human=false;
    this.Numbered=false;
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl,HashMap<COLOR,Integer> map) {
    if (!this.needsToDrawCard(game.getCurrentPlayedCard(),map)) {
      for (int j = 0; j < this.getHandSize(); j++) {
        if (this.getCardFromHand(j).isPlayableOver(game.getCurrentPlayedCard(),map)) {
          Playable.add(getCardFromHand(j));
        }
      }
      return Playable.get((int) (Math.random() * Playable.size() - 2) + 1);
    } else {
      return new DrawCard();
    }

  }

  @Override
  public String toString() {
    String result; 
    if(this.Numbered){
    result= "Player " + this.i;
    }
    else{
      result=s;
    }
    return result;
  }

  @Override
  public COLOR selectColor(IGameLogic game, IController ctrl) {
     int Red=0;
     int Blue=0;
     int Green=0;
     int Yellow=0;
     for(int i=0;i<this.getHandSize();i++){
       if(this.getCardFromHand(i).getColor()==COLOR.RED){
         Red++;
       }
       else if(this.getCardFromHand(i).getColor()==COLOR.BLUE){
         Blue++;
       }
       else if(this.getCardFromHand(i).getColor()==COLOR.GREEN){
         Green++;
       }
       else if(this.getCardFromHand(i).getColor()==COLOR.YELLOW){
         Yellow++;
       }
     }
     
     
     int Max=Math.max(Math.max(Red,Blue),Math.max(Green,Yellow));
     if (Max==Red){
       return COLOR.RED;
     }
     else if(Max==Blue){
       return COLOR.BLUE;
     }
     else if(Max==Green){
       return COLOR.GREEN;
     }
     else{
       return COLOR.YELLOW;
     }
     
    
  }

}
