package factorytest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import factory.BusTicket;
import factory.ClassOfService;
import factory.Locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BusTicketTest {

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
  private BusTicket busTicket1 = new BusTicket("John Smith", "ID43824",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, null);
  private BusTicket busTicket2 = new BusTicket("Becca Johnson", "ID43524",
          departure2, arrival2, Locations.LAX,
          Locations.SFO, null);
  private BusTicket busTicket3 = new BusTicket("Mark Kerring", "ID67643",
          departure3, arrival3, Locations.LAS,
          Locations.IAH, null);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void setPassengerName() {
    BusTicket busTicket1 = new BusTicket("John Smith", "ID43824",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, null);
    busTicket1.setPassengerName("Mary Grace");
    assertEquals("Mary Grace", busTicket1.getPassengerName());
  }

  @Test
  public void setRouteID() {
    BusTicket busTicket2 = new BusTicket("Becca Johnson", "ID43524",
            departure2, arrival2, Locations.LAX,
            Locations.SFO, null);
    busTicket2.setRoute("ID23167");
    assertEquals("ID23167", busTicket2.getRouteID());
  }

  @Test
  public void setOrigination() {
    BusTicket busTicket2 = new BusTicket("Becca Johnson", "ID43524",
            departure2, arrival2, Locations.LAX,
            Locations.SFO, null);
    busTicket2.setOrigination(Locations.BOS);
    assertEquals("Boston, Massachusetts", busTicket2.getDepartureLocation());
  }

  @Test
  public void setDestination() {
    BusTicket busTicket2 = new BusTicket("Becca Johnson", "ID43524",
            departure2, arrival2, Locations.LAX,
            Locations.SFO, null);
    busTicket2.setDestination(Locations.ATL);
    assertEquals("Atlanta, Georgia", busTicket2.getArrivalLocation());
  }

  @Test
  public void setTripDates() {
    BusTicket busTicket3 = new BusTicket("Mark Kerring", "ID67643",
            departure3, arrival3, Locations.LAS,
            Locations.IAH, null);
    busTicket3.setTripDates(departure1, arrival1);

    assertEquals("Sat Dec 21 15:18:00 " + busTicket3.getArrivalDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT)
                    + " 2019", busTicket3.getArrivalDateAndTime().getTime().toString());
    assertEquals("Thu Dec 19 05:15:00 " + busTicket3.getDepartureDateAndTime().getTimeZone()
            .getDisplayName(false, TimeZone.SHORT)
                    + " 2019", busTicket3.getDepartureDateAndTime().getTime().toString());
  }

  @Test
  public void getClassOfService() {
    assertNull(busTicket1.getClassOfService());
    assertNull(busTicket2.getClassOfService());
    assertNull(busTicket3.getClassOfService());
  }

  @Test
  public void setClassOfService() {
    busTicket1.setClassOfService(null);
    busTicket2.setClassOfService(ClassOfService.FIRST_CLASS);
    busTicket3.setClassOfService(ClassOfService.BUSINESS);
    assertNull(busTicket1.getClassOfService());
    assertNull(busTicket2.getClassOfService());
    assertNull(busTicket3.getClassOfService());
  }

  @Test
  public void createBusTicket() {
    BusTicket busTicket5 = new BusTicket("Dave Campbell", "ID23673",
            departure1, arrival1, Locations.BOS,
            Locations.ATL, null);
    BusTicket busTicket6 = new BusTicket("Mary Mae", "ID1278",
            departure2, arrival2, Locations.LAX,
            Locations.SFO, null);

    assertEquals("Dave Campbell", busTicket5.getPassengerName());
    assertEquals("ID23673", busTicket5.getRouteID());
    assertEquals("Boston, Massachusetts",
            busTicket5.getDepartureLocation());
    assertEquals("Atlanta, Georgia",
            busTicket5.getArrivalLocation());
    assertEquals("Thu Dec 19 05:15:00 " + busTicket5.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false, TimeZone.SHORT)  +  " 2019",
            busTicket5.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + busTicket5.getArrivalDateAndTime().getTimeZone()
                    .getDisplayName(false, TimeZone.SHORT)  + " 2019",
            busTicket5.getArrivalDateAndTime().getTime().toString());

    assertEquals("Mary Mae", busTicket6.getPassengerName());
    assertEquals("ID1278", busTicket6.getRouteID());
    assertEquals("Los Angeles, California",
            busTicket6.getDepartureLocation());
    assertEquals("San Francisco, California",
            busTicket6.getArrivalLocation());
    assertEquals("Sun Nov 10 10:10:00 " + busTicket6.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false, TimeZone.SHORT)  +  " 2019",
            busTicket6.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Nov 16 20:15:00 " + busTicket6.getArrivalDateAndTime().getTimeZone()
                    .getDisplayName(false, TimeZone.SHORT)  +  " 2019",
            busTicket6.getArrivalDateAndTime().getTime().toString());
  }
}