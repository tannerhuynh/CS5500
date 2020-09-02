package factory;

import java.util.Calendar;

/**
 * Class for creating train tickets.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class TrainTicket extends AbstractTicket {

  /**
   * Constructor for the train ticket.
   *
   * @param passenger passenger name in string format.
   * @param routeID unique identification for the route in string format.
   * @param departureDateTime Calendar object representing the date and time of departure.
   * @param arrivalDateTime Calendar object representing the date and time of arrival.
   * @param departureLocation enum representing the departure location.
   * @param arrivalLocation enum representing the arrival location.
   * @param classOfService enum representing the class of service for the passenger.
   */
  public TrainTicket(String passenger,
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
    return this.classOfService;
  }

  @Override
  public void setClassOfService(ClassOfService serviceType) {
    this.classOfService = serviceType;
  }
}
