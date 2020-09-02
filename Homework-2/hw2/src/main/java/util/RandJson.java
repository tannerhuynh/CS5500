package util;

import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ticket.ClassOfService;
import ticket.Locations;

/**
 * Utility function to generate a random JSON object for a ticket.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class RandJson {

  /**
   * Cannot instantiate a utility class.
   */
  private RandJson() {
    // Nothing
  }

  public static JSONObject create() {
    JSONObject object = new JSONObject();

    List<String> listNames = new ArrayList<>();
    listNames.add("John Smith");
    listNames.add("Babe Ruth");
    listNames.add("Mary Grace");
    listNames.add("Bob Barker");
    listNames.add("Jet Lee");
    listNames.add("Joanne Putnam");
    listNames.add("Roseanne Calligra");
    listNames.add("Tanner Huynh");
    listNames.add("Dave Campbell");
    listNames.add("Josh Toy");
    listNames.add("Carry Underwood");
    listNames.add("RuPaul Harrington");

    List<String> listTransportType = new ArrayList<>();
    listTransportType.add("Bus");
    listTransportType.add("Plane");
    listTransportType.add("Train");

    String routeID = "ID" + (new SecureRandom().nextInt() * 1000);
    object.put("type", getRandomListElement(listTransportType));

    if(object.get("type").equals("Bus")) {
      object.put("class", "null");
    } else {
      object.put("class", ClassOfService.getRandom().toString());
    }

    object.put("passenger", getRandomListElement(listNames));
    object.put("route", routeID);
    object.put("departure date", genRandomCalendar().getTime().toString());
    object.put("arrival date", genRandomCalendar().getTime().toString());
    object.put("departure location", Locations.getRandom().toString());

    String arrival = "arrival location";
    object.put(arrival, "");

    while(object.get(arrival) == "") {
      String generatedLocation = Locations.getRandom().toString();
      if(generatedLocation != object.get("departure location")) {
        object.put(arrival, generatedLocation);
      }
    }

    return object;
  }

  /**
   * Generate a random Calender object using set values.
   *
   * @return Calendar object.
   */
  private static Calendar genRandomCalendar() {
    return new GregorianCalendar(randBetween(2019, 2020), randBetween(1, 12),
            randBetween(1, 30), randBetween(1, 24), randBetween(0, 60));
  }

  /**
   * Selects a random element from a list.
   *
   * @param list list of strings.
   * @return string element within the list.
   */
  private static String getRandomListElement(List<String> list)
  {
    return list.get(new SecureRandom().nextInt(list.size()));
  }

  /**
   * Select a random number between a start and end value.
   *
   * @param start initial int value.
   * @param end last int value.
   * @return random int value.
   */
  private static int randBetween(int start, int end) {
    return start + (new SecureRandom().nextInt() * (end - start));
  }
}
