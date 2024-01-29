import org.junit.Test;
import static org.junit.Assert.*;

public class TestSkippify {

    @Test
    public void testSkippify() {
        IntList A = IntList.list(1,2,3,4,5,6,7,8,9,10);
        A.skippify();
        assertTrue(A.equals(IntList.list(1,3,6,10)));
        IntList B = IntList.list(9,8,7,6,5,4,3,2,1);
        B.skippify();
        assertTrue(B.equals(IntList.list(9,7,4)));
    }

    @Test
    public void testRemoveDuplicates() {
        IntList A = IntList.list(1,2,2,2,3);
        IntList.removeDuplicates(A);
        assertTrue(A.equals(IntList.list(1,2,3)));
    }
}
