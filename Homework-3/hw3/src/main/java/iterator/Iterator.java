package iterator;

/**
 * A generic iterator.
 */
public interface Iterator<T> {
  /**
   * Check if there are more elements
   */
  boolean hasMore();

  /**
   * Return the next element
   */
  T next();
}
