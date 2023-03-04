/**
 * Stack Interface class from previous assignment
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
 */
public interface Stack { // Stack class ADT
    /**
     * Reinitialize the stack.
     */
    public void clear();

    /**
     * Push "it" onto the top of the stack
     * @param it Object to be pushed
     * @return true when complete
     */
    public boolean push(Object it);

    /**
     * Remove and return the element at the top of the stack
     * @return Object which was removed
     */
    public Object pop();

    /**
     * Return a copy of the top element
     * @return Object at top of Stack
     */
    public Object topValue();

    /**
     * Return the number of elements in the stack
     * @return int number of elements
     */
    public int length();

    /**
     * @return Return true if the stack is empty
     */
    public boolean isEmpty();
}