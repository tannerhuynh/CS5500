package observer;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Date;

/**
 * Class for an observer that implements the Observer interface.
 */
public class ObserverB implements IObserver {
  private static Logger log = Logger.getRootLogger();

  @Override
  public void update(String state) {
    String pattern = "Hello";
    SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss z MM/dd/yyyy");
    Date date = new Date(System.currentTimeMillis());

    if (Pattern.compile(Pattern.quote(pattern), Pattern.CASE_INSENSITIVE).matcher(state).find()) {
      log.info("Alert: " + pattern + " used at " + formatter.format(date));
    }
  }
}
