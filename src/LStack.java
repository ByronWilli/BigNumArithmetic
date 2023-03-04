
/**
 * Linked stack implementation from previous assignment
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
  */
class LStack implements Stack {
    private Link top;               // Pointer to first element
    private int size;               // Number of elements

    /**
     * LStack Constructor 1
     *
     */
    LStack() {
        top = null;
        size = 0;
    }

    /**
     * LStack constructor 2
     * @param size the size entered for the LStack
     */
    LStack(int size) {
        top = null;
        size = 0;
    }

    /**
     * Reinitialize stack
     */
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * Put "it" on stack
     * @param it Object to be added to LStack
     * @return true when complete
     */
    public boolean push(Object it) {
        top = new Link(it, top);
        size++;
        return true;
    }

    /**
     * Remove "it" from stack
     * @return Object removed from LStack
     */
    public Object pop() {
        if (top == null) return null;
        Object it = top.element();
        top = top.next();
        size--;
        return it;
    }

    /**
     * @return top value
     */
    public Object topValue() {      // Return top value
        if (top == null) return null;
        return top.element();
    }

    /**
     * @return stack length
     */
    public int length() {
        return size;
    }

    /**
     * Check if the stack is empty
     * @return true if empty. Otherwise, false
     */
    public boolean isEmpty() {
        return size == 0;
    }
}