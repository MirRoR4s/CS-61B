/**
 * @author 黄建涛
 */

public class ArrayDeque<T> implements Deque<T> {
    // 队首元素索引
    private int front;
    // 队尾元素索引 + 1
    private int back;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    private void reSize(int cap) {
        T[] newArray = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(front + i) % items.length];
        }
        items = newArray;
        front = 0;
        back = size;
    }

    /**
     * 将 item 置于队首
     *
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            reSize(size * 2);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size += 1;
    }

    /**
     * 将 item 置于队尾
     *
     * @param item
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            reSize(items.length * 2);
        }
        items[back] = item;
        back = (back + 1) % items.length;
        size += 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空返回 true，否则返回 false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队列大小
     *
     * @return 队列大小
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 以空格为分隔符从头至尾打印队列
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" " + get(i));
            } else {
                System.out.print(get(i));
            }
        }
    }

    /**
     * 删除并返回队首元素
     *
     * @return 原队首元素
     */
    @Override
    public T removeFirst() {
        if (size > 0) {
            T tmp = items[front];
            front = (front + 1) % items.length;
            size = size - 1;
            double usage = (double) size / items.length;
            if (items.length >= 16 && usage < 0.25) {
                reSize(items.length / 2);
            }
            return tmp;
        }
        return null;
    }

    /**
     * 删除并返回队尾元素
     *
     * @return 原队尾元素
     */
    @Override
    public T removeLast() {
        if (size > 0) {
            // 最后一个元素的索引
            int lastIndex = (back - 1 + items.length) % items.length;
            T tmp = items[lastIndex];
            // 删除最后一个元素
            items[lastIndex] = null;
            back = lastIndex;
            size = size - 1;
            double usage = (double) size / items.length;
            if (usage < 0.25 && items.length >= 16) {
                reSize(items.length / 2);
            }
            return tmp;
        }
        return null;
    }

    /**
     * 获取指定 index 索引处的元素
     *
     * @param index 索引
     * @return index 索引处对应的队列元素
     */
    @Override
    public T get(int index) {
        return items[(front + index) % items.length];
    }
}
