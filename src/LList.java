import java.util.NoSuchElementException;

/**
 * Linked list implementation from previous assignment
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
 */
class LList implements List {
    private Link head;         // Pointer to list header
    private Link tail;         // Pointer to last element
    private Link curr;         // Access to current element
    private int listSize;      // Size of list

    /**
     * LList constructor 1
     * @param size the initial size of LList
     */
    LList(int size) { this(); }     // Constructor -- Ignore size

    /**
     * LList Constructor 2
     */
    LList() { clear(); }

    /**
     * Remove all elements
     */
    public void clear() {
        curr = tail = new Link(null); // Create trailer
        head = new Link(tail);        // Create header
        listSize = 0;
    }

    /**
     * Insert "it" at current position
     * @param it Object to be inserted
     * @return true when complete
     */
    public boolean insert(Object it) {
        curr.setNext(new Link(curr.element(), curr.next()));
        curr.setElement(it);
        if (tail == curr) tail = curr.next();  // New tail
        listSize++;
        return true;
    }

    /**
     * Append "it" to list
     * @param it Object to be appended
     * @return true when complete
     */
    public boolean append(Object it) {
        tail.setNext(new Link(null));
        tail.setElement(it);
        tail = tail.next();
        listSize++;
        return true;
    }

    /**
     * Remove and return current element
     * @return Object which was removed
     * @throws NoSuchElementException if nothing to remove
     */
    public Object remove () throws NoSuchElementException {
        if (curr == tail) // Nothing to remove
            throw new NoSuchElementException("remove() in LList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        Object it = curr.element();             // Remember value
        curr.setElement(curr.next().element()); // Pull forward the next element
        if (curr.next() == tail) tail = curr;   // Removed last, move tail
        curr.setNext(curr.next().next());       // Point around unneeded link
        listSize--;                             // Decrement element count
        return it;                              // Return value
    }

    /**
     * moveToStart
     */
    public void moveToStart() { curr = head.next(); } // Set curr at list start

    /**
     * movetoEnd
     */
    public void moveToEnd() { curr = tail; }          // Set curr at list end

    /**
     * Move curr one step left; no change if now at front
     */
    public void prev() {
        if (head.next() == curr) return; // No previous element
        Link temp = head;
        // March down list until we find the previous element
        while (temp.next() != curr) temp = temp.next();
        curr = temp;
    }

    /**
     * Move curr one step right; no change if now at end
     */
    public void next() { if (curr != tail) curr = curr.next(); }

    /**
     * find length of LList
     * @return int listSize
     */
    public int length() { return listSize; } // Return list length


    /**
     * Return the position of the current element
     * @return int of current position in LList
     */
    public int currPos() {
        Link temp = head.next();
        int i;
        for (i=0; curr != temp; i++)
            temp = temp.next();
        return i;
    }

    /**
     * Move down list to "pos" position
     * @param pos int position to be moved to in LList
     * @return true if valid position in LList. Otherwise, false
     */
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) return false;
        curr = head.next();
        for(int i=0; i<pos; i++) curr = curr.next();
        return true;
    }

    /**
     * This fynction checks if Link is at the end of LList
     * @return Return true if current position is at end of the list
     */
    public boolean isAtEnd() { return curr == tail; }

    /**
     * This function returns current element value
     * @return Object -> current element value
     * @throws NoSuchElementException
     */
    public Object getValue() throws NoSuchElementException {
        if (curr == tail) // No current element
            throw new NoSuchElementException("getvalue() in LList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        return curr.element();
    }

    /**
     * Check if the list is empty
     * @return true if empty. Otherwise, false
     */
    public boolean isEmpty() { return listSize == 0; }

    /**
     * Reverses the list
     */
    public void reverse(){
        if (listSize>1) {
            Link prev = head;
            curr = head.next();
            head.setNext(null);
            Link next = curr.next();

            while (curr != tail) {
                curr.setNext(prev);
                prev = curr;
                curr = next;
                next = curr.next();
            }
            curr.setNext(prev);
            Link temp = head;
            head = tail;
            tail = temp;
        }
    }

    /**
     * Returns the object at a given index
     */
    public Object get(int index) {
        moveToPos(index);
        return getValue();
    }
}