import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlatten {
    @Test
    public void testFlatten() {
        int[][] arr = new int[][] {{1,2,3}, {}, {7, 8}};
        int[] ans = new int[]{1,2,3,7,8};
        assertArrayEquals(ans, FlattenDisc04.flatten(arr));
    }  
}
