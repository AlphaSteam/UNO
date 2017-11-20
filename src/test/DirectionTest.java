package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.player.Direction;

public class DirectionTest {
 Direction Dir;
 Direction Dir2;
  @Before
  public void setUp() {
    Dir=Direction.CLOCKWISE;
    Dir2=Direction.COUNTERCLOCKWISE;
  }
  @Test
  public void getNameTest(){
    assertEquals(Dir.getName(), "Sentido Horario");
    assertEquals(Dir2.getName(), "Sentido Antihorario");
  }
  @Test
  public void getValueTest(){
    assertEquals(Dir.getValue(), -1);
    assertEquals(Dir2.getValue(), 1);
  }
}

