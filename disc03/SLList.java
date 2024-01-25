public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /**
     * 在链表头部插入 x
     * @param x
     */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }


    /**
     * 将 item 插入列表的第 position 个位置，如果 position 超过了链表的长度，
     * 那么将 item 插入到链表末尾
     * @param item 待插入的数
     * @param position 列表位置
     */
    public void insert(int item, int position) {
        // 如果链表为空或要插入的位置在链表头部
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        // 遍历链表，找到 position 之前那个节点并指向它
        while (position > 1 && currentNode.next != null) {
            position--;
            currentNode = currentNode.next;
        }
        // 创建新节点，新节点的下一个节点指向原先位于 position 处的节点，最后将原先 position 之前的那个节点指向新节点
        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }

    /**
     * 翻转当前链表，不要使用 new
     */
    public void reverse() {
        IntNode frontOfReversed = null;
        IntNode nextNodeToAdd = first;
        while (nextNodeToAdd != null) {
            // 用一个节点存储当前节点的下一个节点
            IntNode remainderOfOriginal = nextNodeToAdd.next;
            // 将当前节点下一个节点置空
            nextNodeToAdd.next = frontOfReversed;
            // 指向当前节点
            frontOfReversed = nextNodeToAdd;
            // 将当前节点指向原始的下一个节点
            nextNodeToAdd = remainderOfOriginal;
        }
        first = frontOfReversed;


    }
}
