/**
 * @author Jian Tao Huang
*/
public class LinkedListDeque<T> {
    private class IntNode {
        private IntNode prev;
        private IntNode next;
        private T item;

        public IntNode(T item, IntNode prev, IntNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /*
     * 哨兵节点 sentinel
     * 哨兵节点的下一个节点始终指向队列的第一个节点
     */
    private IntNode sentinel;
    // 记录链表长度
    private int size;

    /**
     * 创建一个空的链表队列
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        // 当链表为空时，prev 和 next 都指向哨兵节点自身。
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        // 创建节点，其 prev 指向哨兵，其 next 指向哨兵的 next
        IntNode p = new IntNode(item, sentinel, sentinel.next);
        // 将原先头节点的 prev 改为 p
        sentinel.next.prev = p;
        // 将哨兵节点的 next 设为 p，
        sentinel.next = p;
        size++;
    }

    /**
     * 将 item 置于队尾
     * 
     * @param item
     */
    public void addLast(T item) {
        IntNode p = new IntNode(item, sentinel.prev, sentinel);
        // 将旧队尾节点指向新队尾节点
        sentinel.prev.next = p;
        // 将哨兵节点的 prev 指向 p
        sentinel.prev = p;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 以空格作为分隔符从头至尾打印队列中的元素
     */
    public void printDeque() {
        IntNode p = sentinel.next;
        while (p != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        // 获取头节点
        IntNode p = sentinel.next;
        // 将哨兵指向头节点后一个节点
        sentinel.next = p.next;
        // 将第二个节点的 prev 改为哨兵
        p.next.prev = sentinel;
        size--;
        return p.item;
    }

    /**
     * 删除并返回队尾元素
     * 
     * @return 队尾元素，如果不存在返回 null
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ans = sentinel.prev.item;
        // 将哨兵指向倒数第二个节点
        sentinel.prev = sentinel.prev.prev;
        // 将倒数第二个节点的 next 改为哨兵
        sentinel.prev.next = sentinel;
        size--;
        return ans;
    }

    /**
     * 获取队列第 index 个元素（从零开始）
     * 
     * @param index 下标
     * @return 队列第 index 个元素，如果不存在则返回 null
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        // 不得修改队列
        IntNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * 与 get 相同，但采用递归实现
     * 
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }
}
