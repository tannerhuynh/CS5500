package observer;

import org.apache.log4j.Logger;

public class LoggerObserver implements IObserver {
  private final IObserver classToBeLogged;
  private static Logger log = Logger.getRootLogger();
  private String className;

  public LoggerObserver(IObserver classToBeLogged) {
    this.classToBeLogged = classToBeLogged;
    className = classToBeLogged.getClass().getName();
  }

  private void bracketMessage(String methodName) {
    log.debug(this.className + "." + methodName);
  }

  @Override
  public void update(String state) {
    bracketMessage( Thread.currentThread().getStackTrace()[2].getMethodName());
    classToBeLogged.update(state);
    bracketMessage(Thread.currentThread().getStackTrace()[2].getMethodName());
  }
}
