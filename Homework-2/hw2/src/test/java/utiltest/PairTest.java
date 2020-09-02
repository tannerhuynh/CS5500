package utiltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ticket.Locations;
import util.Pair;

import static org.junit.Assert.*;

public class PairTest {
  Pair<Locations, Locations> loc1 = new Pair<>(Locations.BOS, Locations.LAX);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getLeft() {
    assertEquals(Locations.BOS, loc1.getLeft());
  }

  @Test
  public void getRight() {
    assertEquals(Locations.LAX, loc1.getRight());
  }

  @Test
  public void setRight() {
    loc1.setRight(Locations.LOS);
    assertEquals(Locations.LOS,loc1.getRight() );
  }

  @Test
  public void testEquals() {
    Pair<Locations, Locations> loc2 = new Pair<>(Locations.BOS, Locations.LAX);
    assertTrue(loc2.equals(loc1));
  }
}