/**
 * @author 黄建涛
 * 一个双端队列的实现，以下简称 Deque。
 * 双端队列是一个大小可以动态变化的序列容器，可以在其两端插入和删除元素。
 * 一些注意事项：
 * - add 和 remove 操作不得涉及循环或递归，并且所花费的时间与队列的大小无关（这两类操作以常量时间进行）
 * - size 操作必须以常量时间进行
 * - get 必须使用迭代而非递归
 * - 程序在任意给定时间所占内存应和队列的大小成正比
 * - 不要保留已不在队列中的元素的引用
 */
public class LinkedListDeque<T> {

    /**
     * 创建一个空的链表队列
     */
    public LinkedListDeque() {

    }
    
    /**
     * 将 item 加入队列前面
     * @param item
     */
    public void addFirst(T item) {

    }

    /**
     * 将 item 加入队列后面
     * @param item
     */
    public void addLast(T item) {

    }

    /**
     * 判断队列是否为空
     * @return 如果队列为空返回 true，否则返回 false
     */
    public boolean isEmpty() {

        return false;
    }

    /**
     * 获取队列的大小
     * @return 队列大小
     */
    public int size() {
        return 0;
    }

    /**
     * 以空格作为分隔符从头至尾打印队列中的元素
     */
    public void printDeque() {

    }

    /**
     * 删除并返回队首元素
     * @return 队首元素，如果不存在返回 null
     */
    public T removeFirst() {
        return null;
    }

    /**
     * 删除并返回队尾元素
     * @return 队尾元素，如果不存在返回 null
     */
    public T removeLast() {
        return null;
    }

    /**
     * 获取队列第 index 个元素（从零开始）
     * @param index 下标
     * @return 队列第 index 个元素，如果不存在则返回 null
     */
    public T get(int index) {
        // 不得修改队列
        return null;
    }

    /**
     * 与 get 相同，但采用递归实现
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        return null;
    }

    
}
