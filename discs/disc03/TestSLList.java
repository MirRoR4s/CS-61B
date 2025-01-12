public class TestSLList {
    public static void testInsert() {
        SLList a = new SLList(1);
        a.addFirst(2);
        a.addFirst(3);
        a.reverse();
    }
    public static void main(String[] args) {
        testInsert();
    }
}
