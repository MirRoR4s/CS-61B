import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @Author mirror4s
 * @Date 2024/2/4
 * @Time 8:56
 */
public class PriorityQueueSortTest {
    @Test
    public void testSort() {
        int[] testArr = new int[]{5, 4, 3, 2, 1};
        int[] result = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(result, PriorityQueueSort.sort(testArr));
    }
    public static void main(String[] args) {

    }
}
