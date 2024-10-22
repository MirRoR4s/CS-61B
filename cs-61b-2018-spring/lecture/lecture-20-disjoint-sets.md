# Lecture 20 Disjoint Sets

### 前言

* [视频](https://www.youtube.com/watch?v=WBLsaH26XXQ)
* [PPT](https://docs.google.com/presentation/d/1J7q2RImSbg26vrWMaYQwYo6\_zPDrrdGRmwm\_U2oY20s/edit#slide=id.g5347e2c8f\_2307)
* [文章](https://joshhug.gitbooks.io/hug61b/content/chap9/)：
* [学习指南](https://sp18.datastructur.es/materials/lectures/lec20/lec20)

***



## B level

### Spring 2017 midterm

#### 6

（a）：考察对Weighted Quick Union 算法的掌握，需要理解该算法的运作流程。（小树放在大树下面，树的大小指的是节点的数量）

所以根据这一点，connect 三个节点无论如何都不可能产生一个高度为2的树，从而本题第一小问的答案是 impossible。之后是第二小问，考点一样。

1. connect(2, 0)
2. connect(4, 0)
3. connect(6, 0)
4. connect(5, 3)
5. connect(3, 0)

（b）：本题考察对四种实现的掌握程度，要求更加全面、透彻地理解四种实现的运作流程。

如果不变动现有数据结构（不增加实例变量什么的）四个实现都不可能实现 undo 操作。

（c）：本题尝试用 graph 图来解决 disjoint set 问题，具体的数据结构是无重复项的[邻接表](https://link.zhihu.com/?target=https%3A//www.cnblogs.com/suozhiyuan/p/14100158.html)。采用 DFS 实现 isConnected 操作，并用 addEdge 实现 connect 操作。考察点包括邻接表的含义（这篇[文章](https://algo.itcharge.cn/08.Graph/01.Graph-Basic/02.Graph-Structure/)关于邻接表讲得很不错，还包含有各项操作的运行时间）

1. connect：待定。看了下网上和GPT的，都说连接两点是O(1)的，那么连接N点（最坏情况）就是O(N)的？
2.  isConnected(a, b)：这里需要判断顶点a、b是否相连，根据邻接表的含义，时间复杂度和顶点a的度有关。在最坏的情况下顶点a的度最大，即顶点a和其他所有节点相连，所以此时的时间复杂度是$$\Theta(N)$$



> 看了下题解，本题的答案不太客观，主要是大家对最坏情况的定义不太一致。

***

### [Spring 2016 midterm 2](https://tbp.studentorg.berkeley.edu/exams/5287/download/)

#### 2. WeightedQuickUnionUF

这个问题的关键在于弄清楚WeightedQuickUnion的最好情况和最坏情况是什么。

~~最好情况显然会得到一个高度最低的树，最坏情况显然对应一个高度最高的树。~~

（b）：

最好：$$\Theta(1)$$ 此时所有的节点都不断地和根节点相连

最坏：$$\Theta(lgN)$$

***

绘制出WeightedQuickUnion于最坏情况下在N=4、6、8时算法对应的树

{% file src="../../.gitbook/assets/tree.drawio" %}

1d

<figure><img src="../../.gitbook/assets/image (4).png" alt=""><figcaption></figcaption></figure>

***

## A level

1. 会导致错误，如果将 id\[find(p)] = id\[find(q)] 变成了 id\[find(p)] = id\[q]，那么如果进行 connect(p, q)再进行 isConnected(p,q)，就会得到错误的结果，因为此时 p 的 root 是q，但是q的root是 find(q)。
2. 如果关心树的高度，为什么不用树高来决定树的大小而是要用节点数量呢？看了下网上的一篇[帖子](https://stackoverflow.com/questions/64551449/union-find-why-we-are-checking-size-for-weighted-quick-union)，似乎不用高度是因为操作的难度问题，用高度决定树的大小似乎会导致在路径压缩中追踪树的大小变得困难。
3. 写一个[WeightedQuickUnionPathCompression](https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionPathCompressionUF.java.html)算法
