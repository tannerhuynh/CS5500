package tickettest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ticket.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LocationsTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getName() {
    assertEquals("Boston, Massachusetts", Locations.BOS.getName());
    assertEquals("Los Angeles, California", Locations.LAX.getName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void findFail() {
    try {
      Locations bos = Locations.find("bos");
    } catch (IllegalArgumentException e) {
      String message = "Invalid value for enum in JSON file.";
      assertEquals(message, e.getMessage());
      throw e;
    }
    fail("Did not catch try.");
  }

  @Test
  public void find() {
    assertEquals(Locations.BOS, Locations.find("BOS"));
    assertEquals(Locations.LAX, Locations.find("LAX"));
    assertEquals(Locations.IAH, Locations.find("IAH"));
  }

  @Test
  public void getRandom() {
    Locations random1 = Locations.getRandom();
    assertEquals(random1, Locations.find(random1.toString()));
  }
}