package singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import util.Pair;

/**
 * Class for creating singleton plane tickets to a specific location.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class PlaneTicketSingleton extends AbstractTicket {
  private static List<Pair<Locations,Locations>> tripsCompleted = new ArrayList<>();

  /**
   * Constructor for the plane ticket.
   *
   * @param passenger passenger name in string format.
   * @param routeID unique identification for the route in string format.
   * @param departureDateTime Calendar object representing the date and time of departure.
   * @param arrivalDateTime Calendar object representing the date and time of arrival.
   * @param departureLocation enum representing the departure location.
   * @param arrivalLocation enum representing the arrival location.
   * @param classOfService enum representing the class of service for the passenger.
   */
  private PlaneTicketSingleton(String passenger,
                              String routeID,
                              Calendar departureDateTime,
                              Calendar arrivalDateTime,
                              Locations departureLocation,
                              Locations arrivalLocation,
                              ClassOfService classOfService) {
    super(passenger, routeID, departureDateTime, arrivalDateTime, departureLocation,
            arrivalLocation, classOfService);
  }

  /**
   * Creates a single instance of a plane ticket specific location.
   *
   * @param passenger passenger name in string format.
   * @param routeID unique identification for the route in string format.
   * @param departureDateTime Calendar object representing the date and time of departure.
   * @param arrivalDateTime Calendar object representing the date and time of arrival.
   * @param departureLocation Locations enum representing the departure city and state.
   * @param arrivalLocation Locations enum representing the arrival city and state.
   * @param classOfService enum representing the class of service for the passenger.
   *
   * @return a singleton bus ticket object.
   */
  public static PlaneTicketSingleton createSingleton(String passenger,
                                                         String routeID,
                                                         Calendar departureDateTime,
                                                         Calendar arrivalDateTime,
                                                         Locations departureLocation,
                                                         Locations arrivalLocation,
                                                         ClassOfService classOfService) {
    Pair<Locations, Locations> travelPlan = new Pair<>(departureLocation, arrivalLocation);

    if (!tripsCompleted.isEmpty()) {
      for (Pair<Locations, Locations> route : tripsCompleted) {
        if (travelPlan.equals(route)) {
          return null;
        }
      }
      tripsCompleted.add(travelPlan);
      return new PlaneTicketSingleton(passenger,
              routeID,
              departureDateTime,
              arrivalDateTime,
              departureLocation,
              arrivalLocation,
              classOfService);
    } else {
      tripsCompleted.add(travelPlan);
      return new PlaneTicketSingleton(passenger,
              routeID,
              departureDateTime,
              arrivalDateTime,
              departureLocation,
              arrivalLocation,
              classOfService);
    }
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
