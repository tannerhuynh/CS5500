package iterator;

import java.util.NoSuchElementException;

/**
 * The Class Bit Vector.
 */
public class BitVector implements IBitVector {
  /**
   * List of integers.
   */
  private int[] intArray;

  /**
   * Constructor for a new bit vector.
   */
  public BitVector() {
    this.intArray = new int[1];
  }

  @Override
  public boolean get(int i) {
    int arrPos = i / 32;
    int bitPos = i % 32;

    if(arrPos > 0) {
      arrPos = arrPos - 1;
    }

    if(arrPos > (intArray.length - 1)) {
      return false;
    }

    return (intArray[arrPos] & (31 << (bitPos - 1))) != 0;
  }

  @Override
  public void set(int i) {
    if(i < 0) {
      throw new IllegalArgumentException("Number cannot be negative.");
    }

    int arrPos = i / 32;
    int bitPos = i % 32;

    if(arrPos > (intArray.length - 1)) {
      intArray = resize(intArray, arrPos);
    }

    if(arrPos > 0) {
      arrPos = arrPos - 1;
    }

    intArray[arrPos] = ((1 << bitPos) | intArray[arrPos]);
  }

  /**
   * Resize an array if the value being set is outside of the current array size.
   * @param arr current array.
   * @param i new size.
   */
  private int[] resize(int[] arr, int i) {
    int[] temp = arr;
    arr = new int[i];
    System.arraycopy(temp, 0, arr, 0, temp.length);
    return arr;
  }

  @Override
  public void clear(int i) {
    int arrPos = i / 32;
    int bitPos = i % 32;

    if(i < 0) {
      throw new IllegalArgumentException("Out of bounds.");
    }

    if(arrPos > 0) {
      arrPos = arrPos - 1;
    }

    intArray[arrPos] &= ~(1 << bitPos);
  }

  @Override
  public void copy(IBitVector vector) {
    Iterator<Integer> iterator = vector.iterator();

    while (iterator.hasMore()) {
      this.set(iterator.next());
    }
  }

  @Override
  public int size() {
    int count = 0;

    for(int i = 0 ; i < intArray.length; i++) {
      while (intArray[i] > 0) {
        count += intArray[i] & 1;
        intArray[i] >>= 1;
      }
    }
    return count;
  }

  @Override
  public Iterator<Integer> iterator() {
    return new BitVectorIterator(this);
  }

  /**
   * The Class BitVectorIterator.
   */
  private static class BitVectorIterator implements Iterator<Integer> {
    /**
     * The set of numbers.
     */
    int[] intArr;

    int arrPos = 0;

    /**
     * Instantiates a new bit vector iterator.
     *
     * @param bitVector the bit vector
     */
    private BitVectorIterator(BitVector bitVector) {
      this.intArr = bitVector.intArray;
    }
    @Override
    public boolean hasMore() {
      for (int i = arrPos; i < intArr.length; i++) {
        StringBuilder num = new StringBuilder(Integer.toBinaryString(intArr[i]));
        for (int j = num.length() - 1; j >= 0; j--) {
          if (num.charAt(j) == '1') {
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public Integer next() {
      while ( arrPos < intArr.length) {
        StringBuilder num = new StringBuilder(Integer.toBinaryString(intArr[arrPos]));
        int bitPos = num.length() - 1;
        while (bitPos >= 0) {
          if (num.charAt(bitPos) == '1') {
            arrPos++;
            bitPos--;
            return (arrPos * 32) + (31 - bitPos);
          }
          bitPos--;
        }
        arrPos++;
      }
      throw new NoSuchElementException("No element found");

    }
  }
}
