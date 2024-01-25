public class MyArray {
    /**
     * 将 item 插入数组的 position 处
     * 如果 position 超出了数组长度，则将 item 插入数组末尾
     * @param arr 被插入的数组
     * @param item 待插入的元素
     * @param position 待插入的位置
     * @return 插入后的新数组
     */
    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length + 1];
        int min = Math.min(position, arr.length);
        for (int i = 0; i < min; i++) {
            result[i] = arr[i];
        }
        result[position] = item;
        // 注意 i 不可以从 position + 1 开始！
        for (int i = position; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }
        return result;
    }

    /**
     * 反转数组（破坏性方式）
     * @param arr 待反转的数组
     */
    public static void reverse(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size / 2 ; i++) {
            int tmp = arr[i];
            arr[i] = arr[size - i - 1];
            arr[size - i - 1] = tmp;
        }
    }
    
    /**
     * 将 arr 中索引 i 处的元素重复 arr[i] 次并放入一个新数组中（非破坏性）
     * @param arr
     * @return 重复后的新数组
     */
    public static int[] replicate(int[] arr) {
        int sum = 0;
        for (int i: arr) {
            sum += i;
        }
        int[] result = new int[sum];
        int cnt = 0;
        for (int i: arr) {
            for (int j = 0; j < i; j++) {
                result[cnt] = i;
                cnt++;
            }
        }
        return result;
    }
}
