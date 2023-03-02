import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Byron Williamson
 * CPSC 340 Project 2
 */
public class BigNumArithmeticTest {

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