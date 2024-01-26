public class DISC01 {

    /**
     * 返回第 n 个斐波那契数
     * @param n 
     * @return 第 n 个斐波那契数
     */
    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}
