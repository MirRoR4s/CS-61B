public class TestPrintDeque {

    public static void testPrintDeque() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        l1.printDeque();
    }
    public static void main(String[] args) {
        testPrintDeque();
    }
}
