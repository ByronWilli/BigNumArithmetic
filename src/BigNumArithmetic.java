import java.io.*;
import java.util.Scanner;

public class BigNumArithmetic {

    public static void main(String[] args) {
        try {
            System.out.println(process(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file");
        }

    }

    public static String process(String s) throws FileNotFoundException {
        //creates a stack
        // reads the file, creating a new linked list when it hits the first non zero digit
        // Appends every sig digit to the linked list as an int
        // Reverses the list and pushes that linked list when it hits any char other than 0-9
        // calls the operation functions when it hits an operator

        //Initiate LStack And LList objects to go into stack
        LStack myStack = new LStack();
        LList myLList = new LList();
        //create a String of that will be updated to print expression details
        String expressionDetails = "";
        //Set the file path to command line input args[0]
        File inputFile = new File(s);
        Scanner fileIn = new Scanner(inputFile);
        //While file is not empty get next string and use value to determine the action to take
        while (fileIn.hasNext()){
            String newInput = fileIn.next();
            //If there is a new line then push
            if (newInput.equals("\n")){
                myLList.reverse();
            //Every empty space pushes LList to Stack
            } else if (newInput.equals(" ")){
                //check if LList is empty. If so continue to next file input
                if(myLList.isEmpty()){
                    continue;
                } else {
                    //LList is not empty, Therefore, reverse & push LList to stack. clear() so new LList can be created.
                    myLList.reverse();
                    myStack.push(myLList);
                    myLList.clear();

                }
            //newInput being an addition sign triggers add() method. pop top 2 LLists from stack to use in add function
            } else if (newInput.equals("+")){
                LList first = (LList) myStack.pop();
                LList second = (LList) myStack.pop();
                expressionDetails += second.toString() + " + " + first.toString() + " ";
                //call add() on both LLists and push result back into stack;
                myStack.push(add(first, second));
            } else if(newInput.equals("*")) {
                LList first = (LList) myStack.pop();
                LList second = (LList) myStack.pop();
                int x = 0;
                //call mult() on both LLists and push result back into stack;
                myStack.push(mult(first, second , x));
                expressionDetails += second.toString() + " * " + first.toString() + " ";
            }else if (newInput.equals("^")){
                LList first = (LList) myStack.pop();
                LList second = (LList) myStack.pop();
                //converts LList to an int
                first.moveToStart();
                int expNum = 1;
                int finalNum = 0;
                while(first.getValue()!=null){
                    finalNum += ((int)first.getValue() * expNum);
                    expNum *= 10;
                    first.next();
                }
                //call exp() on both LLists and push result back into stack;
                myStack.push(exp(second, finalNum));
                expressionDetails += second.toString() + " * " + first.toString() + " ";
            } else {
                //If newInput is not " ", "+", "*", or "^", then it should be a number.
                //If value is not a leading 0 in number, add it to an Integer and append it to myLList
                if (myLList.length() == 0 && newInput.equals("0")){
                    continue;
                } else {
                    int x = strToInt(newInput);
                    myLList.append(x);
                }
            }
        }
        expressionDetails += "= " + myStack.topValue().toString();
        //System.out.println(expressionDetails);
    return expressionDetails;
    }
    //this function turns the String value read from file to an Integer.
    public static int strToInt(String x) {
        if (x.equals("0")) {
            return 0;
        } else if (x.equals("1")){
            return 1;
        } else if (x.equals("2")){
            return 2;
        }else if (x.equals("3")){
            return 3;
        } else if (x.equals("4")){
            return 4;
        }else if (x.equals("5")){
            return 5;
        } else if (x.equals("6")){
            return 6;
        }else if (x.equals("7")){
            return 7;
        } else if (x.equals("8")){
            return 8;
        }else if (x.equals("9")){
            return 9;
        } else {
            return 0;
        }
    }
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
            // Handles adding a 1 to the linked list if there's still a remainder after the last operation
            if (t>9){
                t-=10;
                r=1;
            }
            c.append(t);
        }
        // handling if there's a remainder
        if (r == 1){
            c.append(1);
        }
        return c;
    }

    public static LList mult(LList a, LList b, int i){
        LList c = new LList();
        // If at the end of the list return 1
        if (i == a.length()){
            c.append(0);
            return c;
        }
        // Adds zeros to the front of the new linked list equal to the int
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
    public static LList exp(LList a, int b){
        LList c = new LList();
        if (b==1) {
            // If the exponent = 1, return the number
            return a;
        }
        // if the exponent is odd, return mult(x, exp(x,n-1))
        if (b % 2 == 1){
            b--;
            return mult(a,exp(a,b), 0);
        }
        // otherwise, return mult (exp(x, n/2) exp(x, n/2))
        b /= 2;
        return mult(exp(a, b),exp(a, b), 0);
    }
    //Turns an LList into a string for testing purposes
    public static String toString(LList a){
        String s = "";
        for (int i = a.length()-1; i > -1; i--) {
            s += a.get(i);
        }
        return s;
    }
}
