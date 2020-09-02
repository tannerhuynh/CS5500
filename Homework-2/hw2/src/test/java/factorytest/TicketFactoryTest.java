package factorytest;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.TimeZone;

import factory.TicketFactory;
import factory.ITicket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TicketFactoryTest {
  private JSONObject busTicketInfo = new JSONObject();
  private JSONObject planeTicketInfo = new JSONObject();
  private JSONObject trainTicketInfo = new JSONObject();
  private JSONObject boatTicketInfo = new JSONObject();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void constructorFail() throws NoSuchMethodException, IllegalAccessException,
          InvocationTargetException, InstantiationException {
    Constructor<TicketFactory> constructor = TicketFactory.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test (expected = IllegalArgumentException.class)
  public void createTicketFail() {
    boatTicketInfo.put("type", "Boat");
    boatTicketInfo.put("passenger", "Mary Grace");
    boatTicketInfo.put("route", "ID2314");
    boatTicketInfo.put("departure date", "Thu Dec 19 05:15:00 GMT 2019");
    boatTicketInfo.put("arrival date", "Fri Dec 20 08:24:00 GMT 2019");
    boatTicketInfo.put("departure location", "BOS");
    boatTicketInfo.put("arrival location", "ATL");
    boatTicketInfo.put("class", "FIRST_CLASS");

    try {
      ITicket ticket4 = TicketFactory.create(boatTicketInfo);
    } catch (IllegalArgumentException e) {
      String message = "Not a valid method of transportation.";
      assertEquals(message, e.getMessage());
      throw e;
    }
    fail("Did not catch try.");
  }

  @Test
  public void createTicket() {
    busTicketInfo.put("type", "Bus");
    busTicketInfo.put("passenger", "Mary Grace");
    busTicketInfo.put("route", "ID2314");
    busTicketInfo.put("departure date", "Thu Dec 19 05:15:00 GMT 2019");
    busTicketInfo.put("arrival date", "Fri Dec 20 08:24:00 GMT 2019");
    busTicketInfo.put("departure location", "BOS");
    busTicketInfo.put("arrival location", "ATL");
    busTicketInfo.put("class", "null");

    planeTicketInfo.put("type", "Plane");
    planeTicketInfo.put("passenger", "Mary Grace");
    planeTicketInfo.put("route", "ID2314");
    planeTicketInfo.put("departure date", "Thu Dec 19 05:15:00 GMT 2019");
    planeTicketInfo.put("arrival date", "Fri Dec 20 08:24:00 GMT 2019");
    planeTicketInfo.put("departure location", "BOS");
    planeTicketInfo.put("arrival location", "ATL");
    planeTicketInfo.put("class", "ECONOMY");

    trainTicketInfo.put("type", "Train");
    trainTicketInfo.put("passenger", "Mary Grace");
    trainTicketInfo.put("route", "ID2314");
    trainTicketInfo.put("departure date", "Thu Dec 19 05:15:00 GMT 2019");
    trainTicketInfo.put("arrival date", "Fri Dec 20 08:24:00 GMT 2019");
    trainTicketInfo.put("departure location", "BOS");
    trainTicketInfo.put("arrival location", "ATL");
    trainTicketInfo.put("class", "FIRST_CLASS");

    ITicket ticket1 = TicketFactory.create(busTicketInfo);
    ITicket ticket2 = TicketFactory.create(planeTicketInfo);
    ITicket ticket3 = TicketFactory.create(trainTicketInfo);

    assertEquals("Mary Grace", ticket1.getPassengerName());
    assertEquals("ID2314", ticket1.getRouteID());
    assertEquals("Fri Dec 20 08:24:00 " + ticket1.getArrivalDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket1.getArrivalDateAndTime()
            .getTime()
            .toString());
    assertEquals("Thu Dec 19 05:15:00 " + ticket1.getDepartureDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket1.getDepartureDateAndTime()
            .getTime()
            .toString());
    assertEquals("Boston, Massachusetts", ticket1.getDepartureLocation());
    assertEquals("Atlanta, Georgia", ticket1.getArrivalLocation());
    assertNull(ticket1.getClassOfService());

    assertEquals("Mary Grace", ticket2.getPassengerName());
    assertEquals("ID2314", ticket2.getRouteID());
    assertEquals("Fri Dec 20 08:24:00 " + ticket2.getArrivalDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket2.getArrivalDateAndTime()
            .getTime()
            .toString());
    assertEquals("Thu Dec 19 05:15:00 " + ticket2.getDepartureDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket2.getDepartureDateAndTime()
            .getTime()
            .toString());
    assertEquals("Boston, Massachusetts", ticket2.getDepartureLocation());
    assertEquals("Atlanta, Georgia", ticket2.getArrivalLocation());
    assertEquals("ECONOMY", ticket2.getClassOfService().toString());

    assertEquals("Mary Grace", ticket3.getPassengerName());
    assertEquals("ID2314", ticket3.getRouteID());
    assertEquals("Fri Dec 20 08:24:00 " + ticket3.getArrivalDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket3.getArrivalDateAndTime()
            .getTime()
            .toString());
    assertEquals("Thu Dec 19 05:15:00 " + ticket3.getDepartureDateAndTime()
            .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019", ticket3.getDepartureDateAndTime()
            .getTime()
            .toString());
    assertEquals("Boston, Massachusetts", ticket3.getDepartureLocation());
    assertEquals("Atlanta, Georgia", ticket3.getArrivalLocation());
    assertEquals("FIRST_CLASS", ticket3.getClassOfService().toString());
  }
}