import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Byron Williamson Ted Stanton
 * CPSC 340 Project 2
 */
public class BigNumArithmeticTest {

    //Tests the process function
    @Test
    public void processTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        File in = new File("test1.out.txt");
        Scanner fileIn = null;
        String output = "";
        try {
            fileIn = new Scanner(in);
            while(fileIn.hasNextLine()){
                output+= fileIn.nextLine();
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

    //Tests the charToInt function with multiple different values. 0,1,9 and a non-relevant value such as x
    @Test
    public void charToIntTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        char zero = '0';
        char one = '1';
        char nine = '9';
        char wrongValue = 'x';
        assertEquals(0,bn.charToInt(zero));
        assertEquals(1,bn.charToInt(one));
        assertEquals(9,bn.charToInt(nine));
        assertEquals(null,bn.charToInt(wrongValue));

    }

    //Test the leadingZeros() function on multiple different LLists
    //Test 1 LList mix = 0,0,0,9,0,5   is changed to 9,0,5
    //Test 2 LList multipleNonZeros = 1,2 stays the same as there are no leading zeros
    //Test 3 LList oneNonZero = 5 stays the same as there are no leading zeros
    //Test 4 oneZero = 0 stays the same, as it is the only Link in LList
    //Test 5 multipleZeros = 0,0,0,0 is changed to 0 all other links are removed
    @Test
    public void leadingZerosTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
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
        bn.leadingZeros(mix);
        assertEquals(3,mix.length());
        assertEquals(9, mix.get(0));
        assertEquals(0, mix.get(1));
        assertEquals(5, mix.get(2));

        //Create a LList to test with only non-zero integers
        LList multipleNonZero = new LList();
        multipleNonZero.append(1);
        multipleNonZero.append(2);
        //Call leadingZeros, test LList's length, there are no leading zeros so nothing in LList should have changed
        bn.leadingZeros(multipleNonZero);
        assertEquals(2,multipleNonZero.length());
        assertEquals(1, multipleNonZero.get(0));
        assertEquals(2, multipleNonZero.get(1));

        //Create a LList to test with one non-zero integer
        LList oneNonZero = new LList();
        oneNonZero.append(5);
        //call leadingZeros, test LList's length, and remaining Link's value
        bn.leadingZeros(oneNonZero);
        assertEquals(1,oneNonZero.length());
        assertEquals(5, oneNonZero.get(0));

        //Create a LList to test with one element containing a 0 integer
        LList oneZero = new LList();
        oneZero.append(0);
        //call leadingZeros, test LList's length, and remaining Link's value
        bn.leadingZeros(oneZero);
        assertEquals(1,oneZero.length());
        assertEquals(0, oneZero.get(0));

        //Create a LList to test with multiple elements containing only 0 integers
        LList multipleZeros = new LList();
        multipleZeros.append(0);
        multipleZeros.append(0);
        multipleZeros.append(0);
        multipleZeros.append(0);
        //call leadingZeros, test LList's length, and the remaining Link's value
        bn.leadingZeros(multipleZeros);
        assertEquals(1,multipleZeros.length());
        assertEquals(0, multipleZeros.get(0));
    }


    //Tests the addition function
    @Test
    public void addTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        LList a = new LList();
        LList b = new LList();
        a.append(9);
        a.append(9);
        b.append(1);
        assertEquals("100", bn.toString(bn.add(a,b)));
    }
    @Test
    public void multTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        LList a = new LList();
        LList b = new LList();
        a.append(9);
        a.append(9);
        b.append(3);
        b.append(2);
        b.append(1);
        assertEquals("12177",  bn.toString(bn.mult(a,b, 0)));
    }
    @Test
    public void expTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        LList a = new LList();
        int b = 5;
        a.append(0);
        a.append(2);
        assertEquals(bn.toString(bn.exp(a,b)), "3200000" );
        b = 4;
        assertEquals(bn.toString(bn.exp(a,b)), "160000" );
        b = 1;
        assertEquals(bn.toString(bn.exp(a,b)), "20" );

    }
}