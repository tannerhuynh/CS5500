package teststack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import stack.ArrayListAdapter;

public class ArrayAdapterTest {
  private ArrayListAdapter<Integer> list;
  private ArrayListAdapter<Integer> list2;
  private ArrayListAdapter<Integer> list3;

  @Test
  public void testAddAndPeek() {
    list = new ArrayListAdapter<>();
    list.push(12);
    assertEquals(Integer.valueOf(12), list.peek());

    list2 = new ArrayListAdapter<>();
    list2.push(12);
    list2.push(12);
    assertEquals(Integer.valueOf(12), list2.peek());
    assertEquals(Integer.valueOf(12), list2.pop());
    assertEquals(Integer.valueOf(12), list2.pop());

    list3 = new ArrayListAdapter<>();
    for(int i = 0 ; i < 9999 ; i++ ) {
      list3.push(i);
    }
    assertEquals(Integer.valueOf(0), list3.peek());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNull() {
    list2 = new ArrayListAdapter<>();
    list2.push(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveEmpty() {
    list3 = new ArrayListAdapter<>();
    list3.pop();
  }

  @Test
  public void testRemove() {
    list = new ArrayListAdapter<>();
    list.push(12);
    assertEquals(Integer.valueOf(12) , list.pop());

    list2 = new ArrayListAdapter<>();
    list2.push(1);
    list2.push(2);
    list2.push(3);
    list2.push(4);
    assertEquals(Integer.valueOf(1) , list2.pop());

    list3 = new ArrayListAdapter<>();
    for(int i = 0 ; i < 9999 ; i++ ) {
      list3.push(i);
    }
    assertEquals(Integer.valueOf(0), list3.pop());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPeek() {
    list = new ArrayListAdapter<>();
    assertNull(list.peek());
  }

  @Test
  public void testEmpty() {
    list = new ArrayListAdapter<>();
    assertTrue(list.empty());
    list.push(13);
    list.push(14);
    assertFalse(list.empty());
  }

  @Test
  public void testPeek() {
    list3 = new ArrayListAdapter<>();
    for(int i = 0 ; i < 9999 ; i++ ) {
      list3.push(i);
    }
    assertEquals(Integer.valueOf(0), list3.peek());
    assertEquals(Integer.valueOf(0), list3.peek());
    assertEquals(Integer.valueOf(0), list3.peek());
  }
}
