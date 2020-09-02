package factory;

import java.security.SecureRandom;

import util.Find;

/**
 * Enums representing the service classes available for certain tickets. Class hierarchy ranges
 * from first class, business, to economy as the lowest fare.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public enum ClassOfService {
  FIRST_CLASS,
  BUSINESS,
  PREMIUM_ECONOMY,// Planes only
  ECONOMY,        // Planes only
  BASIC_ECONOMY,  // Planes only
  SLEEPER_CABIN, // Trains only
  COACH;          // Trains only

  /**
   * Getter for the enum which matches the given string format.
   *
   * @param id string format
   * @return enum that matches the given string.
   */
  public static ClassOfService find(String id) {
    if(id.equalsIgnoreCase("null")) {
      return null;
    } else {
      return Find.find(ClassOfService.class, id);
    }
  }

  /**
   * Select a random enum.
   *
   * @return a random enum.
   */
  public static ClassOfService getRandom() {
    return values()[new SecureRandom().nextInt(values().length)];
  }
}
