package test;
import org.junit.Test;

import model.card.CardPile;
import model.card.ICardPile;
import model.card.type.NullCard;

import static org.junit.Assert.*;

import org.junit.Before;
public class CardPileTest {
  private ICardPile Deck;
  private ICardPile Deck2;
  @Before
  public void setUp() {
    Deck=new CardPile();
    Deck2=new CardPile();
  }
  @Test
  public void isEmptyTest(){
    assertTrue(Deck.isEmpty());
  }
  @Test
  public void pushCardsTest(){
    for(int i =0;i<4;i++){
    Deck.pushCard(new NullCard());
    Deck2.pushCard(new NullCard());
    }
    assertEquals(Deck.getSize(),4);
    assertEquals(Deck2.getSize(),4);
    Deck.pushCards(Deck2);
    assertTrue(Deck2.isEmpty());
    assertEquals(Deck.getSize(),8);
  }
}
