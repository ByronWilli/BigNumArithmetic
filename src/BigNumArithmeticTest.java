import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Byron Williamson
 * CPSC 340 Project 2
 */
public class BigNumArithmeticTest {
    //Turns an LList into a string for testing purposes
    public String toString(LList a){
        String s = "";
        for (int i = a.length()-1; i > -1; i--) {
            s += a.get(i);
        }
        return s;
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
        assertEquals("100", toString(bn.add(a,b)));
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
        assertEquals("12177",  toString(bn.mult(a,b, 0)));
    }
    @Test
    public void expTest(){
        BigNumArithmetic bn = new BigNumArithmetic();
        LList a = new LList();
        int b = 5;
        a.append(0);
        a.append(2);
        //assertEquals(toString(bn.exp(a,b)), "3200000" );
        b = 4;
        assertEquals(toString(bn.exp(a,b)), "160000" );
        b = 1;
        assertEquals(toString(bn.exp(a,b)), "20" );

    }

}