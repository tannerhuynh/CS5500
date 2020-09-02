package util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class that turns a string into a Calendar object.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class CalFormat {

  /**
   * Cannot instantiate a utility class.
   */
  private CalFormat() {
    // Nothing
  }

  /**
   * Map string into a format that is readable by Calendar Util. Expected String format
   * is Sat Dec 21 15:18:00 EST 2019 (Alpha Alpha Numeric Numeric Alpha Numeric).
   *
   * @param date the current date from the JSON object.
   * @return Calendar format of the given date.
   */
  public static Calendar map(String date) {
    String[] dateArray = date.split(" ");

    if(dateArray.length != 6) {
      throw new IllegalArgumentException("There needs to be exactly six values in the order " +
              "DAYOFWEEK(THU) MONTH(DEC) DAY(21) HH:MM:SS TIMEZONE(EST) YYYY(2019).");
    }

    String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Nov",
            "Dec"};
    String monthName = dateArray[1];
    int month = 0;

    for (int i = 0; i < monthArray.length; i++) {
      if (monthName.equals(monthArray[i])) {
        month = i + 1;
      }
    }
    String[] timeArray = dateArray[3].split(":");
    int year = Integer.parseInt(dateArray[5]);
    int day = Integer.parseInt(dateArray[2]);
    int hour = Integer.parseInt(timeArray[0]);
    int minute = Integer.parseInt(timeArray[1]);
    int second = Integer.parseInt(timeArray[2]);

    return new GregorianCalendar(year, month, day, hour, minute, second);
  }
}
