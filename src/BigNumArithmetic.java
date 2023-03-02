import java.io.*;
import java.util.Scanner;

public class BigNumArithmetic {

    public static void main(String[] args) throws FileNotFoundException {
        //creates a stack
        // reads the file, creating a new linked list when it hits the first non zero digit
        // Appends every sig digit to the linked list as an int
        // Reverses the list and pushes that linked list when it hits any char other than 0-9
        // calls the operation functions when it hits an operator

        //Link myLink = new Link();
        //File inputFile = new File("src/input-file");
        //Initiate LStack And LList objects to go into stack
        LStack myStack = new LStack();
        LList myLList = new LList();
        //For each instance of args do necessary step
        for (int i=0; i<args.length; i++){
            //if there is a new line then push
            if (args[i].equals("\n")){
                myLList.reverse();
            //Every empty space pushes LList to Stack
            } else if (args[i].equals(" ")){
                //check if LList is empty. If so continue to next args[i]
                if(myLList.isEmpty()){
                    continue;
                }else {
                    //LList is not empty, Therefore, reverse & push LList to stack. clear() so new LList can be created.
                    myLList.reverse();
                    myStack.push(myLList);
                    myLList.clear();

                }
            } else {
                //If args[i] is not empty space or new line, add it to the LList
                myLList.append(args[i]);;
            }
        }

    }
    public static LList add(LList a, LList b){
        LList c = new LList();
        // Append zeros to the shorter number until numbers are the same size
        // Loop through the lists, adding each digit and appending to a new linked list, carrying any remainder
        // Handles adding a 1 to the linked list if there's still a remainder after the last operation
        // returns the new linked list
        return c;
    }

    public static LList mult(LList a, LList b, int i){
        LList c = new LList();
        // If curr = null return 0
        // multiplies the digit of the first number equal to the counter int by each digit of the second
        // Appends the results of to a new list
        // adds the remainder to the list if there's still a remainder after the last operation
        // Adds zeros to the front of the new linked list equal to the int
        // Returns a recursive call, addition (linked list, multiplication(original list, other original list, digit +1))
        return c;
    }
    public static LList exp(LList a, LList b){
        LList c = new LList();
        // If the exponent = 1, return the number
        // if the exponent is odd, return mult(x, exp(x,n-1))
        // otherwise, return mult (exp(x, n/2) exp(x, n/2))
        return c;
    }

}
