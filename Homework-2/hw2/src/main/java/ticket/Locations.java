package ticket;

import java.security.SecureRandom;

import util.Find;

/**
 * Enum representing locations in the world for travel. This enum has a getter for retrieving the
 * locations name.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public enum Locations {
  BOS("Boston, Massachusetts"),
  LOS("Lowell, Massachusetts"),
  LAX("Los Angeles, California"),
  IAH("Houston, Texas"),
  LAS("Las Vegas, Nevada"),
  SFO("San Francisco, California"),
  ATL("Atlanta, Georgia");

  private String name;

  /**
   * Constructor for locations enum.
   *
   * @param name string format of the location.
   */
  Locations(String name) {
    this.name = name;
  }

  /**
   * Getter for the string format of the enum.
   *
   * @return string representing the enum.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for the enum which matches the given string format.
   *
   * @param id string format
   * @return enum that matches the given string.
   */
  public static Locations find(String id) {
    return Find.find(Locations.class, id);
  }

  /**
   * Select a random enum.
   *
   * @return a random enum.
   */
  public static Locations getRandom() {
    return values()[new SecureRandom().nextInt(values().length)];
  }
}
