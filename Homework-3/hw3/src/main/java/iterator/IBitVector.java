package iterator;

/**
 * A bit vector.
 */
public interface IBitVector {
  /**
   * Determine if the bit at position i is set. True if it is set; otherwise false.
   *
   * @param i position of bit.
   */
  boolean get(int i);

  /**
   * Set the bit at position i.
   *
   * @param i position of bit.
   */
  void set(int i);

  /**
   * Clear the bit at position i.
   *
   * @param i position of bit.
   */
  void clear(int i);

  /**
   * Copy the bits from an IBitVector.
   *
   * @param b bit vector.
   */
  void copy(IBitVector b);

  /**
   * Determine the number of non-zero bits in the BitVector.
   */
  int size();

  /**
   * Iterator over the Integer values represented by this BitVector.
   */
  Iterator<Integer> iterator();
}
