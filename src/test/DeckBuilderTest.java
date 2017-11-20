package test;
import org.junit.Test;

import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import static org.junit.Assert.*;

import org.junit.Before;
public class DeckBuilderTest {
  DeckBuilder DB;
  @Before
  public void setUp() {
    DB = new DeckBuilder();
  }

  @Test
  public void NormalDeckTest(){
    DB.SetNormalStrategy();
    ICardPile Deck=DB.createDeck();
    assertEquals(Deck.getSize(),108);
  }
  @Test
  public void NumericDeckTest(){
    DB.SetNumericStrategy();
    ICardPile Deck=DB.createDeck();
    assertEquals(Deck.getSize(),76);
  }
  @Test
  public void TestDeckTest(){
    DB.SetTestStrategy();
    ICardPile Deck=DB.createDeck();
    assertEquals(Deck.getSize(),0);
  }
}
