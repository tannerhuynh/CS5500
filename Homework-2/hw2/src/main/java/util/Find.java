package util;

/**
 * Utility function to match a string to an object within an enum list.
 *
 * @author tannerhuynh
 * @version 1.0.2
 * @since 10-07-2019
 */
public class Find {

  /**
   * Cannot instantiate a utility class.
   */
  private Find() {
    // Nothing
  }

  /**
   * Class for checking whether a valid object exists based on a given string.
   *
   * @param em object.
   * @param id String.
   * @param <E> Object.
   * @return the enum that matches the String.
   */
  public static <E extends Enum<E>> E find(Class<E> em, String id) {
    E result;
    try {
      result = Enum.valueOf(em, id);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid value for enum in JSON file.");
    }
    return result;
  }
}