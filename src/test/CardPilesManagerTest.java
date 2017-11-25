package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.card.CardPilesManager;
import model.card.ICardPile;
import model.card.deck.DeckBuilder;
import model.card.type.CardComodin4;
import model.card.type.CardNum;
import model.card.type.ICard;
import model.card.type.NullCard;

public class CardPilesManagerTest {
  private ICardPile Deck;
  private CardPilesManager CPM;
  private ICard carta;
  @Before
  public void setUp() {
  DeckBuilder DB = new DeckBuilder();
  DB.SetTestStrategy();
  Deck = DB.createDeck();
  carta = new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE);
  Deck.pushCard(new CardComodin4());
  Deck.pushCard(carta);

 
  }
  @Test
  public void DrawCardsTest(){
  CPM=new CardPilesManager(Deck);
  assertEquals(CPM.getDrawableCardsNumber(),1);
  CPM.discard(new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE));
  CPM.discard(new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE));
  CPM.discard(new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE));
  CPM.discard(new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE));
  assertEquals(CPM.getDrawableCardsNumber(),5);
  assertEquals(Deck.getSize(),1);
  assertEquals(CPM.drawCards(3).size(),3);
  assertEquals(Deck.getSize(),1);
  assertEquals(CPM.getDrawableCardsNumber(),2);
  assertEquals(CPM.drawCards(30).size(),2);
  }
  @Test
  public void CardPilesManagerInstanciateTest(){
    CPM=new CardPilesManager(Deck);
    assertEquals(CPM.getCurrentPlayedCard(),carta);
  }
  @Test
  public void DiscardTest(){
    CPM=new CardPilesManager(Deck);
    CPM.discard(new CardNum(model.card.type.COLOR.GREEN, model.card.type.Symbol.ONE));
    assertEquals(CPM.getDrawableCardsNumber(),2);
    CPM.discard(new NullCard());
    assertEquals(CPM.getDrawableCardsNumber(),2);
  }
  
}

