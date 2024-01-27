/** 基于数组的双端队列实现
 * 1. 常量时间增删（调整大小除外）
 * 2. 常量时间获取指定索引元素
 * 3. 常量时间获取长度
 * 4. 数组的起始长度为 8
 * 5. 程序所用内存与项数成正比，对于长度 16 以上的数组，其使用率至少应该在 25 % 以上
 * @author 黄建涛
*/
public class ArrayDeque<T> {
    // 指向队首元素的位置
    private int front;
    // 指向队尾元素的下一个位置
    private int back;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    /**
     * 修改数组的总长度
     * @param cap 修改后数组的总长度
     */
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
     * @param item
     */
    public void addFirst(T item) {
        if (size == items.length) {
            reSize(size * 2);
        }
        front = (front -1 + items.length) % items.length;
        items[front] = item;
        size += 1;
    }

    /**
     * 将 item 置于队尾
     * @param item
     */
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
     * @return 为空返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队列大小
     * @return 队列大小
     */
    public int size() {
        return size;
    }

    /**
     * 以空格为分隔符从头至尾打印队列
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" " + get(i));
            }
            else {
                System.out.print(get(i));
            }
        }
    }

    /**
     * 删除并返回队首元素
     * @return 原队首元素
     */
    public T removeFirst() {
        if (size > 0) {
            T tmp = items[front];
            front = (front + 1) % items.length;
            size = size - 1;
            return tmp;
        }
        return null;
    }

    /**
     * 删除并返回队尾元素
     * @return 原队尾元素
     */
    public T removeLast() {
        if (size > 0) {
        // 最后一个元素的索引
        int lastIndex = (back - 1 + items.length) % items.length;
        T tmp = items[lastIndex];
        // 删除最后一个元素
        items[lastIndex] = null;
        back = lastIndex;
        size = size - 1;
        return tmp;
        }
        return null;
    }

    /**
     * 获取指定 index 索引处的元素
     * @param index 索引
     * @return  index 索引处对应的队列元素
     */
    public T get(int index) {
        return items[(front + index) % items.length];
    }
}
