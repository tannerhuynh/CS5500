package testobserver;

import org.junit.Test;

import observer.ConcreteSubject;
import observer.IObserver;
import observer.ObserverA;
import observer.ObserverB;
import observer.ObserverC;

import static org.junit.Assert.*;

public class SubjectTest {
  private ConcreteSubject subjectA = new ConcreteSubject();
  private ConcreteSubject subjectB = new ConcreteSubject();
  private IObserver observerA = new ObserverA();
  private IObserver observerB = new ObserverB();
  private IObserver observerC = new ObserverC();

  @Test
  public void addObserver() {
    subjectA.addObserver(observerA);
    subjectA.addObserver(observerB);
    subjectA.addObserver(observerC);
    assertEquals(3, subjectA.getObserverListSize());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testIllegalOutBounds() {
    subjectA.removeObserver(observerA);
    subjectB.addObserver(observerA);
    subjectB.removeObserver(observerB);
  }

  @Test
  public void removeObserver() {
    subjectA.addObserver(observerA);
    subjectA.addObserver(observerB);
    subjectA.addObserver(observerC);
    subjectA.removeObserver(observerB);
    assertEquals(2, subjectA.getObserverListSize());
  }

  @Test
  public void getState() {
    subjectA.setState("Hello");
    assertEquals("Hello", subjectA.getState());
  }

  @Test
  public void setState() {
    subjectB.setState("World!");
    assertEquals("World!", subjectB.getState());
  }
}