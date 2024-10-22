# HW

{% embed url="https://sp18.datastructur.es/materials/lectures/lec18/lec18" %}

### B Level

1. 找出以下程序在n非常大时的运行时间：

```java
 public void print_fib(int n){
   for(int i = 0; i < n; i++i){
       System.out.println(fib(i));
   }
 }

 public int fib(int n){
   if(n <= 0){
     return 0;
   }
   elif(n == 1){
     return 1;
   }
   else{
     return fib(n-1) + fib(n-2);
   }
 }
```

先分析 fib 函数的运行时间，相关资料可参见代码随想录——[递归斐波那契数列的性能分析](https://programmercarl.com/%E5%89%8D%E5%BA%8F/%E9%80%92%E5%BD%92%E7%AE%97%E6%B3%95%E7%9A%84%E6%97%B6%E9%97%B4%E4%B8%8E%E7%A9%BA%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6%E5%88%86%E6%9E%90.html#%E9%80%92%E5%BD%92%E6%B1%82%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0%E5%88%97%E7%9A%84%E6%80%A7%E8%83%BD%E5%88%86%E6%9E%90)。分析得出的结果是 fib 函数的运行时间是 $$2^n$$。递归算法的时间复杂度分析本质就是**递归次数 \* 每次递归的时间复杂度**，在这里 fib 函数每次递归的时间复杂度都是 O(1)，递归次数是 $2^{n-1}-1$。简化就得到了 2^n。

然后结合 fib 分析 print\_fib 函数的运行时间，print\_fib 函数的运行时间就是 所有 fib(i) 函数运行时间之和。fib(i) 的时间复杂度是 O(2^i)，print\_fib 函数的运行时间为&#x20;

$$
O(2^0) + O(2^1) + O(2^2)+...+O(2^{n-1})
$$

这是一个等比数列，运用等比数列求和公式可得 print\_fib 函数的运行时间为$$O(2^n)$$

$$1$$

2. 继续分析以上程序，但将打印语句改为：

```java
System.out.println(fib(n));
```

$$
O(2^1)+2O(2^2)+3O(2^3)+...+(n-1)O(2^n)))
$$

在这个修改后的 `print_fib` 方法中，`fib(n)` 会被调用 `n` 次，每次的复杂度是 `O(2^n)`。因此，总的时间复杂度是 $$O(n\times2^n)$$



3. 找出以下函数的运行时间

```java
 public void melo(int N){
   for(int i = 0; i < N*N; i++){
     System.out.println("Gelo is fruit pudding");
   }
   for(int i = 0; i < N*N*N; i++){
     System.out.println("Zo Two the Warriors");
   }
 }
```

$$N^3$$

4. 找出以下函数的运行时间

```java
 public void grigobreath(int N){
     for(int i  = 0; i < N; i++){
       System.out.println("Gul-great")
     }
     grigobreath(N * 1/2); 
     grigobreath(N * 1/4);
     grigobreath(N * 1/4);
 }
```

一次递归操作的时间复杂度是 $$O(n) + O(3)$$

而递归次数是 $$log_2^n + 2log_4^n$$

首先，我们可以将这个函数的运行时间表示为一个递归关系式。假设函数 `grigobreath(N)` 的运行时间是 `T(N)`，那么根据程序的结构，我们有：

$$
T(N)=N+T(N2)+T(N4)+T(N4)T(N) = N + T\left(\frac{N}{2}\right) + T\left(\frac{N}{4}\right) + T\left(\frac{N}{4}\right)T(N)=N+T(2N​)+T(4N​)+T(4N​)
$$

这表示：

* 首先程序执行了 `N` 次循环操作（复杂度 `O(N)`）。
* 然后它递归地调用了三个 `grigobreath` 函数，分别传递了 `N/2` 和 `N/4` 大小的参数。

**2. 递归树的建立**

我们可以用递归树的方法来分析这段代码的时间复杂度。递归树是一种将递归调用层次化表示的方法。

**第一层（原始调用 `T(N)`）**:

* 运行时间是 `N`。

**第二层（递归调用的子问题）**:

* 一个 `T(N/2)` 和两个 `T(N/4)`，每个的运行时间如上面递归关系式中所示。

**第三层（进一步分解的子问题）**:

* `T(N/2)` 会再分解为 `T(N/4)` 和两个 `T(N/8)`。
* `T(N/4)` 会进一步分解为 `T(N/8)` 和两个 `T(N/16)`，以此类推。

递归树的深度由 `N` 逐渐减小的速率决定。在这种情况下，由于递归调用参数的减小比例为 `N/2` 和 `N/4`，树的高度大约为 `O(\log N)`。

**3. 每层的计算**

在递归树的每一层中，总共有多少计算量？

* **第一层**: `N`
* **第二层**: `N/2 + N/4 + N/4 = N`
* **第三层**: 各个递归部分总和也是 `N`，依此类推。

这意味着每一层的计算量都是 `O(N)`。由于树的高度是 `O(\log N)`，总时间复杂度就是每层复杂度乘以树的高度：

T(N)=O(Nlog⁡N)T(N) = O(N \log N)T(N)=O(NlogN)

#### 结论

程序的总体时间复杂度是 `O(N \log N)`。这种复杂度意味着，当 `N` 增大时，程序的运行时间会以 `N` 乘上 `\log N` 的速度增长。这种增长速度比线性时间 `O(N)` 更快，但比指数时间 `O(2^N)` 要慢得多。
