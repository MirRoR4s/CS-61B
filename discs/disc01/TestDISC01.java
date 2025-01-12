import org.junit.Test;
import static org.junit.Assert.*;

public class TestDISC01 {

    @Test
    public void testFib() {
        assertEquals(0, DISC01.fib(0));
        assertEquals(1, DISC01.fib(1));
        assertEquals(1, DISC01.fib(2));
        assertEquals(2, DISC01.fib(3));
        assertEquals(3, DISC01.fib(4));
        assertEquals(5, DISC01.fib(5));
    }

}
