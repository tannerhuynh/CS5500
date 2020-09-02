package testiterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import iterator.BitVector;
import iterator.IBitVector;

public class BitVectorTest {
  private IBitVector bitVector;
  private IBitVector bitVector2;
  private IBitVector bitVector3;
  private IBitVector bitVector4;
  private IBitVector bitVector5;
  private IBitVector bitVector6;

  @Test
  public void testSetandGet() {
    bitVector = new BitVector();
    bitVector.set(12);
    bitVector.set(67);
    bitVector.set(4);
    assertTrue(bitVector.get(12));
    assertTrue(bitVector.get(67));
    assertTrue(bitVector.get(4));

    bitVector2 = new BitVector();
    bitVector2.set(124);
    bitVector2.set(9);
    assertFalse(bitVector2.get(12));
    assertFalse(bitVector2.get(67));
    assertTrue(bitVector2.get(124));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetAndGetIllegalVector() {
    bitVector3 = new BitVector();
    bitVector3.set(-1);
    bitVector3.get(68);
  }

  @Test
  public void testSetDuplicateElement() {
    bitVector4 = new BitVector();
    bitVector4.set(2);
    bitVector4.set(4);
    bitVector4.set(3);
    assertTrue(bitVector4.get(2));
    assertFalse(bitVector4.get(6));
  }

  @Test
  public void testClear() {
    bitVector2 = new BitVector();
    bitVector2.set(12);
    bitVector2.set(67);
    bitVector2.set(4);
    bitVector2.clear(67);
    assertTrue(bitVector2.get(12));
    assertFalse(bitVector2.get(67));
    assertTrue(bitVector2.get(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testClearBounds() {
    bitVector3 = new BitVector();
    bitVector3.clear(-1);
  }

  @Test
  public void testEmptyVectorSize() {
    bitVector6 = new BitVector();
    assertEquals(0,bitVector6.size());
  }

  @Test
  public void testCopy() {
    bitVector4 = new BitVector();
    bitVector4.set(11);
    bitVector4.set(99);
    bitVector3 = new BitVector();
    bitVector3.copy(bitVector4);
    assertEquals(2, bitVector3.size());
    bitVector3.get(11);
  }

  @Test
  public void testSize() {
    bitVector5 = new BitVector();
    bitVector5.set(12);
    bitVector5.set(67);
    bitVector5.set(4);
    bitVector5.clear(67);
    assertTrue(bitVector5.get(12));
    assertFalse(bitVector5.get(67));
    assertTrue(bitVector5.get(4));
    assertEquals(2,bitVector5.size());
  }
}
