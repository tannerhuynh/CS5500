package factorytest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.ClassOfService;
import factory.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class ClassOfServiceTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test (expected = IllegalArgumentException.class)
  public void findEnumFail() {
    try {
      Locations economy = Locations.find("ECONOMY");
    } catch (IllegalArgumentException e) {
      String message = "Invalid value for enum in JSON file.";
      assertEquals(message, e.getMessage());
      throw e;
    }
    fail("Did not catch try.");
  }

  @Test
  public void find() {
    assertEquals(ClassOfService.ECONOMY, ClassOfService.find("ECONOMY"));
    assertEquals(ClassOfService.FIRST_CLASS, ClassOfService.find("FIRST_CLASS"));
    assertEquals(ClassOfService.COACH, ClassOfService.find("COACH"));
    assertEquals(ClassOfService.SLEEPER_CABIN, ClassOfService.find("SLEEPER_CABIN"));
    assertNull(ClassOfService.find("null"));
  }

  @Test
  public void getRandom() {
    ClassOfService random1 = ClassOfService.getRandom();
    assertEquals(random1, ClassOfService.find(random1.toString()));
  }
}