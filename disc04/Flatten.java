public class Flatten {
    /**
     * 将二维数组x中的元素串联起来并返回
     * @param x 二维数组
     * @return 包含串联元素的一维数组
     */
    public static int[] flatten(int[][] x) {
        // 遍历二维数组求总长度
        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                totalLength++;
            }
        }
        // 根据总长度定义新数组
        int[] a =  new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                a[aIndex] = x[i][j];
                aIndex++;
            }
        }
        return a;
    }
}
