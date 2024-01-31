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
            }
            else {
                Integer x = sad1.removeLast();
                Integer y = a1.removeLast();
                message += "removeLast()\n";
                assertEquals(message, y, x);
            }
        }

//        for (int i = 0; i < 10; i += 1) {
//            StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();
//            ArrayDequeSolution<Integer> arrayDeque = new ArrayDequeSolution<>();
//            double numberBetweenZeroAndOne = StdRandom.uniform();
//            String message = "";
//            if (numberBetweenZeroAndOne < 0.5) {
//                studentArrayDeque.addFirst(5);
//                studentArrayDeque.addLast(3);
//                studentArrayDeque.removeFirst();
//                Integer x = studentArrayDeque.removeLast();
//                studentArrayDeque.addLast(1);
//
//                arrayDeque.addFirst(5);
//                arrayDeque.addLast(3);
//                arrayDeque.removeFirst();
//                Integer y = arrayDeque.removeLast();
//                studentArrayDeque.addLast(1);
//                assertEquals("addFirst(5)\naddLast(3)\nremoveFirst()\nremoveLast()\naddLast(1)", y, x);
//            } else {
//                studentArrayDeque.addLast(5);
//                message += "addLast(5)\n";
//                arrayDeque.addLast(5);
//                studentArrayDeque.addFirst(3);
//                arrayDeque.addFirst(3);
//                message += "addFirst(3)\n";
//                Integer x = studentArrayDeque.removeLast();
//                message += "removeLast()\n";
//                Integer y = arrayDeque.removeLast();
//                assertEquals(message, y, x);
//                x = studentArrayDeque.removeFirst();
//                message += "removeFirst()\n";
//                y = arrayDeque.removeFirst();
//                assertEquals(message, y, x);
//            }
//        }


    }
    public static void main(String[] args) {

    }
}
