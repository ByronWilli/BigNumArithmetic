import org.junit.Test;
import static org.junit.Assert.*;

public class LListTest {
    /**
     * This method tests the reverse() method within the LList class.
     * Using 2 LList objects reverse one and use a supplementary test
     * to compare the values in the reversed positions.
     */
    @Test
    public void reverseTest() {
        //Create 2 LLists with the same element in the same positions
        LList inOrder = new LList();
        inOrder.append(1);
        inOrder.append(2);
        inOrder.append(3);
        inOrder.append(4);
        inOrder.append(5);
        inOrder.append(6);

        LList reverseOrder = new LList();
        reverseOrder.append(1);
        reverseOrder.append(2);
        reverseOrder.append(3);
        reverseOrder.append(4);
        reverseOrder.append(5);
        reverseOrder.append(6);

        //This test tests whether the elements in the LList were reversed before calling the reverse function().
        //Using the supplemental function reverseSupp() this should return false.
        assertEquals(false, reverseSupp(inOrder,reverseOrder));
        //This test tests whether the elements in the LList were reversed after calling the reverse function().
        //Using the supplemental function reverseSupp() this should return true.
        inOrder.reverse();
        assertEquals(true, reverseSupp(inOrder, reverseOrder));
    }

    /**
     * This method is a supplemental function for the reverse() test method.
     * It ensures that each object in the reversed list are in opposite order from the original list.
     * @param originalList LList that was not reversed.
     * @param reversedList LList that was supposed to be reversed.
     * @return boolean true if all elements in reversedList are in opposite order from original list.
     */

    public boolean reverseSupp(LList originalList, LList reversedList) {
        //Create an int pointing to the end of the lists.
        int j = reversedList.length() - 1;
        //Initiate a boolean to be returned if all elements pass the test.
        boolean x = true;
        //Loop through originalList from start to end of the LList
        //compare that value to the value at position j in reversedList.
        for (int i = 0; i < reversedList.length(); i++) {
            originalList.moveToPos(j);
            reversedList.moveToPos(i);
            if (originalList.getValue() != reversedList.getValue()) {
                //if elements not equal return false
                return false;
            }
            //decrement j to move the position in reversedList
            j--;
        }
        //If all cases tested did not result in returning false, return true
        return x;
    }

    /**
     * This tests whether objects are being stored properly to LLists and LStacks.
     * Specifically, we are testing with the push() and pop() methods from LStack
     * and testing append() and remove() method from LList.
     * In LStack push() adds to stack pop() removes top value from stack.
     * In LList append() adds an Object to the LList and remove() removes it.
     * This will mimic how things are file inputs are stored in process()
     */
    @Test
    public void storageTest(){
        //create 2 chars to be put into LList objects
        char two = '2';
        char four = '4';
        //create an empty stack
        LStack myStack = new LStack();
        //create 2 LLists
        LList myLList1 = new LList();
        LList myLList2 = new LList();
        //put int values into LLists
        myLList1.append(two);
        myLList2.append(four);
        //Test that the Objects were properly stored in LList
        //myLList1 should have getValue() should be 2
        assertEquals('2', myLList1.getValue());
        //myLList2 should have a value of 4
        assertEquals('4', myLList2.getValue());
        //push myLList1 to LStack, test length() of LStack and topValue() of LStack
        //This will verify that push worked correctly
        myStack.push(myLList1);
        //myLList1 is the only element in stack, length() should return 1
        assertEquals(1, myStack.length());
        // topValue() should be myLList1 as it was last pushed in stack
        assertEquals(myLList1, myStack.topValue());
        //Now push myLList2 to Stack
        myStack.push(myLList2);
        //myLList2 & myLList2 are the only elements in stack, length() should return 2
        assertEquals(2, myStack.length());
        // topValue() should be myLList2 as it was last pushed in stack
        assertEquals(myLList2, myStack.topValue());
        //pop() returns the value removed from top of stack. This should be myLList2
        assertEquals(myLList2, myStack.pop());
        //'2' is the only element left in stack, length() should return 1
        assertEquals(1, myStack.length());
        // topValue() in LStack should be myLList1
        assertEquals(myLList1, myStack.topValue());
    }
}