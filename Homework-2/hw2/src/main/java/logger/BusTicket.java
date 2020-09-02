package logger;

import java.util.Calendar;

/**
 * Class for creating bus tickets. Bus tickets do not have a class of service and
 * therefore return null for those methods.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
  public class BusTicket extends AbstractTicket {

  /**
   * Constructor for the bus ticket.
   *
   * @param passenger passenger name in string format.
   * @param routeID unique identification for the route in string format.
   * @param departureDateTime Calendar object representing the date and time of departure.
   * @param arrivalDateTime Calendar object representing the date and time of arrival.
   * @param departureLocation enum representing the departure location.
   * @param arrivalLocation enum representing the arrival location.
   * @param classOfService is always null.
   */
  public BusTicket(String passenger,
                    String routeID,
                    Calendar departureDateTime,
                    Calendar arrivalDateTime,
                    Locations departureLocation,
                    Locations arrivalLocation,
                    ClassOfService classOfService) {
    super(passenger, routeID, departureDateTime, arrivalDateTime, departureLocation,
            arrivalLocation, classOfService);
  }

  @Override
  public ClassOfService getClassOfService() {
    return null;
  }

  @Override
  public void setClassOfService(ClassOfService serviceClass) {
    // Nothing
  }
}
