public class TestRemoveFirst {
    public static void testRemoveFirst() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addLast(1);
        int first = l1.removeFirst();
        if (first == 1 && l1.size() == 0) {
            System.out.println("passed");
        }
    }
    public static void main(String[] args) {
        testRemoveFirst();
    }
}
