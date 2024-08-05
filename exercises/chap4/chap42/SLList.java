public class SLList<Item> {

    private class IntNode {
        public Item item;
        public IntNode next;

        public IntNode(Item item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(null, null);
    }

    public SLList(Item x) {
        sentinel = new IntNode(x, new IntNode(x, null));
        size = 1;
    }

    public void addFirst(Item x) {
        IntNode first = sentinel.next;
        first = new IntNode(x, first);
        size++;
    }

    public void addLast(Item x) {
        IntNode p = sentinel.next; // 头节点
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size += 1;
    }

    public int size() {
        return size;
    }

    public Item removeLast() {
        IntNode first = sentinel.next;
        int cnt = 0;
        while (first != null) {
                cnt += 1;
                if (cnt == size) {
                    return first.item;
                }
                first = first.next;
        }
        return null;
    }

    public void print() {
        IntNode p = sentinel.next;
        while (p != null) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }
}