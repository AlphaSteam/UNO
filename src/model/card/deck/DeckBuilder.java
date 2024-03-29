package model.card.deck;

import model.card.ICardPile;

/**
 * Builder that uses the Strategy Pattern to create a UNO deck
 * 
 * @author Sebastian Alfaro
 *
 */
public class DeckBuilder {
  private IDeckStrategy Strategy;

  /**
   * DeckBuilder constructor Default behaviour corresponds to the Normal deck strategy.
   */
  public DeckBuilder() {
    Strategy = new NormalDeckStrategy();
  }

  /**
   * Sets the strategy of the builder to make a test deck.
   */
  public void SetTestStrategy() {
    Strategy = new TestDeckStrategy();
  }

  /**
   * Sets the strategy of the builder to make a normal deck.
   */
  public void SetNormalStrategy() {
    Strategy = new NormalDeckStrategy();
  }

  /**
   * Sets the strategy of the builder to make a numeric deck.
   */
  public void SetNumericStrategy() {
    Strategy = new NumericDeckStrategy();
  }

  /**
   * Sets the strategy of the builder to make a deck full of Temporal Ban cards.
   */
  public void SetBanStrategy() {
    Strategy = new BanDeckStrategy();
  }

  /**
   * Sets the strategy of the builder to make a deck full of ZAWARUDO cards.
   */
  public void SetDIOStrategy() {
    Strategy = new ZAWARUDODeckStrategy();
  }

  /**
   * Creates the deck with the chosen strategy.
   * 
   * @return ICardPile, that is the deck.
   */
  public ICardPile createDeck() {
    return (Strategy.createDeck());
  }

}
