import org.junit.Test;
import static org.junit.Assert.*;

public class TestIntList {

    @Test
    public void testSquare() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.square(L);
        assertEquals(IntList.of(1, 2, 3), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testSquareMutative() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareMutative(L);
        assertEquals(IntList.of(1, 4, 9), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testSquareMutativeRecursively() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareMutativeRecursive(L);
        assertEquals(IntList.of(1, 4, 9), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

}
