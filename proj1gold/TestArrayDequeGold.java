import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        // @source StudentArrayDequeLauncher.java
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a1 = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                a1.addLast(i);
                message += "addLast(" + i + ")\n";
            } else {
                sad1.addFirst(i);
                a1.addFirst(i);
                message += "addFirst(" + i + ")\n";
            }
        }
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer x = sad1.removeFirst();
                Integer y = a1.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, y, x);
            } else {
                Integer x = sad1.removeLast();
                Integer y = a1.removeLast();
                message += "removeLast()\n";
                assertEquals(message, y, x);
            }
        }
    }
    public static void main(String[] args) {

    }
}
