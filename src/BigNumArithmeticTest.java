import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Byron Williamson
 * CPSC 340 Project 2
 */
public class BigNumArithmeticTest {
    @Test
    public void additionTest1(){
        LStack myStack = new LStack();

        int number = 0;
        assertEquals(0, BigNumArithmetic.addition());
    }
}