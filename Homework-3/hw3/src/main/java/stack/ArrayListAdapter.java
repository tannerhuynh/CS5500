package stack;

import java.util.ArrayList;

/**
 * The adapter for an ArrayList.
 *
 * @param <T> the generic type
 */
public class ArrayListAdapter<T> implements Stack<T> {
  /**
   * The array list.
   * */
  private ArrayList<T> list;

  /**
   * Constructor for the array adapter.
   */
  public ArrayListAdapter() {
    list = new ArrayList<>();
  }

  @Override
  public boolean empty() {
    return list.isEmpty();
  }

  @Override
  public T push(T item) {
    if(item == null) {
      throw new IllegalArgumentException("Cannot add null.");
    }
    list.add(item);
    return item;
  }

  @Override
  public T pop() {
    if(!list.isEmpty()) {
      T item = list.get(0);
      list.remove(0);
      return item;
    }
    throw new IllegalArgumentException("Stack is empty.");
  }

  @Override
  public T peek() {
    if(!list.isEmpty()) {
      return list.get(0);
    }
    throw new IllegalArgumentException("Stack is empty.");
  }
}
