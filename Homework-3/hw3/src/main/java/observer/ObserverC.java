package observer;

import org.apache.log4j.Logger;

/**
 * Class for an observer that implements the Observer interface.
 */
public class ObserverC implements IObserver {
  private static Logger log = Logger.getRootLogger();

  @Override
  public void update(String state) {
    StringBuilder str = new StringBuilder();

    for(int i = state.length() - 1; i >= 0; i--) {
      str.append(state.charAt(i));
    }

    log.info(str.toString());
  }
}
