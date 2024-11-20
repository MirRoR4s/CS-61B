/**
 * An deque implementation based on array.
 */
public class ArrayDeque<T> {
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            a[i] = items[(nextFirst + i) % items.length];
        }

        items = a;
        nextFirst = 0;
        nextLast = size;
    }

    private int leftShift(int a) {
        return Math.floorMod(a - 1, items.length);
    }

    private int rightShift(int a) {
        return Math.floorMod(a + 1, items.length);
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        size++;

        nextFirst = leftShift(nextFirst);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        size++;

        nextLast = rightShift(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" " + get(i));
            } else {
                System.out.print(get(i));
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextFirst = rightShift(nextFirst);
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        size--;

        double usage = (double) size / items.length;
        if (items.length >= 16 && usage < 0.25) {
            resize(items.length / 2);
        }

        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = leftShift(nextLast);
        T tmp = items[nextLast];
        items[nextLast] = null;
        size--;

        double usage = (double) size / items.length;
        if (usage < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return tmp;
    }

    public T get(int index) {
        index = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[index];
    }
}
