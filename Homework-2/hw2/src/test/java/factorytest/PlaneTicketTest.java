package factorytest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import factory.ClassOfService;
import factory.Locations;
import factory.PlaneTicket;

import static org.junit.Assert.assertEquals;

public class PlaneTicketTest {

  // Travel Calendar
  private Calendar departure1 = new GregorianCalendar(2019,Calendar.DECEMBER,19,
          5, 15);
  private Calendar departure2 = new GregorianCalendar(2019,Calendar.NOVEMBER,10,
          10, 10);
  private Calendar departure3 = new GregorianCalendar(2020,Calendar.MAY,16,
          18, 30);

  private Calendar arrival1 = new GregorianCalendar(2019,Calendar.DECEMBER,21,
          15, 18);
  private Calendar arrival2 = new GregorianCalendar(2019,Calendar.NOVEMBER,16,
          20, 15);
  private Calendar arrival3 = new GregorianCalendar(2020,Calendar.MAY,20,
          9, 27);

  // Tickets
  private PlaneTicket planeTicket1 = new PlaneTicket("John Smith", "PL487234",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, ClassOfService.ECONOMY);
  private PlaneTicket planeTicket2 = new PlaneTicket("Becca Johnson", "PL234148",
          departure2, arrival2, Locations.LAX,
          Locations.SFO, ClassOfService.PREMIUM_ECONOMY);
  private PlaneTicket planeTicket3 = new PlaneTicket("Mark Kerring", "PL43721",
          departure3, arrival3, Locations.LAS,
          Locations.IAH, ClassOfService.BASIC_ECONOMY);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getClassOfService() {
    assertEquals(ClassOfService.ECONOMY, planeTicket1.getClassOfService());
    assertEquals(ClassOfService.PREMIUM_ECONOMY, planeTicket2.getClassOfService());
    assertEquals(ClassOfService.BASIC_ECONOMY, planeTicket3.getClassOfService());
    assertEquals("ECONOMY", planeTicket1.getClassOfService().toString());
    assertEquals("PREMIUM_ECONOMY", planeTicket2.getClassOfService().toString());
    assertEquals("BASIC_ECONOMY", planeTicket3.getClassOfService().toString());
  }

  @Test
  public void setClassOfService() {
    planeTicket1.setClassOfService(ClassOfService.FIRST_CLASS);
    planeTicket2.setClassOfService(ClassOfService.BUSINESS);
    planeTicket3.setClassOfService(ClassOfService.ECONOMY);
    assertEquals(ClassOfService.FIRST_CLASS, planeTicket1.getClassOfService());
    assertEquals(ClassOfService.BUSINESS, planeTicket2.getClassOfService());
    assertEquals(ClassOfService.ECONOMY, planeTicket3.getClassOfService());
    assertEquals("FIRST_CLASS", planeTicket1.getClassOfService().toString());
    assertEquals("BUSINESS", planeTicket2.getClassOfService().toString());
    assertEquals("ECONOMY", planeTicket3.getClassOfService().toString());
  }

  @Test
  public void createPlaneTicket() {
    PlaneTicket planeTicket5 = new PlaneTicket("Dave Campbell", "ID23673",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, ClassOfService.FIRST_CLASS);
    PlaneTicket planeTicket6 = new PlaneTicket("Mary Mae", "ID1278",
            departure2, arrival2, Locations.LAX,
            Locations.SFO, ClassOfService.BUSINESS);

    assertEquals("Dave Campbell", planeTicket5.getPassengerName());
    assertEquals("ID23673", planeTicket5.getRouteID());
    assertEquals("Boston, Massachusetts",
            planeTicket5.getDepartureLocation());
    assertEquals("Atlanta, Georgia",
            planeTicket5.getArrivalLocation());
    assertEquals("Thu Dec 19 05:15:00 " + planeTicket5.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) +  " 2019",
            planeTicket5.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + planeTicket5.getArrivalDateAndTime()
                    .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019",
            planeTicket5.getArrivalDateAndTime().getTime().toString());
    assertEquals(ClassOfService.FIRST_CLASS, planeTicket5.getClassOfService());

    assertEquals("Mary Mae", planeTicket6.getPassengerName());
    assertEquals("ID1278", planeTicket6.getRouteID());
    assertEquals("Los Angeles, California",
            planeTicket6.getDepartureLocation());
    assertEquals("San Francisco, California",
            planeTicket6.getArrivalLocation());
    assertEquals("Sun Nov 10 10:10:00 " + planeTicket6.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019",
            planeTicket6.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Nov 16 20:15:00 " + planeTicket6.getArrivalDateAndTime()
                    .getTimeZone().getDisplayName(false,
            TimeZone.SHORT) + " 2019",
            planeTicket6.getArrivalDateAndTime().getTime().toString());
    assertEquals(ClassOfService.BUSINESS, planeTicket6.getClassOfService());
  }
}