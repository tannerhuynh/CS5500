package observer;

/**
 * Observer interface that implements the method to update the observer if changes occur in the
 * Observee.
 *
 * @author thuynh
 * @since October 21, 2019
 * @version 1.0.1
 */
public interface IObserver {

  /**
   * Reports the changes in state for the observee.
   * @param state changes to state.
   */
  void update(String state);
}
