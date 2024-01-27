public class Deque {
    private int[] arr;
    private int front;
    private int back;
    private int size;

    public Deque() {
        arr = new int[8];
        front = 0;
        back = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        int[] newArr = new int[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(front + i) % arr.length];
        }
        arr = newArr;
        front = 0;
        back = size;
    }

    public void pushFront(int val) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        front = (front - 1 + arr.length) % arr.length;
        arr[front] = val;
        size++;
    }

    public void pushBack(int val) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        arr[back] = val;
        back = (back + 1) % arr.length;
        size++;
    }

    public int popFront() {
        if (size == 0) {
            throw new RuntimeException("Deque is empty");
        }
        int val = arr[front];
        front = (front + 1) % arr.length;
        size--;
        if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return val;
    }

    public int popBack() {
        if (size == 0) {
            throw new RuntimeException("Deque is empty");
        }
        back = (back - 1 + arr.length) % arr.length;
        int val = arr[back];
        size--;
        if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return val;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[(front + index) % arr.length];
    }
}
