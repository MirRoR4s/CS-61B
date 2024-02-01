/**
 * 基于数组的双端队列实现
 * 1. 常量时间增删（调整大小除外）
 * 2. 常量时间获取指定索引元素
 * 3. 常量时间获取长度
 * 4. 数组的起始长度为 8
 * 5. 程序所用内存与项数成正比，对于长度 16 以上的数组，其使用率至少应该在 25 % 以上
 *
 * @author 黄建涛
 */

public class ArrayDeque<T> {
    // 指向队首元素的位置
    private int front;
    // 指向队尾元素的下一个位置
    private int backNext;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        backNext = 0;
    }

    /**
     * 修改数组的总长度
     *
     * @param cap 修改后数组的总长度
     */
    private void reSize(int cap) {
        T[] newArray = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(front + i) % items.length];
        }
        items = newArray;
        front = 0;
        backNext = size;
    }

    /**
     * 将 item 置于队首
     *
     * @param item
     */
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
    public void addLast(T item) {
        if (size == items.length) {
            reSize(items.length * 2);
        }
        items[backNext] = item;
        backNext = (backNext + 1) % items.length;
        size += 1;
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
        T tmp = items[front];
        front = (front + 1) % items.length;
        size = size - 1;
        double usage = (double) size / items.length;
        if (items.length >= 16 && usage < 0.25) {
            reSize(items.length / 2);
        }
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        // 最后一个元素的索引
        int lastIndex = (backNext - 1 + items.length) % items.length;
        T tmp = items[lastIndex];
        // 删除最后一个元素
        items[lastIndex] = null;
        backNext = lastIndex;
        size = size - 1;
        double usage = (double) size / items.length;
        if (usage < 0.25 && items.length >= 16) {
            reSize(items.length / 2);
        }
        return tmp;
    }

    public T get(int index) {
        return items[(front + index) % items.length];
    }
}
