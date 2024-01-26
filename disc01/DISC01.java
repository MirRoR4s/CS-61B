/**
 * @see https://sp18.datastructur.es/materials/discussion/disc01.pdf
 * @see https://sp18.datastructur.es/materials/discussion/examprep01.pdf
 */
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

    /**
     * 比较难搞的一点是这几个参数的含义是什么？讨论没有给出。。
     * @param n
     * @param k
     * @param f0
     * @param f1
     * @return
     */
    public static int fib2(int n, int k, int f0, int f1) {
        return 0;
    }
}
