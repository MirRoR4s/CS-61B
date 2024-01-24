public class TestAddFirst {

    public static void testAddFirst() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        System.out.println(l1.size());
    }
    public static void main(String[] args) {
        testAddFirst();
    }
}
