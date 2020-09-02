package factory;

import org.json.JSONObject;

import java.util.Calendar;

import logger.Counter;
import util.CalFormat;

/**
 * A ticket factory that creates tickets based on input from a JSON object containing data about
 * a passenger's ticket. The following format is in the JSON Object:
 *
 * Transportation Type (type): Bus, Train, or Plane.
 * Passenger Name (passenger): First and last name of passenger.
 * Unique Route Identification String (route): ID for the specific route.
 * Departure Date and Time (departure date): Calendar Util in Gregorian format.
 * Arrival Date and Time (arrival date): Calendar Util in Gregorian format.
 * Departure Location (departure location): Enum for locations using 3 letter codes (ie LAX).
 * Arrival Location (arrival location): Enum for locations using 3 letter codes (ie LAX).
 * Class of Service (class): Enum for class of travel service (ie ECONOMY, BUSINESS, FIRST_CLASS).
 *
 * This class utilizes location and service enums and a locally packaged Find util to match
 * JSON object records with existing enums. This class also utilizes a Calendar util from Oracle.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class TicketFactory {

  /**
   * Illegal constructor for this class.
   */
  private TicketFactory() {
    // Nothing
  }

  public static ITicket create(JSONObject ticketInfo) {
    String ticketType = ticketInfo.getString("type").toUpperCase();
    String passenger = ticketInfo.getString("passenger");
    String routeID = ticketInfo.getString("route").toUpperCase();
    Calendar departDate = CalFormat.map(ticketInfo.getString("departure date"));
    Calendar arrivalDate = CalFormat.map(ticketInfo.getString("arrival date"));
    Locations departure =
            Locations.find(ticketInfo.getString("departure location").toUpperCase());
    Locations arrival =
            Locations.find(ticketInfo.getString("arrival location").toUpperCase());
    ClassOfService classType = ClassOfService.find(ticketInfo.getString("class").toUpperCase());

    switch (ticketType) {
      case "BUS":
        Counter.busCounter();
        return new BusTicket(passenger, routeID, departDate, arrivalDate, departure,
                arrival,
                null);
      case "TRAIN":
        Counter.trainCounter();
        return new TrainTicket(passenger, routeID, departDate, arrivalDate, departure,
                arrival,
                classType);
      case "PLANE":
        Counter.planeCounter();
        return new PlaneTicket(passenger, routeID, departDate, arrivalDate, departure,
                arrival,
                classType);
      default:
        throw new IllegalArgumentException("Not a valid method of transportation.");
    }
  }
}
