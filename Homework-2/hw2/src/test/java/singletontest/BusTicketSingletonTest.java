package singletontest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import singleton.BusTicketSingleton;
import singleton.ClassOfService;
import singleton.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BusTicketSingletonTest {
  // Travel Calendar
  private Calendar departure1 = new GregorianCalendar(2019,Calendar.DECEMBER,19,
          5, 15);

  private Calendar arrival1 = new GregorianCalendar(2019,Calendar.DECEMBER,21,
          15, 18);

  // Tickets
  private BusTicketSingleton busTicket1 = BusTicketSingleton.createSingleton("John Smith",
          "ID43824",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, null);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createSingleton() {
    BusTicketSingleton busTicket2 = BusTicketSingleton.createSingleton(
            "John Smith", "ID43824",
            departure1, arrival1, Locations.IAH,
            Locations.ATL, null);
    BusTicketSingleton busTicket3 = BusTicketSingleton.createSingleton(
            "Grace Smith", "PL487234",
            departure1, arrival1, Locations.LAX,
            Locations.BOS, null);
    BusTicketSingleton busTicket1 = BusTicketSingleton.createSingleton("John Smith",
            "ID43824",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, null);

    assertNotNull(busTicket2);
    assertEquals("John Smith", busTicket2.getPassengerName());
    assertEquals("ID43824", busTicket2.getRouteID());
    assertEquals("Thu Dec 19 05:15:00 " +  busTicket2.getDepartureDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019",
            busTicket2.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + busTicket2.getArrivalDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019", busTicket2.getArrivalDateAndTime().getTime().toString());
    assertEquals("Houston, Texas", busTicket2.getDepartureLocation());
    assertEquals("Atlanta, Georgia", busTicket2.getArrivalLocation());
    assertNull(busTicket2.getClassOfService());

    assertNotNull(busTicket3);
    assertEquals("Grace Smith", busTicket3.getPassengerName());
    assertEquals("PL487234", busTicket3.getRouteID());
    assertEquals("Thu Dec 19 05:15:00 " + busTicket3.getDepartureDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019",
            busTicket3.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + busTicket3.getArrivalDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019",
            busTicket3.getArrivalDateAndTime().getTime().toString());
    assertEquals("Los Angeles, California", busTicket3.getDepartureLocation());
    assertEquals("Boston, Massachusetts", busTicket3.getArrivalLocation());
    assertNull(busTicket2.getClassOfService());

    assertNull(busTicket1);
  }

  @Test
  public void getClassOfService() {
    BusTicketSingleton busTicket4 = BusTicketSingleton.createSingleton(
            "John Smith", "ID43824",
            departure1, arrival1, Locations.LOS,
            Locations.BOS, null);
    assertNotNull(busTicket4);
    assertNull(busTicket4.getClassOfService());
  }

  @Test
  public void setClassOfService() {
    busTicket1.setClassOfService(null);
    assertNull(busTicket1.getClassOfService());
    busTicket1.setClassOfService(ClassOfService.FIRST_CLASS);
    assertNull(busTicket1.getClassOfService());
    busTicket1.setClassOfService(ClassOfService.BUSINESS);
    assertNull(busTicket1.getClassOfService());
  }
}