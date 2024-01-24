public class TestGet {
    public static void testGet() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        if (l1.get(0) == 2 && l1.get(1) == 1) {
            System.out.println("passed!");
        }
        if (l1.get(l1.size()) == null) {
            System.out.println("passed null!");
        }
    }

    public static void testGetRecursive() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        if (l1.getRecursive(0) == 2 && l1.getRecursive(1) == 1) {
            System.out.println("passed!");
        }
        if (l1.getRecursive(l1.size()) == null) {
            System.out.println("passed null!");
        }
    }

    public static void main(String[] args) {
        // testGet();
        testGetRecursive();
    }
}
