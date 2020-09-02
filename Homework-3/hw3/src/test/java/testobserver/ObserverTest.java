package testobserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import observer.ConcreteSubject;
import observer.ObserverA;
import observer.ObserverB;
import observer.ObserverC;

import static org.junit.Assert.*;

public class ObserverTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void update() {
    ConcreteSubject subject = new ConcreteSubject();
    ObserverA observerA = new ObserverA();
    ObserverB observerB = new ObserverB();
    ObserverC observerC = new ObserverC();
    subject.addObserver(observerA);
    subject.addObserver(observerB);
    subject.addObserver(observerC);

    subject.setState("Hello I'm Done");
    subject.notifyObservers();

    assertEquals("Hello I'm Done", subject.getState());
  }
}