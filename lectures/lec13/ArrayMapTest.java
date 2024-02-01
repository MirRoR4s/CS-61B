import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayMapTest {
    @Test
    public void testPut() {
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        arrayMap.put(1, "first");
        arrayMap.put(2, "second");
        assertEquals(2, arrayMap.size());
    }
    @Test
    public void testGet() {
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        arrayMap.put(1, "first");
        String ans = arrayMap.get((Integer)1);
        assertEquals("first", ans);
    }

    @Test
    public void testGetKeyIndex() {
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        arrayMap.put(1, "first");
        Integer a = 1;
        int index = arrayMap.getKeyIndex(a);
        assertEquals(0, index);
    }
}
