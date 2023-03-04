import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
public class LStackTest {
    /**
     * Tests the storage of items in the lstack by pushing 4 items,
     * ensuring that the size = 4,
     * clearing the list,
     * and testing that the size = 0
     */
    @Test
    public void pushAndClear() {
        LStack l =new LStack(20);
        l.push("t");
        l.push("e");
        l.push("s");
        l.push("t");
        assertEquals(4, l.length());
        l.clear();
        assertEquals(0, l.length());
    }
}
