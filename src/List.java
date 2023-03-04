import java.util.NoSuchElementException;

/**
 * List class ADT. Generalize by using "Object" for the element type.
 * List.java class from previous assignment
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
 */
public interface List { // List class ADT
    /**
     * Remove all contents from the list, so it is once again empty
     */
    public void clear();

    /**
     * Insert "it" at the current location
     * The client must ensure that the list's capacity is not exceeded
    */
    public boolean insert(Object it);

    /**Append "it" at the end of the list
    // The client must ensure that the list's capacity is not exceeded
    */
    public boolean append(Object it);

    /**
     * Remove and return the current element
    */
    public Object remove() throws NoSuchElementException;

    /**
     * Set the current position to the start of the list
     */
    public void moveToStart();

    /**
     * Set the current position to the end of the list
     */
    public void moveToEnd();

    /**
     * Move the current position one step left, no change if already at beginning
     */
    public void prev();

    /**
     * Move the current position one step right, no change if already at end
     */
    public void next();

    /**
     * Return the number of elements in the list
     * @return
     */
    public int length();

    /**
     * Return the position of the current element
     * @return
     */
    public int currPos();

    /**
     * Set the current position to "pos"
     * @param pos
     * @return
     */
    public boolean moveToPos(int pos);

    /**
     * Return true if current position is at end of the list
     * @return
     */
    public boolean isAtEnd();

    /**
     * Return the current element
     * @return
     * @throws NoSuchElementException
     */
    public Object getValue() throws NoSuchElementException;

    /**
     * Return true if list is empty
     * @return
     */
    public boolean isEmpty();

    /**
     * reverses the order of the list
     */
    public void reverse();

    /**
     * Returns the object at a given index
     * @param index
     * @return
     */
    public Object get(int index);
}
