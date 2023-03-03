import java.io.*;
import java.util.Scanner;

/**
 * BigNumArithmetic class holds the main() method to run the code.
 * Along with multiple functions to complete project requirements.
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
 */
public class BigNumArithmetic {

    /**
     * main() function reads the file passed in from command line
     * Calls process() to process the input of the file as needed.
     * The results of process() are printed back to user.
     * @param args command line argument
     */
    public static void main(String[] args) {
        try {
            System.out.println(process(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file");
        }
    }
    /**
     * Process() is called in the main() method. To run take each input from the file and process it as needed.
     * numbers are pushed to the stack, while operators call specific functions such as add(), mult(), and exp()
     * to find the answer to the final equation.
     * A String is returned to main containing the expression details and answer to the equation.
     * @param s a string which is passed on the command line
     * @return String which contatins expression details and final answer
     * @throws FileNotFoundException if the String passed as parameter is not found as a valid file path.
     */
    public static String process(String s) throws FileNotFoundException {
        //Create a String of that will be updated to print.
        String finalLine = "";
        //Set the file path to command line input args[0].
        File inputFile = new File(s);
        Scanner fileIn = new Scanner(inputFile);
        //While file is not empty get next string.
        while (fileIn.hasNextLine()) {
            //Create LStack, LList, String for expressions
            LStack myStack = new LStack();
            LList myLList = new LList();
            String expressionDetails = "";
            //create boolean to check for valid line inputs
            Boolean badline=false;
            //newLine is the char the most recent input read from file
            String newInput = fileIn.nextLine();
            //Convert newLine to a char array. Then loop through each char
            char[] line = newInput.toCharArray();
            for (int i = 0; i < newInput.length(); i++) {
                //Every empty space pushes LList to Stack
                char c = line[i];
                if (Character.isWhitespace(c)) {
                    //check if LList is empty. If so continue to next file input
                    if (myLList.isEmpty()) {
                        continue;
                    } else {
                        //LList is not empty, reverse() & push() LList to stack.
                        //clear() so new LList can be created.
                        leadingZeros(myLList);
                        myLList.reverse();
                        expressionDetails += toString(myLList) + " ";
                        myStack.push(myLList);
                        myLList = new LList();
                    }
                    //newInput being "+" triggers add() method.
                    //pop() top 2 LLists from stack to use in add() function
                } else if (c == '+') {
                    LList first = (LList) myStack.pop();
                    LList second = (LList) myStack.pop();
                    expressionDetails += "+ ";
                    //call add() on both LLists and push result back into stack;
                    if (first == null || second == null) { badline = true; }
                    if (badline == false){ myStack.push(add(first, second)); }
                } else if (c == '*') {
                    LList first = (LList) myStack.pop();
                    LList second = (LList) myStack.pop();
                    expressionDetails += "* ";
                    if (first == null || second == null) { badline = true; }
                    //if valid line call mult() function
                    if (badline == false) {
                        LList result = mult(first, second, 0);
                        //reverse result in order to rid LList of any leading 0's
                        result.reverse();
                        leadingZeros(result);
                        //reverse back and push to stack
                        result.reverse();
                        myStack.push(result);
                    }
                } else if (c == '^') {
                    LList first = (LList) myStack.pop();
                    LList second = (LList) myStack.pop();
                    if (first == null || second == null) { badline = true; }
                    //converts LList to an int
                    if (badline == false) {
                        first.moveToStart();
                        int expNum = 1;
                        int finalNum = 0;
                        while (first.isAtEnd() == false) {
                            finalNum += ((int) first.getValue() * expNum);
                            expNum *= 10;
                            first.next();
                        }
                        //call exp() on both LLists and push result back into stack;
                        myStack.push(exp(second, finalNum));
                    }
                    expressionDetails +="^ ";
                } else {
                    //If newInput is not " ", "+", "*", or "^", then it should be a number. Append() to LList.
                    int x = (int) charToInt(c);
                    myLList.append(x);
                }
            }
            if (myStack.length() != 1){
                badline=true;
            }
            if (badline){
                finalLine += expressionDetails + "=\n";
            } else {
                finalLine += expressionDetails + "= " + toString((LList) myStack.pop()) + "\n";
            }
        }
        return finalLine;
    }

    /**
     * This function turns the String value read from file to an Integer.
     * Only called on number values read from the file into the program
     * Corresponding Integer values are returned as an int. While any non number chars return null;
     * @param x char from file input
     * @return Object an int if char was a valid number. Otherwise, return null.
     */
     public static Object charToInt(char x) {
        if (x == '0') {
            return 0;
        } else if (x == '1'){
            return 1;
        } else if (x == '2'){
            return 2;
        } else if (x == '3'){
            return 3;
        } else if (x == '4'){
            return 4;
        } else if (x == '5'){
            return 5;
        } else if (x == '6'){
            return 6;
        } else if (x == '7'){
            return 7;
        } else if (x == '8'){
            return 8;
        } else if (x == '9'){
            return 9;
        } else {
            return null;
        }
    }
    /**
     * This function is used to remove any leading zeroes within the LList it is called upon. "00054" will equal "54"
     * This function also prevents LList with only zero values from being removed completely. "00000" will equal "0"
     * This must be used when LList is complete, and before reversing the LList.
     * This function does not return anything but rather changes the LList
     * @param myLList
     */
    public static void leadingZeros(LList myLList){
        //Loop through the LList. Move current position to the position "i" each time.
        for (int i=0; i<myLList.length();i++){
            myLList.moveToPos(i);
            //If there is only 1 element in LList, do not change anything. Preventing LLists valued 0 from being erased
            if(myLList.length() == 1){
                break;
            //If LList length has more than one element, remove Links until you reach a Link with a non-zero value
            } else if((int) myLList.getValue() == 0) {
                myLList.remove();
                //decrement i back to start of LList
                i--;
            } else {
                //If this link is non-zero break from for loop
                break;
            }
        }
    }

    /**
     * add()
     * @param a
     * @param b
     * @return
     */
    public static LList add(LList a, LList b){
        LList c = new LList();
        //handle adding zero
        if ((int)a.get(0) == 0&&a.length()==1){return b;}
        if ((int)b.get(0) == 0&&b.length()==1){return a;}
        // Append zeros to the shorter number until numbers are the same size
        if(a.length()>b.length()){
            int i = a.length()-b.length();
            for (int j=0; j < i; j++) {
                b.append(0);
            }
        }
        if(b.length()>a.length()){
            int i = b.length()-a.length();
            for (int j=0; j < i; j++) {
                a.append(0);
            }
        }
        // Loop through the lists, adding each digit and appending to a new linked list, carrying any remainder
        int r = 0;
        for (int i = 0; i < a.length(); i++) {
            int t = (int)a.get(i) + (int) b.get(i) + r;
            r = 0;
            if (t>9){
                t-=10;
                r=1;
            }
            c.append(t);
        }
        // handling if there's a remainder after the last opperation
        if (r == 1){
            c.append(1);
        }
        return c;
    }

    /**
     * mult()
     * @param a
     * @param b
     * @param i
     * @return
     */
    public static LList mult(LList a, LList b, int i){
        LList c = new LList();
        // If at the end of the list return 1
        if (i == a.length()){
            c.append(0);
            return c;
        }
        // Adds zeros to the front of the new linked list equal to the place value we're on
        for (int k = i; k > 0 ; k--) {
            c.append(0);
        }
        // multiplies the digit of the first number equal to the counter int by each digit of the second
        int r = 0;
        for (int j = 0; j < b.length(); j++) {
            int t =((int)a.get(i))*((int)b.get(j)) + r;
            r=0;
            while (t > 9) {
                t -= 10;
                r++;

            }
            // Appends the results of to a new list
            c.append(t);
        }
        if (r>0){
            // adds the remainder to the list if there's still a remainder after the last operation
            c.append(r);
        }
        i++;
        // Returns a recursive call, addition (linked list, multiplication(original list, other original list, digit +1))
        return add(c, mult(a,b,i));
    }

    /**
     * exp()
     * @param a
     * @param b
     * @return
     */
    public static LList exp(LList a, int b){
        if (b==0) {
            //if the exponent is 0, return 1
            LList c = new LList();
            c.append(1);
            return c;
        }else if (b==1) {
            // If the exponent = 1, return the number
            return a;

        } else if (b % 2 == 1){
            // if the exponent is odd, return mult(x, exp(x,n-1))
            b--;
            return mult(a,exp(a,b), 0);
        }
        // otherwise, return mult (exp(x, n/2) exp(x, n/2))
        b /= 2;
        return mult(exp(a, b),exp(a, b), 0);
    }

    /**
     * toString ()
     * Turns an LList into a string for testing purposes
     * @param a
     * @return
     */
    public static String toString(LList a){
        String s = "";
        for (int i = a.length()-1; i > -1; i--) {
            s += a.get(i);
        }
        return s;
    }
}
