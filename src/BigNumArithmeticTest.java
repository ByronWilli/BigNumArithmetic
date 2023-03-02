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
        assertEquals(toString(bn.add(a,b)), "100" );
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
        assertEquals(toString(bn.mult(a,b, 0)), "12177" );
    }


}