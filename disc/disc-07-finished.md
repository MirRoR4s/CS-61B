# Disc 07-Finished

{% file src="../.gitbook/assets/disc07.pdf" %}
习题
{% endfile %}

{% file src="../.gitbook/assets/disc07sol.pdf" %}
答案
{% endfile %}

### 1

#### 1.1

$$O(1), O(logn), O(n), O(nlogn),  O(n^2logn), O(n^3), O(2^n), O(n!), O(n^n)$$

#### 1.2

1. ~~false,~~ $$f(n)\in\Omega(g(n))$$
2. false, $$f(n)\in O(g(n))$$
3. true
4. ~~true~~
5. true
6. true
7. false, $$f(n)\in\Omega(g(n)$$

***

### 2 Analyzing Runtime

#### 2.1

最好情况：$$\Theta(N)$$

~~最坏情况：~~$$\Theta(NM)$$

**最坏情况：**$$\Theta(M+N)$$



***

#### 2.2

最好：$$\Theta(NlogN)$$

最坏：$$\Theta(N^2)$$

(a): 检查array数组每个元素是否都存在重复元素，如果有则返回true，没有则返回false。

(b): 利用数组有序的特性，如果数组中有元素出现了多遍，那么这些元素一定是相邻的。

```java
public static boolean mystery(int[] array) {
    array = mrpoolsort(array);
    int N = array.length;
    for (int i = 0; i < N - 1; i += 1) {
        if (array[i] == array[i+1]) {
            return true
        }
    }
    return false;
```

以上程序最好运行时间是 $$\Theta(NlogN)$$，最坏是$$\Theta(N^2logN)$$

如果一个整数至多在数组中出现两次，给出一个仅需常数内存的算法。？？

**使用一个key为元素，value为元素出现次数的map，这样可以在** $$\Theta(n)$$**的时间完成，但是也需要**$$O(n)$$

**的内存。——空间换时间**



***

#### 2.3

最好情况：comeOn() 每次都为 false，此时内层循环执行 $$log_2^M$$次，外层循坏是 N 次，总共是 $$\Theta(NlogM)$$

最坏情况：comeOn() 每次都为 true，此时内层循环执行 M 次，外层是 N次，总共是 $$\Theta(NM)$$

***

### 3 Have you Ever Fast?

#### 3.1

~~(a): 利用数组有序的特性，在外层循环固定 A\[i]，然后利用二分查找搜索 A\[j] = x - A\[i] 是否存在，这样整体的时间复杂度就是 O(NlogN)。~~

```java
    public static boolean findSumFaster(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            if (A[left] + A[right] == x) {
                return true;
            } else if (A[left] + A[right] < x) {
                left++;

            } else {
                right--;
            }
        }
        return false;
    }
```

~~(b): 原始算法是~~ $$O(N^2)$$~~，改进算法是~~ $$O(NlogN)$$

(b): 改进算法在最好情况下的时间复杂是$$\Theta(1)$$，最好情况下的时间复杂度是$$\Theta(N)$$

***

### 4 CTCI Extra

#### 4.1 Union

如何仅有 O(M+N)的时间完成？~~自然想到的可能会是哈希表，用于快速判断元素是否已经在集合中。~~

**使用Java官方的Set抽象数据类型即可**

```java
    public static int[] union(int[] A, int[] B) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        for (int num: B) {
            set.add(num);
        }
        int[] unionArray = new int[set.size()];
        int index = 0;
        for (int num : set) {
            unionArray[index] = num;
            index += 1;
        }
        return unionArray;
    }
```

我想出的平凡算法：

```java
import java.util.ArrayList;
import java.util.List;

public class Union {

    public static int[] union(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
       
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        
        for (int i = 0; i < b.length; i++) {
            if (!list.contains(b[i])) {
                list.add(b[i]);
            }
        }

        // int[] array =  list.toArray(new int[list.size()]);
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;

    }
    public static void main(String[] args) {
        int[] a = new int[] {1,2,3,4};
        int[] b = new int[] {3,4,5,6};
        int[] c = union(a, b);

        for (int i = 0; i < c.length; i++) {
            if (i != 0) {
                System.out.print(" " + c[i]);
            } else {
                System.out.print(c[i]);
            }
        }
    }
}
```

#### 4.2

求出两个数组的交集

Set ADT 有个 retainAll 方法可以用来求交集。

或者也可以跟求并集一样的解法，但是在添加元素时做一个元素是否已在另一个集合中的判断。（这是官方的做法）

以下是我的代码（不是官方的）：

```java
    public static int[] intersect(int[] a, int[] b) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            set1.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            set2.add(b[i]);
        }
        set2.retainAll(set1);
        int[] array = new int[set2.size()];
        int cnt = 0;
        for (int i: set2) {
            array[cnt] = i;
            cnt++;
        }
        return array;
    }
```
