import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author Jian Tao Huang
 * @Date 2/4/24 1:51 PM
 * @Version 1.0
 */
public class MyQueueTest {
    @Test
    public void testPush() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
    }

    @Test
    public void testPoll() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        assertEquals(1, queue.pop());
        queue.push(2);
        queue.push(3);
        assertEquals(2, queue.pop());
    }
    public static void main(String[] args) {

    }
}
