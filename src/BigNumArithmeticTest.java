import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.Assert.*;
/**
 * BigNumArithmeticTest contains multiple test functions for all functions created in the BigNumArithmetic class
 * @author Byron Williamson Ted Stanton
 * @version 2023-03-03
 */
public class BigNumArithmeticTest {
    /**
     * This tests the entire program from the main() method in BigNumArithmeticTest.java
     * Reads expected output from "test1.out.txt" and print each line to user.
     * Calls main() and uses the file "input_file" as a String[] args parameter
     * main() automatically prints out the result when called.
     * Compare the 2 results printed, to ensure program is running correctly.
     * All values printed should be exactly the same.
     */
    @Test
    public void mainTest() {
        BigNumArithmetic bn = new BigNumArithmetic();
        //create a String[] which can be used as parameter for calling main()
        String[] args = new String[1];
        String filename = "input-file";
        //set args[0] to "input-file" this eventually will be passed int main()
        args[0] = filename;
        //Read lines from expected output file "test1.out.txt"
        File in = new File("test1.out.txt");
        Scanner fileIn;
        String output = "";
        try {
            fileIn = new Scanner(in);
            while (fileIn.hasNextLine()) {
                output += fileIn.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find output test file");
        }
        //Print out the expected output from "test1.out.txt"
        System.out.println("Expected results from Test1.out.txt: \n\n" + output);
        //Print out the actual output from main
        System.out.println("Actual results from main(): \n");
        //Call main() and compare the outputs
        bn.main(args);
    }
    /**
     * Tests the process function
     */
    @Test
    public void processTest() {
        BigNumArithmetic bn = new BigNumArithmetic();
        File in = new File("test1.out.txt");
        Scanner fileIn;
        String output = "";
        try {
            fileIn = new Scanner(in);
            while(fileIn.hasNextLine()){
                output += fileIn.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find output test file");
        }

        try {
            assertEquals(bn.process("input-file"), output);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find input test file");
        }
    }

    /**
     * This tests the process() on a file that has empty lines and numbers with leading zeros
     */
    @Test
    public void processTest2() {
        BigNumArithmetic bn = new BigNumArithmetic();
        File in = new File("test2-out");
        Scanner fileIn;
        String output = "";
        try {
            fileIn = new Scanner(in);
            while(fileIn.hasNextLine()){
                output += fileIn.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find output test file");
        }

        try {
            assertEquals(bn.process("test2-in"), output);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find input test file");
        }
    }
    /**
     * This tests the process() on a completely empty file. Verifies no errors are thrown.
     * The output should equal what is in the emptyTest-in.txt file which also contains nothing.
     */
    @Test
    public void processTest3() {
        BigNumArithmetic bn = new BigNumArithmetic();
        File in = new File("emptyTest-out.txt");
        Scanner fileIn;
        String output = "";
        try {
            fileIn = new Scanner(in);
            while(fileIn.hasNextLine()){
                output += fileIn.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find output test file");
        }

        try {
            assertEquals(bn.process("emptyTest-in.txt"), output);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find input test file");
        }
    }

    /**
     * Tests the charToInt function with multiple different values. 0,1,9 and a non-relevant value such as x
     */
    @Test
    public void charToIntTest() {
        char zero = '0';
        char one = '1';
        char nine = '9';
        char wrongValue = 'x';
        //This test should return 0 from char'0'
        assertEquals(0, BigNumArithmetic.charToInt(zero));
        //This test should return 1 from char'1'
        assertEquals(1, BigNumArithmetic.charToInt(one));
        //This test should return 9 from char'9'
        assertEquals(9, BigNumArithmetic.charToInt(nine));
        //This test should return null from char'x'
        assertEquals(null, BigNumArithmetic.charToInt(wrongValue));
    }

    /**
     * Test the leadingZeros() function on multiple different LList
     * Test 1 LList mix = 0,0,0,9,0,5   is changed to 9,0,5
     * Test 2 LList multipleNonZeros = 1,2 stays the same as there are no leading zeros
     * Test 3 LList oneNonZero = 5 stays the same as there are no leading zeros
     * Test 4 oneZero = 0 stays the same, as it is the only Link in LList
     * Test 5 multipleZeros = 0,0,0,0 is changed to 0 all other links are removed
     */
    @Test
    public void leadingZerosTest() {
        //create a LList to test with a mix of 0's and non-zero integers.
        LList mix = new LList();
        mix.append(0);
        mix.append(0);
        mix.append(0);
        mix.append(9);
        mix.append(0);
        mix.append(5);
        //Call leadingZeros, test LList's length, and remaining 3 Link values
        //This should only remove the first 3 zeros. because those are the only leading zeroes in LList.
        BigNumArithmetic.leadingZeros(mix);
        assertEquals(3,mix.length());
        assertEquals(9, mix.get(0));
        assertEquals(0, mix.get(1));
        assertEquals(5, mix.get(2));

        //Create a LList to test with only non-zero integers
        LList multipleNonZero = new LList();
        multipleNonZero.append(1);
        multipleNonZero.append(2);
        //Call leadingZeros, test LList's length, there are no leading zeros so nothing in LList should have changed
        BigNumArithmetic.leadingZeros(multipleNonZero);
        assertEquals(2,multipleNonZero.length());
        assertEquals(1, multipleNonZero.get(0));
        assertEquals(2, multipleNonZero.get(1));

        //Create a LList to test with one non-zero integer
        LList oneNonZero = new LList();
        oneNonZero.append(5);
        //call leadingZeros, test LList's length, and remaining Link's value
        BigNumArithmetic.leadingZeros(oneNonZero);
        assertEquals(1,oneNonZero.length());
        assertEquals(5, oneNonZero.get(0));

        //Create a LList to test with one element containing a 0 integer
        LList oneZero = new LList();
        oneZero.append(0);
        //call leadingZeros, test LList's length, and remaining Link's value
        BigNumArithmetic.leadingZeros(oneZero);
        assertEquals(1,oneZero.length());
        assertEquals(0, oneZero.get(0));

        //Create a LList to test with multiple elements containing only 0 integers
        LList multipleZeros = new LList();
        multipleZeros.append(0);
        multipleZeros.append(0);
        multipleZeros.append(0);
        multipleZeros.append(0);
        //call leadingZeros, test LList's length, and the remaining Link's value
        BigNumArithmetic.leadingZeros(multipleZeros);
        assertEquals(1, multipleZeros.length());
        assertEquals(0, multipleZeros.get(0));
    }

    /**
     * Tests the add() function within BigNumArithmetic.java.
     * when a '+' value is read from the file add() pops the top 2 LLists and adds them together.
     * The result is then returns as a String using toString() on the LList returned
     */
     @Test
    public void addTest() {
        BigNumArithmetic bn = new BigNumArithmetic();
        //create 2 LLists to be added
        LList a = new LList();
        LList b = new LList();
        //append values to LLists
        a.append(9);
        a.append(9);
        b.append(1);
        //Test add() using the 2 LLists as parameters. LList a = 9,9 + LList b = 1
        //This should return 100.
        assertEquals("100", bn.toString(bn.add(a, b)));
    }

    /**
     * This Tests the mult() function within BigNumArithmetic.java
     * when a '*' value is read from the file add() pops the top 2 LLists and multiplies them together.
     * The result is then returns as a String using toString() on the LList returned
     */
    @Test
    public void multTest() {
        BigNumArithmetic bn = new BigNumArithmetic();
        LList a = new LList();
        LList b = new LList();
        a.append(9);
        a.append(9);
        b.append(3);
        b.append(2);
        b.append(1);
        assertEquals("12177",  bn.toString(bn.mult(a, b, 0)));
    }

    /**
     * This Tests the mult() function within BigNumArithmetic.java
     * when a '^' value is read from the file add() pops the top 2 LLists and exponentiation them together.
     * The result is then returns as a String using toString() on the LList returned
     */
    @Test
    public void expTest() {
        BigNumArithmetic bn = new BigNumArithmetic();
        //create a LList and integer, which simulates the value from another LList
        LList a = new LList();
        //create an integer, which simulates the value from another LList. a * 10^b
        int b = 5;
        //populate LList a
        a.append(0);
        a.append(2);
        //call exp() with (LList a and int b) as parameters. 20 *10^5
        //This should return 3200000 as a String
        assertEquals(bn.toString(bn.exp(a, b)), "3200000" );
        //Test 2 change int b = 4
        b = 4;
        //call exp() with (LList a and int b) as parameters. 20 *10^4
        //This should return 160000 as a String
        assertEquals(bn.toString(bn.exp(a, b)), "160000" );
        //Test 3 change int b =1
        b = 1;
        //call exp() with (LList a and int b) as parameters. 20 *10^1
        //This should return 20 as a String
        assertEquals(bn.toString(bn.exp(a, b)), "20" );
    }

}