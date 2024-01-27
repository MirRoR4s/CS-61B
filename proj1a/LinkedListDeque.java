/**
 * @see https://sp18.datastructur.es/materials/proj/proj1a/proj1a
 * @see https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html
 * @author Huang Jian Tao
 */
public class LinkedListDeque<T> {
    /*
     * 一个环型双端队列实现，以下简称 Deque。双端队列是一个大小可以动态变化的序列容器，可以在其两端插入和删除元素。
     * 环形双端队列代表链表的最后一个元素的 next 指向哨兵节点，哨兵节点的 next 指向第一个元素，哨兵节点的 prev 指向最后一个元素。
     * 一些注意事项：
     * add 和 remove 操作不得涉及循环或递归，并且所花费的时间与队列的大小无关（这两类操作以常量时间进行）
     * size 操作必须以常量时间进行
     * get 必须使用迭代而非递归
     * 程序在任意给定时间所占内存应和队列的大小成正比
     * 不要保留已不在队列中的元素的引用
     */
    // 哨兵节点
    private IntNode sentinel;
    // 记录链表长度
    private int size;

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

    /**
     * 创建一个空的链表队列
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        // 当链表为空时，prev 和 next 都指向哨兵节点自身。
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 将 item 置于队首
     * 
     * @param item
     */
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

    /**
     * 判断队列是否为空
     * 
     * @return 如果队列为空返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队列长度
     * 
     * @return 队列长度
     */
    public int size() {
        return size;
    }

    /**
     * 以空格作为分隔符从头至尾打印队列中的元素
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(this.get(i));
        }
    }

    /**
     * 删除并返回队首元素
     * 
     * @return 队首元素，如果不存在返回 null
     */
    public T removeFirst() {
        if (size > 0) {
            // 获取头节点
            IntNode p = sentinel.next;
            // 将哨兵指向头节点后一个节点
            sentinel.next = p.next;
            // 将第二个节点的 prev 改为哨兵
            p.next.prev = sentinel;
            // 长度减一
            size--;
            return p.item;
        }
        return null;
    }

    /**
     * 删除并返回队尾元素
     * 
     * @return 队尾元素，如果不存在返回 null
     */
    public T removeLast() {
        if (size > 0) {
            T ans = sentinel.prev.item;
            // 将哨兵指向倒数第二个节点
            sentinel.prev = sentinel.prev.prev;
            // 将倒数第二个节点的 next 改为哨兵
            sentinel.prev.next = sentinel;
            size--;
            return ans;
        }
        return null;
    }

    /**
     * 获取队列第 index 个元素（从零开始）
     * 
     * @param index 下标
     * @return 队列第 index 个元素，如果不存在则返回 null
     */
    public T get(int index) {
        // 不得修改队列
        IntNode p = sentinel;
        int cnt = 0;
        while (p.next != null && cnt <= size) {
            if (cnt == index) {
                return p.next.item;
            }
            p = p.next;
            cnt++;
        }
        return null;
    }

    /**
     * 与 get 相同，但采用递归实现
     * 
     * @param index
     * @return
     */
    public T getRecursive(int index) {

        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }
}
