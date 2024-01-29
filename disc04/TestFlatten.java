import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlatten {
    @Test
    public void TestFlatten() {
        int[][] arr = new int[][] {{1,2,3}, {}, {7, 8}};
        int[] ans = new int[]{1,2,3,7,8};
        assertArrayEquals(ans, Flatten.flatten(arr));
    }  
}
