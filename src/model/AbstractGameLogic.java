package model;

import java.util.Observable;

import controller.IController;
import model.card.ICardPilesManager;
import model.card.type.ICard;
import model.player.type.IPlayer;
import view.GUIView;

/**
 * Represents the complete game logic.
 * 
 * @author eriveros
 *
 */
public abstract class AbstractGameLogic extends Observable implements IGameLogic  {
  
}
