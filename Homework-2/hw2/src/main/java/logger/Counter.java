package logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import util.Pair;

/**
 * Counter class to keep track of how many tickets of each type were created and how many travel
 * destinations have been reached of each location.
 */
public class Counter {
  private static List<Pair<Locations, Integer>> counterLocation = new ArrayList<>();
  private static Integer train = 0;
  private static Integer plane = 0;
  private static Integer bus = 0;

  /**
   * Illegal instantiation of constructor.
   */
  private Counter() {
    // Nothing
  }

  /**
   * Keeps count of locations using a list.
   *
   * @param loc Location enum.
   */
  public static void countLocation(Locations loc) {
    boolean found = false;
    if(!counterLocation.isEmpty()) {
      for (Pair<Locations, Integer> pair : counterLocation) {
        if (pair.getLeft() == loc && !found) {
          pair.setRight(pair.getRight() + 1);
          found = true;
        } else if (counterLocation.indexOf(pair) == counterLocation.size() - 1 && !found) {
          counterLocation.add(new Pair<>(loc, 1));
          break;
        }
      }
    } else {
      counterLocation.add(new Pair<>(loc, 1));
    }
  }

  /**
   * Train counter.
   */
  public static void trainCounter(){
    train = train + 1;
  }

  /**
   * Plane counter.
   */
  public static void planeCounter(){
    plane = plane + 1;
  }

  /**
   * Bus counter.
   */
  public static void busCounter(){
    bus = bus + 1;
  }

  /**
   * Prints out the tickets counted.
   */
  public static void print() {
    for (Pair<Locations, Integer> e : counterLocation) {
      System.out.println(e.getLeft() + ": " + e.getRight());
    }
    System.out.println("Train tickets: " + train);
    System.out.println("Plane tickets: " + plane);
    System.out.println("Bus tickets: " + bus);
  }

  /**
   * Logger to logs tickets counted.
   * @throws IOException if file cannot be created.
   */
  public static void report() throws IOException {
    String className = Counter.class.getName();
    Logger logger = Logger.getLogger(className);
    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

    FileHandler handler;
    try {
      handler = new FileHandler("counter.txt", true);
    } catch (SecurityException | IOException e) {
      throw new IOException("Could not create file.");
    }
    handler.setFormatter(new SimpleFormatter());
    logger.entering(className, method);
    logger.addHandler(handler);


    for (Pair<Locations, Integer> e : counterLocation) {
      logger.log(Level.INFO, e.getLeft() + ":  " + e.getRight());
    }

    logger.log(Level.INFO, "Train tickets: {0}", train);
    logger.log(Level.INFO, "Plane tickets: {0}", plane);
    logger.log(Level.INFO, "Bus tickets: {0}", bus);
  }
}
