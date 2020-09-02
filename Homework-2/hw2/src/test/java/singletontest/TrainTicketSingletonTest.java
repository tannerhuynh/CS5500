package singletontest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import singleton.TrainTicketSingleton;
import singleton.ClassOfService;
import singleton.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TrainTicketSingletonTest {
  // Travel Calendar
  private Calendar departure1 = new GregorianCalendar(2019,Calendar.DECEMBER,19,
          5, 15);

  private Calendar arrival1 = new GregorianCalendar(2019,Calendar.DECEMBER,21,
          15, 18);

  // Tickets
  private TrainTicketSingleton trainTicket1 = TrainTicketSingleton.createSingleton(
          "John Smith", "PL487234",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, ClassOfService.COACH);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createSingleton() {
    TrainTicketSingleton trainTicket2 = TrainTicketSingleton.createSingleton(
            "John Smith", "PL487234",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, ClassOfService.COACH);
    TrainTicketSingleton trainTicket3 = TrainTicketSingleton.createSingleton(
            "Grace Smith", "PL487234",
            departure1, arrival1, Locations.LAX,
            Locations.BOS, ClassOfService.ECONOMY);

    assertNull(trainTicket2);

    assertNotNull(trainTicket3);
    assertEquals("Grace Smith", trainTicket3.getPassengerName());
    assertEquals("PL487234", trainTicket3.getRouteID());
    assertEquals("Thu Dec 19 05:15:00 " + trainTicket3.getDepartureDateAndTime()
            .getTimeZone().getDisplayName(false, TimeZone.SHORT) + " 2019",
            trainTicket3.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + trainTicket3.getArrivalDateAndTime()
            .getTimeZone().getDisplayName(false, TimeZone.SHORT) + " 2019",
            trainTicket3.getArrivalDateAndTime().getTime().toString());
    assertEquals("Los Angeles, California", trainTicket3.getDepartureLocation());
    assertEquals("Boston, Massachusetts", trainTicket3.getArrivalLocation());
    assertEquals("ECONOMY", trainTicket3.getClassOfService().toString());
  }

  @Test
  public void getClassOfService() {
    TrainTicketSingleton trainTicket4 = TrainTicketSingleton.createSingleton(
            "John Smith", "ID43824",
            departure1, arrival1, Locations.LOS,
            Locations.BOS, ClassOfService.ECONOMY);
    assertNotNull(trainTicket4);
    assertEquals(ClassOfService.ECONOMY, trainTicket4.getClassOfService());
  }

  @Test
  public void setClassOfService() {
    trainTicket1.setClassOfService(ClassOfService.SLEEPER_CABIN);
    assertEquals(ClassOfService.SLEEPER_CABIN, trainTicket1.getClassOfService());
    assertEquals("SLEEPER_CABIN", trainTicket1.getClassOfService().toString());
  }
}