package observer;

import org.apache.log4j.Logger;

/**
 * Class for an observer that implements the Observer interface.
 */
public class ObserverA implements IObserver {
  private static Logger log = Logger.getRootLogger();

  @Override
  public void update(String state) {
    StringBuilder str = new StringBuilder();

    for (char c : state.toCharArray()) {
      str.append((int) c).append(" ");
    }

    log.info(str.toString());
  }
}
