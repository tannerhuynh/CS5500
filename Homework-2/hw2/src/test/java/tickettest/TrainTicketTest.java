package tickettest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import ticket.ClassOfService;
import ticket.Locations;
import ticket.TrainTicket;

import static org.junit.Assert.assertEquals;

public class TrainTicketTest {

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
  private TrainTicket trainTicket1 = new TrainTicket("John Smith", "PL487234",
          departure1, arrival1, Locations.BOS,
          Locations.ATL, ClassOfService.COACH);
  private TrainTicket trainTicket2 = new TrainTicket("Becca Johnson", "PL234148",
          departure2, arrival2, Locations.LAX,
          Locations.SFO, ClassOfService.BUSINESS);
  private TrainTicket trainTicket3 = new TrainTicket("Mark Kerring", "PL43721",
          departure3, arrival3, Locations.LAS,
          Locations.IAH, ClassOfService.SLEEPER_CABIN);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getClassOfService() {
    assertEquals(ClassOfService.COACH, trainTicket1.getClassOfService());
    assertEquals(ClassOfService.BUSINESS, trainTicket2.getClassOfService());
    assertEquals(ClassOfService.SLEEPER_CABIN, trainTicket3.getClassOfService());
    assertEquals("COACH", trainTicket1.getClassOfService().toString());
    assertEquals("BUSINESS", trainTicket2.getClassOfService().toString());
    assertEquals("SLEEPER_CABIN", trainTicket3.getClassOfService().toString());
  }

  @Test
  public void setClassOfService() {
    trainTicket1.setClassOfService(ClassOfService.SLEEPER_CABIN);
    trainTicket2.setClassOfService(ClassOfService.FIRST_CLASS);
    trainTicket3.setClassOfService(ClassOfService.BUSINESS);
    assertEquals(ClassOfService.SLEEPER_CABIN, trainTicket1.getClassOfService());
    assertEquals(ClassOfService.FIRST_CLASS, trainTicket2.getClassOfService());
    assertEquals(ClassOfService.BUSINESS, trainTicket3.getClassOfService());
    assertEquals("SLEEPER_CABIN", trainTicket1.getClassOfService().toString());
    assertEquals("FIRST_CLASS", trainTicket2.getClassOfService().toString());
    assertEquals("BUSINESS", trainTicket3.getClassOfService().toString());
  }

  @Test
  public void createTrainTicket() {
    factory.TrainTicket trainTicket5 = new factory.TrainTicket("Dave Campbell", "ID23673",
            departure1, arrival1, factory.Locations.BOS,
            factory.Locations.ATL, factory.ClassOfService.FIRST_CLASS);
    factory.TrainTicket trainTicket6 = new factory.TrainTicket("Mary Mae", "ID1278",
            departure2, arrival2, factory.Locations.LAX,
            factory.Locations.SFO, factory.ClassOfService.BUSINESS);

    assertEquals("Dave Campbell", trainTicket5.getPassengerName());
    assertEquals("ID23673", trainTicket5.getRouteID());
    assertEquals("Boston, Massachusetts",
            trainTicket5.getDepartureLocation());
    assertEquals("Atlanta, Georgia",
            trainTicket5.getArrivalLocation());
    assertEquals("Thu Dec 19 05:15:00 " + trainTicket5.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false,
                    TimeZone.SHORT) + " 2019",
            trainTicket5.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Dec 21 15:18:00 " + trainTicket5.getArrivalDateAndTime()
                    .getTimeZone().getDisplayName(false,
                    TimeZone.SHORT) + " 2019",
            trainTicket5.getArrivalDateAndTime().getTime().toString());
    assertEquals(factory.ClassOfService.FIRST_CLASS, trainTicket5.getClassOfService());

    assertEquals("Mary Mae", trainTicket6.getPassengerName());
    assertEquals("ID1278", trainTicket6.getRouteID());
    assertEquals("Los Angeles, California",
            trainTicket6.getDepartureLocation());
    assertEquals("San Francisco, California",
            trainTicket6.getArrivalLocation());
    assertEquals("Sun Nov 10 10:10:00 " + trainTicket6.getDepartureDateAndTime()
                    .getTimeZone().getDisplayName(false,
                    TimeZone.SHORT) + " 2019",
            trainTicket6.getDepartureDateAndTime().getTime().toString());
    assertEquals("Sat Nov 16 20:15:00 " + trainTicket6.getArrivalDateAndTime()
                    .getTimeZone().getDisplayName(false,
                    TimeZone.SHORT) + " 2019",
            trainTicket6.getArrivalDateAndTime().getTime().toString());
    assertEquals(factory.ClassOfService.BUSINESS, trainTicket6.getClassOfService());
  }
}