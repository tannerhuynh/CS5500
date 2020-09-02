package factory;

import java.util.Calendar;

/**
 * Abstract class representing methods shared across all ticket types. The following methods are
 * required by default when generating tickets for users.
 *
 * Passenger Name
 * Route ID
 * Departure Date and Time
 * Arrival Date and Time
 * Departure Location
 * Arrival Location
 * Class of Service
 *
 * This abstraction uses the same Calendar util from the interface.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public abstract class AbstractTicket implements ITicket {
  protected String passenger;
  protected String routeID;
  protected Calendar departureDateTime;
  protected Calendar arrivalDateTime;
  protected Locations departureLocation;
  protected Locations arrivalLocation;
  protected ClassOfService classOfService;

  /**
   * Constructor for the abstraction class of tickets.
   *
   * @param passenger passenger name in string format.
   * @param routeID unique identification for the route in string format.
   * @param departureDateTime Calendar object representing the date and time of departure.
   * @param arrivalDateTime Calendar object representing the date and time of arrival.
   * @param departureLocation enum representing the departure location.
   * @param arrivalLocation enum representing the arrival location.
   * @param classOfService the class of ticket depending on th transportation type.
   */
  public AbstractTicket(String passenger,
                        String routeID,
                        Calendar departureDateTime,
                        Calendar arrivalDateTime,
                        Locations departureLocation,
                        Locations arrivalLocation,
                        ClassOfService classOfService) {
    this.passenger = passenger;
    this.routeID = routeID;
    this.departureDateTime = departureDateTime;
    this.arrivalDateTime = arrivalDateTime;
    this.departureLocation = departureLocation;
    this.arrivalLocation = arrivalLocation;
    this.classOfService = classOfService;
  }

  @Override
  public String getPassengerName() {
    return passenger;
  }

  @Override
  public void setPassengerName(String passengerName) {
    this.passenger = passengerName;
  }

  @Override
  public String getRouteID() {
    return routeID;
  }

  @Override
  public void setRoute(String routeID) {
    this.routeID = routeID;
  }

  @Override
  public String getDepartureLocation() {
    return departureLocation.getName();
  }

  @Override
  public String getArrivalLocation() {
    return arrivalLocation.getName();
  }

  @Override
  public void setOrigination(Locations origination) {
    this.departureLocation = origination;
  }

  @Override
  public void setDestination(Locations destination) {
    this.arrivalLocation = destination;
  }

  @Override
  public Calendar getDepartureDateAndTime() {
    return departureDateTime;
  }

  @Override
  public Calendar getArrivalDateAndTime() {
    return arrivalDateTime;
  }

  @Override
  public void setTripDates(Calendar departureDate, Calendar arrivalDate) {
    this.departureDateTime = departureDate;
    this.arrivalDateTime = arrivalDate;
  }
}
