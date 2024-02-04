import java.util.PriorityQueue;

/**
 * @Author mirror4s
 * @Date 2024/2/4
 * @Time 8:49
 */
public class PriorityQueueSort {

    /**从小到大排序一个整型数组*/
    public static int[] sort(int[] intArr) {
        int[] result = new int[intArr.length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        for (int i: intArr) {
            priorityQueue.add(i);
        }
        int index = 0;
        while (!priorityQueue.isEmpty()) {
            result[index] = priorityQueue.poll();
            index++;
        }
        return result;
    }
    public static void main(String[] args) {

    }
}
