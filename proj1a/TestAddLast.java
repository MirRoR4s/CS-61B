public class TestAddLast {
    public static void testAddLast() {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addLast(1);
        l1.addLast(2);
        if (l1.size() == 2) {
            System.out.println("passed length!");
        }
        if (l1.get(0) == 1 && l1.get(1) == 2) {
            System.out.println("passed!");
        }



    }
    public static void main(String[] args) {
        testAddLast();
    }
}
