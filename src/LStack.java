
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
     * @param size
     */
    LStack(int size) {
        top = null;
        size = 0;
    }

    /**
     *  Reinitialize stack
     */
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * Put "it" on stack
     * @param it
     * @return
     */
    public boolean push(Object it) {
        top = new Link(it, top);
        size++;
        return true;
    }

    /**
     *  Remove "it" from stack
     * @return
     */
    public Object pop() {
        if (top == null) return null;
        Object it = top.element();
        top = top.next();
        size--;
        return it;
    }

    /**
     * return top value
     * @return
     */
    public Object topValue() {      // Return top value
        if (top == null) return null;
        return top.element();
    }

    /**
     *  Return stack length
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * Check if the stack is empty
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method ensures that every close parenthesis ')' and bracket ']' in a given String have corresponding
     * open parenthesis '(' and brackets '[' in the correct positions.
     * This method takes a String as a parameter.
     * All open brackets and parenthesis are pushed to the Stack one at a time.
     * If a closed bracket or parenthesis is found, the top value of the stack must be a corresponding open bracket
     * or parenthesis, in order for it to be well-formed.
     * Every correctly corresponding open bracket or parenthesis in the top value gets popped from the Stack.
     * At the end of the algorithm, all open parenthesis or brackets should have been popped from the Stack.
     * Leaving it empty. If the Stack is empty, then the String was well-formed. Otherwise, it was not well-formed.
     * A message will be printed back to the user informing them whether the String was well-formed or not.
     * @param x String
     */
    public void matchParenthesis(String x) {
        //Check to see if the String parameter entered is empty. If so print message to user letting them know.
        if (x.length() == 0){
            System.out.println("The String you have entered is empty.");
            //If the String parameter has one character, it not well-formed
        } else if (x.length() == 1){
            System.out.println("This String is not well-formed.");
        }
        //If the String parameter is longer than one character, check if it is well-formed or not.
        else {
            //Push each char in String x that equals an opening bracket '[' or opening parenthesis '(' to the Stack.
            for (int i = 0; i < x.length(); i++) {
                if (x.charAt(i) == '(' || x.charAt(i) == '[') {
                    push(x.substring(i, i + 1));
                    //Any closing parenthesis results in a search for a corresponding opening parenthesis to pop from Stack.
                } else if (x.charAt(i) == ')') {
                    //If there is nothing in the stack to compare the closing parenthesis to,
                    //then the String is not well-formed.
                    if (length() == 0){
                        //Push the closing parenthesis into the Stack so that it does not read empty at the end.
                        push(x.substring(i, i + 1));
                        //If the top value in the Stack is an open parenthesis, then pop it from the Stack.
                    } else if (topValue().toString().equals("(")) {
                        pop();
                        //If the top value in the Stack is not an open parenthesis, then the String is not well-formed.
                    } else {
                        //Exit the for-loop
                        break;
                    }
                    //Any closing bracket results in a search for a corresponding opening bracket to pop from Stack.
                } else if (x.charAt(i) == ']') {
                    //If there is nothing in the stack to compare the closing bracket to,
                    //then the String is not well-formed.
                    if (length() == 0) {
                        //Push the closing bracket into the Stack so that it does not read empty at the end.
                        push(x.substring(i, i + 1));
                        //If the top value in the Stack is an open bracket, then pop it from the Stack.
                    } else if (topValue().toString().equals("[")) {
                        pop();
                        //If the top value in the Stack is not an open bracket, then the String is not well-formed.
                    } else {
                        //Exit the for-loop
                        break;
                    }
                }
            }
            //If all open brackets and open parenthesis have been popped from the stack the String was well-formed.
            if (length() == 0) {
                System.out.println("This String is well-formed.");
            } else {
                //There are element in the stack. Therefore, the String was not well-formed.
                System.out.println("This String is not well-formed.");
                //Ensure the stack is empty at the end of function.
                clear();
            }
        }
    }
}