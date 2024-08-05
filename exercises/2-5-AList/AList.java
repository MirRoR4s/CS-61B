/*
 * 老师的答案参见：@https://github.com/Berkeley-CS61B/lectureCode-sp17/blob/master/lists4/naive/AList.java
 */
public class AList {

    private int[] array;
    private int size; // 数组当前所含元素个数

    /**
     * 
     * @param cap 数组容量
     */
    public AList(int cap) {
        array = new int[cap];
        size = 0;
    }

    public void addLast(int x) {
        array[size] = x;
        size++;
    }

    public int getLast() {
        return array[size - 1];
    }

    public int get(int index) {
        return array[index];
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        System.out.println("1");
    }
}
