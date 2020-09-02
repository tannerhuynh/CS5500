package stack;

/**
 * A generic stack
 */
public interface Stack<T> {
  /**
   * Tests if this stack is empty.
   *
   * @return True if and only if this stack contains no items; false otherwise.
   */
  boolean empty();

  /**
   * Pushes an item onto the top of this stack.
   *
   * @param item the item to be pushed onto this stack.
   * @return the item argument.
   */
  T push(T item);

  /**
   * Removes the object at the top of this stack and returns that object as the value of this
   * function.
   *
   * @return The object at the top of this stack (the last item of the Vector object).
   */
  T pop();

  /**
   * Looks at the object at the top of this stack without removing it from the stack. Returns: the
   * object at the top of this stack.
   */
  T peek();
}
