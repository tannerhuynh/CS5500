package singletontest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import singleton.PlaneTicketSingleton;
import singleton.ClassOfService;
import singleton.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PlaneTicketSingletonTest {
  // Travel Calendar
  private Calendar departure1 = new GregorianCalendar(2019,Calendar.DECEMBER,19,
          5, 15);

  private Calendar arrival1 = new GregorianCalendar(2019,Calendar.DECEMBER,21,
          15, 18);

  // Tickets
  private PlaneTicketSingleton planeTicket1 = PlaneTicketSingleton.createSingleton(
          "John Smith", "PL487234",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, ClassOfService.ECONOMY);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createSingleton() {
    PlaneTicketSingleton planeTicket2 = PlaneTicketSingleton.createSingleton(
            "John Smith", "PL487234",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, ClassOfService.ECONOMY);
    PlaneTicketSingleton planeTicket3 = PlaneTicketSingleton.createSingleton(
            "Grace Smith", "PL487234",
            departure1, arrival1, Locations.LAX,
            Locations.BOS, ClassOfService.FIRST_CLASS);

    assertNull(planeTicket2);

    assertNotNull(planeTicket3);
    assertEquals("Grace Smith", planeTicket3.getPassengerName());
    assertEquals("PL487234", planeTicket3.getRouteID());
    assertEquals("Thu Dec 19 05:15:00 " + planeTicket3.getDepartureDateAndTime()
                    .getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019",
            planeTicket3.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + planeTicket3.getArrivalDateAndTime()
                    .getTimeZone()
            .getDisplayName(false, TimeZone.SHORT) + " 2019",
            planeTicket3.getArrivalDateAndTime().getTime().toString());
    assertEquals("Los Angeles, California", planeTicket3.getDepartureLocation());
    assertEquals("Boston, Massachusetts", planeTicket3.getArrivalLocation());
    assertEquals("FIRST_CLASS", planeTicket3.getClassOfService().toString());
  }

  @Test
  public void getClassOfService() {
    PlaneTicketSingleton planeticket4 = PlaneTicketSingleton.createSingleton(
            "John Smith", "ID43824",
            departure1, arrival1, Locations.LOS,
            Locations.BOS, ClassOfService.ECONOMY);
    assertNotNull(planeticket4);
    assertEquals(ClassOfService.ECONOMY, planeticket4.getClassOfService());
  }

  @Test
  public void setClassOfService() {
    planeTicket1.setClassOfService(ClassOfService.FIRST_CLASS);
    assertEquals(ClassOfService.FIRST_CLASS, planeTicket1.getClassOfService());
    assertEquals("FIRST_CLASS", planeTicket1.getClassOfService().toString());
  }
}