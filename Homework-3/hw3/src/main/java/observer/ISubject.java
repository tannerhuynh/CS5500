package observer;

/**
 * Subject interface that implements methods to add, remove, and notify observers for subject
 * classes.
 *
 * @author thuynh
 * @since October 21, 2019
 * @version 1.0.1
 */
public interface ISubject {

  /**
   * Adds an observer to the subject.
   * @param o observer.
   */
  void addObserver(IObserver o);

  /**
   * Removes an observer from the subject.
   * @param o observer.
   */
  void removeObserver(IObserver o);

  /**
   * Getter for the size of the observer list.
   * @return size of observer list.
   */
  int getObserverListSize();

  /**
   * Notify all observers of the changes in the subject's state.
   */
  void notifyObservers();
}
