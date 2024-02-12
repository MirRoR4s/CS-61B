package synthesizer;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import javax.management.RuntimeErrorException;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test(expected = java.lang.RuntimeException.class)
    public void testEuqueue() {
        ArrayRingBuffer<Integer> arrayRingBuffer = new ArrayRingBuffer(3);
        arrayRingBuffer.enqueue(1);
        assertEquals(1, arrayRingBuffer.fillCount());
        arrayRingBuffer.enqueue(2);
        arrayRingBuffer.enqueue(3);
        arrayRingBuffer.enqueue(4);
    }

    @Test(expected = java.lang.RuntimeException.class)
    public void testDequeue() {
        ArrayRingBuffer<Integer> arrayRingBuffer = new ArrayRingBuffer(3);
        arrayRingBuffer.enqueue(1);
        assertEquals(1, (int)arrayRingBuffer.dequeue());
        assertEquals(0, arrayRingBuffer.fillCount());
        arrayRingBuffer.dequeue();
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arrayRingBuffer = new ArrayRingBuffer<>(3);
        arrayRingBuffer.enqueue(1);
        arrayRingBuffer.enqueue(2);
        arrayRingBuffer.enqueue(3);
        for (int i: arrayRingBuffer) {
            System.out.println(i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
