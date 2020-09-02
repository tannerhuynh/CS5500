package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * A type of concrete subject that implements the methods in the subject interface.
 */
public class ConcreteSubject implements ISubject {
  /**
   * Current state of the subject.
   */
  private String state;
  /**
   * List of observers currently observing the observee.
   */
  private List<IObserver> observerList;

  /**
   * Constructor for the subject with the list of observers.
   */
  public ConcreteSubject() {
    observerList = new ArrayList<>();
  }

  @Override
  public void addObserver(IObserver o) {
    observerList.add(o);
  }

  @Override
  public void removeObserver(IObserver o) {
    if (observerList.isEmpty()) {
      throw new IndexOutOfBoundsException("List is empty.");
    }
    observerList.remove(o);
  }

  @Override
  public int getObserverListSize() {
    return this.observerList.size();
  }

  @Override
  public void notifyObservers() {
    for (IObserver o : observerList) {
      o.update(state);
    }
  }

  /**
   * Getter for the current state of the subject.
   * @return state of the subject
   */
  public String getState() {
    return this.state;
  }

  /**
   * Setter for the current state of the subject.
   * @param state state
   */
  public void setState(String state) {
    this.state = state;
  }
}
