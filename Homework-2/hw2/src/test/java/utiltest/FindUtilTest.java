package utiltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import ticket.Locations;
import util.Find;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

public class FindUtilTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void constructorFail() throws NoSuchMethodException, IllegalAccessException,
          InvocationTargetException, InstantiationException {
    Constructor<Find> constructor = Find.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test (expected = IllegalArgumentException.class)
  public void findEnumFail() {
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
  public void findEnum() {
    Locations bos = Locations.find("BOS");
    assertEquals("Boston, Massachusetts", bos.getName());
  }
}