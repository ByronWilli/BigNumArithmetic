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
            System.out.println("Cannot find test1.out.txt test file");
        }

        try {
            System.out.println(bn.process("test1.txt"));
            System.out.println(output);
            assertEquals(bn.process("test1.txt"), output);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find test1.txt test file");
        }
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