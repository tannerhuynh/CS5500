package util;

/**
 * Class to create pairs of related objects.
 * @param <L> object
 * @param <R> object
 */
public class Pair<L, R> {
  private L left;
  private R right;

  /**
   * Constructor for pairs.
   *
   * @param left left object.
   * @param right right object.
   */
  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Getter for the left object.
   *
   * @return the left pair object.
   */
  public L getLeft() {
    return left;
  }

  /**
   * Getter for the right object.
   *
   * @return the right pair object.
   */
  public R getRight() {
    return right;
  }

  /**
   * Setter for the right pair object.
   *
   * @param right object to be set.
   */
  public void setRight(R right) {
    this.right = right;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    Pair<?, ?> pair = (Pair<?, ?>) o;

    if (!left.equals(pair.left))
      return false;
    return right.equals(pair.right);
  }

  @Override
  public int hashCode()
  {
    return 31 * left.hashCode() + right.hashCode();
  }
}
