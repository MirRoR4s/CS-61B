/**
 * A double ended queue implementation that support any data type based
 * on linked list. This implementation apply one sentinel and the circular
 * double link to attain fast and elegant addFirst and addLast method.
 */
public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private Node next;
        private T item;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel; // a dummy node that always point to the first node.
    private int size; // the size of this deque.

    /**
     * create a empty deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item to the front of this deque.
     * 
     * @param item
     */
    public void addFirst(T item) {
        Node oldNode = sentinel.next;
        Node newNode = new Node(item, sentinel, oldNode);

        sentinel.next = newNode;
        oldNode.prev = newNode;
        size++;
    }

    /**
     * Adds an item to the back of this deque.
     * 
     * @param item
     */
    public void addLast(T item) {
        Node oldLastNode = sentinel.prev;
        Node newLastNode = new Node(item, oldLastNode, sentinel);

        oldLastNode.next = newLastNode;
        sentinel.prev = newLastNode;
        size++;
    }

    /**
     * return true when this deque is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the size of this deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in this deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }

    /**
     * Removes and returns the item at the front of this deque. If no
     * such item exists, returns null.
     * 
     * @return the item at the front of this deque.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        Node oldFirstNode = sentinel.next;
        Node newFirstNode = oldFirstNode.next;
        sentinel.next = newFirstNode;
        newFirstNode.prev = sentinel;

        size--;
        T item = oldFirstNode.item;
        oldFirstNode = null;
        return item;
    }

    /**
     * Removes and returns the item at the back of this deque. If not
     * such item exists, returns null.
     * 
     * @return the item at the back of this deque.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node oldLastNode = sentinel.prev;
        Node newLastNode = oldLastNode.prev;
        sentinel.prev = newLastNode;
        newLastNode.next = sentinel;

        size--;
        T item = oldLastNode.item;
        oldLastNode = null;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is
     * the next item, and so forth. If no such item exists, returns null.
     * 
     * @param index the index of the item.
     * @return the item at the given index.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * The same as get method, but uses recursion.
     * 
     * @param index the index of the item.
     * @return the item at the given index.
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        }

        return getRecursive(p.next, index - 1);
    }
}
