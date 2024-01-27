import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        int ans = ad1.get(0);
        assertEquals(3, ans);
        ans = ad1.get(1);
        assertEquals(2, ans);
        ans = ad1.get(2);
        assertEquals(1, ans);
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        int ans = ad1.get(0);
        assertEquals(1, ans);
        ans = ad1.get(1);
        assertEquals(2, ans);
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        int ans = ad1.removeLast();
        assertEquals(3, ans);
        ans = ad1.removeLast();
        assertEquals(2, ans);
        ans = ad1.removeLast();
        assertEquals(1, ans);
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        int ans = ad1.removeFirst();
        assertEquals(1, ans);
        ans = ad1.removeFirst();
        assertEquals(2, ans);
        ans = ad1.removeFirst();
        assertEquals(3, ans);
    }



}
