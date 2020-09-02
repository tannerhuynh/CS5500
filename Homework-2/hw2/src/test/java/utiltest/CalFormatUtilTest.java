package utiltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.TimeZone;

import util.CalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CalFormatUtilTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void constructorFail() throws NoSuchMethodException, IllegalAccessException,
          InvocationTargetException, InstantiationException {
    Constructor<CalFormat> constructor = CalFormat.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void mapDate() {
    String date1 = "Thu Dec 19 05:15:00 GMT 2019";
    String date2 = "Sat Nov 16 20:15:00 GMT 2019";

    Calendar calendar1 = CalFormat.map(date1);
    Calendar calendar2 = CalFormat.map(date2);

    assertEquals("Thu Dec 19 05:15:00 " + calendar1.getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019", calendar1.getTime().toString());
    assertEquals("Sat Nov 16 20:15:00 " + calendar2.getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) +" 2019", calendar2.getTime().toString());
  }
}