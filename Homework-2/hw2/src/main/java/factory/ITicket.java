package factory;

import java.util.Calendar;

/**
 * A set of methods for creating, setting, and getting metadata on a ticket object. Ticket
 * objects can contain the following:
 *
 * Passenger Name: String name of the person that purchased the ticket.
 * Route ID: String unique identification for the specific transportation method and route.
 * Departure and Arrival City: Locations enum for the origin and destination.
 * Departure and Arrival State: Locations enum for the origin and destination.
 * Departure Date and Time: Military time and day of departure. This can be translated to standard
 * time for user local time and day.
 * Arrival Date and Time: Military time and day of arrival. This can be translated to standard
 * time for user local time and day.
 * Class of Service: An enum representing the user's boarding class if transportation method
 * allows for this distinction.
 *
 * This interface uses the utility Calendar to set trip dates. ClassOfService enums are used to set
 * boarding class type for transportation methods that allow for this distinction. The enum
 * Locations is used to set locations of city and state.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public interface ITicket {

  /**
   * Getter for the name of the ticket's passenger.
   *
   * @return passenger name in string format.
   */
  String getPassengerName();

  /**
   * Setter for the name of the ticket's passenger.
   *
   * @param passengerName passenger name in string format.
   */
  void setPassengerName(String passengerName);

  /**
   * Getter for the unique identification pertaining to the ticket's route.
   *
   * @return unique identification number related to the ticket in string format.
   */
  String getRouteID();

  /**
   * Setter for the unique identification pertaining to the ticket's route.
   *
   * @param routeID unique identification number related to the ticket in string format.
   */
  void setRoute(String routeID);

  /**
   * Getter for the departure city string.
   *
   * @return the departure city in string format.
   */
  String getDepartureLocation();

  /**
   * Getter for the arrival city string.
   *
   * @return the arrival city in string format.
   */
  String getArrivalLocation();

  /**
   * Setter for the ticket origin in using Locations enum.
   *
   * @param origination enum representing location.
   */
  void setOrigination(Locations origination);

  /**
   * Setter for the ticket destination using Locations enum.
   *
   * @param destination enum representing location.
   */
  void setDestination(Locations destination);

  /**
   * Getter for the departure date and time which is represented by util Calendar but printed out
   * in string format MM DD YYYY HH MM.
   *
   * @return the departure date and time in string format MM DD YYYY HH MM.
   */
  Calendar getDepartureDateAndTime();

  /**
   * Getter for the arrival date and time which is represented by util Calendar but printed out
   * in string format YYYY MM DD HH MM.
   *
   * @return the arrival date and time in string format YYYY MM DD HH MM.
   */
  Calendar getArrivalDateAndTime();

  /**
   * Setter for the trip dates using util Calendar for both departure and arrival in the format
   * YYYY Calendar.MONTH DD HH MM.
   *
   * @param departureDate Calendar departure date in the format YYYY Calendar.MONTH DD HH MM.
   * @param arrivalDate Calendar arrival date in the format YYYY Calendar.MONTH DD HH MM.
   */
  void setTripDates(Calendar departureDate, Calendar arrivalDate);

  /**
   * Getter for the class of service for certain tickets which allow class distinctions defined
   * in the enum ClassOfService.
   *
   * @return enum representing the class of service available on certain tickets.
   */
  ClassOfService getClassOfService();

  /**
   * Setter for the class of service for certain tickets which allow class distinctions defined
   * in the enum ClassOfService.
   *
   * @param serviceClass enum representing the class of service available on certain tickets.
   */
  void setClassOfService(ClassOfService serviceClass);
 }