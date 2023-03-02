import java.io.*;
import java.util.Scanner;

public class BigNumArithmetic {

    public static void main(String[] args) throws FileNotFoundException {
        //creates a stack
        // reads the file, creating a new linked list when it hits the first non zero digit
        // Appends every sig digit to the linked list as an int
        // Reverses the list and pushes that linked list when it hits any char other than 0-9
        // calls the operation functions when it hits an operator


        LStack myStack = new LStack();
        //Link myLink = new Link();
        File inputFile = new File("src/input-file");
        Scanner fileIn = new Scanner(inputFile);
        //while (fileIn.hasNext()){
          //  char
            //Link myLink = new Link(fileIn);

            //myStack.push(fileIn);
        //}
        }


    public static LList add(LList a, LList b){
        LList c = new LList();
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
            // Handles adding a 1 to the linked list if there's still a remainder after the last operation
            if (t>9){
                t-=10;
                r=1;
            }
            c.append(t);
        }
        if (r == 1){
            c.append(1);
        }
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
