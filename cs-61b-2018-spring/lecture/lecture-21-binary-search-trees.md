# Lecture 21 Binary Search Trees

## 前言

接下来开始要进入一些高级数据结构的学习了，在那之前建议先看一下这篇[综述](https://joshhug.gitbooks.io/hug61b/content/chap10/chap101.html)以对这些数据结构支持的操作有一个基本的了解。

这节课学习的内容如下：

这节课学习 BST（Binary Search Tree），了解如何在 BST 中查询、插入、删除元素。

* [文章](https://joshhug.gitbooks.io/hug61b/content/chap10/chap102.html)
* [PPT](https://docs.google.com/presentation/d/1rEHpAx8Xu2LnJBWsRPWy8blL20qb96Q5UhdZtQYFkBI/edit?usp=sharing)
* [视频](https://www.youtube.com/watch?v=OvIg\_6SREVA)
* [习题](https://sp18.datastructur.es/materials/lectures/lec21/lec21)

BST 是符号表（Symbol Table）的一种实现方式之一。BST 的想法主要来源于希望在**有序链表中**像**有序数组**那样进行快速的搜索操作**。**

> 符号表是一种支持对键值对（key-value pair）进行搜索和插入的数据结构

***

## BST 定义

在介绍 BST 二叉搜索树之前，需要先了解最基本的树（tree）是什么。

树由两部分组成：

1. 节点
2. 连接这些节点的边（注意**任意两个节点之间仅能有一条路径**）

<figure><img src="../../.gitbook/assets/image (14).png" alt=""><figcaption></figcaption></figure>

上图绿色的部分是树，红色的则不是。

> 没有子节点的节点称为叶节点（leaf node）
>
> 位于树的顶部没有父节点的节点称为根节点（root node）

在基本的树结构上再施加一些约束，就可以得到二叉树BT和二叉搜索树BST。

二叉树：树中的每个节点只能有 0 个或者 1 个或者 2 个子节点

**二叉搜索树 BST**：在二叉树的基础之上，对每个节点 X 来说，X 的左子树的每个节点的 key 都小于 X 的 key，同时 X 的右子树的每个节点的 key 都大于 X 的 key。

> 注意 BST 的性质使得树中不允许出现重复的 key

<figure><img src="../../.gitbook/assets/image (15).png" alt=""><figcaption></figcaption></figure>

下面是一个 BST 的简单代码：

```java
private class BST<Key> {
    private Key key;
    private BST left;
    private BST right;

    public BST(Key key, BST left, BST Right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BST(Key key) {
        this.key = key;
    }
}
```

***

## BST 操作

### 搜索

下面介绍如何在 BST 中快速搜索指定元素 X ：

1. 从树的根节点开始，将 X 与根节点相比较（比较指的是用节点的 Key 的值与 X 相比较）
2. 如果 X 大于根节点，则移动到根节点的右子节点
3. 如果 X 小于根节点，则移动到根节点的左子节点
4. 重复以上的过程，如果最后转移到了某个叶节点，则说明树中不存在元素 X，否则我们会停留在 Key 等于 X 的节点上。

搜索的简单代码如下（找到则返回以对应的 Key 为根节点的 BST）：

```java
static BST find(BST T, Key sk) {
   if (T == null)
      return null;
   if (sk.equals(T.key))
      return T;
   else if (sk ≺ T.key)
      return find(T.left, sk);
   else
      return find(T.right, sk);
}
```

如果树相对来说比较稠密（bushy）的话，那么搜索操作的时间复杂度是 log(n)，因为树的高度是 logn。所以 BST 的搜索操作是非常快的！

***

### 插入

在 BST 中插入节点也十分简单，因为我们总会以叶节点为父节点进行插入操作。

具体地说，当我们在 BST 中插入元素 X 时，我们首先在树中寻找它。如果 X 已存在于树中，则无需做任何操作。如果 X 不存在于树中，那么此时我们一定位于某个叶节点上，此时只需将 X 作为该节点的左子节点或右子节点即可完成插入操作。

以下是插入操作的简单代码（返回插入新节点后的树）：

```java
static BST insert(BST T, Key ik) {
  if (T == null)
    return new BST(ik);
  if (ik ≺ T.key)
    T.left = insert(T.left, ik);
  else if (ik ≻ T.key)
    T.right = insert(T.right, ik);
  return T;
}
```

### 删除

在 BST 中删除节点则略显复杂，因为我们需要维护 BST 的性质。可以将删除分成三种情况：

* 要删除的节点没有子节点：将指向该节点的指针置空，随后该节点就会被 Java 的垃圾收集器自动回收。

<figure><img src="../../.gitbook/assets/image (16).png" alt=""><figcaption><p>删除key为glut的节点</p></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (17).png" alt=""><figcaption></figcaption></figure>

* 要删除的节点有 1 个子节点：将指向该节点的指针指向该节点的子节点

<figure><img src="../../.gitbook/assets/image (18).png" alt=""><figcaption><p>删除key为flat的节点</p></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (19).png" alt=""><figcaption><p>将指针指向elf</p></figcaption></figure>

* 要删除的节点有两个子节点：这种情况是最复杂的，因为不能够像上面那样将指向节点的指针指向节点的子节点，（这可能会破坏 BST 的性质）所以我们需要通过某种方法决定出一个新的节点来替换要删除的节点。

> 一般来说，这个新节点要么是被删除节点的左子树中最大的节点，要么是被删除节点的右子树中最小的节点，这样的删除手法称为 **Hibbard deletion**

> 注意替换的时候要将新节点从原有位置删去（**Hibbard deletion 可以保证删除该新节点的情况一定是情况 1 或者 2，也就是该新节点只会有 0 个或者 1 个子节点）**

<figure><img src="../../.gitbook/assets/image (23).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (24).png" alt=""><figcaption></figcaption></figure>

## BST 应用

可以用 BST 实现 Set，这比起用 Array 实现的 Set 性能要更好，因为 ArraySet 的 Contains 方法在最坏情况下的时间复杂度是 O(n)（因为要遍历整个数组），而 BSTSet 则是 O(logn)

还可以用 BST 实现 Map，只需让节点存储键值对而非单纯的值就可以。

## 习题

### C level

1. BST 在最好、最坏情况下的高度是什么？

最好是 logN，最坏是 N（比如连续插入一个递增的序列，此时树的高度是线性增加的）

可参考PPT的这[一页](https://docs.google.com/presentation/d/1rEHpAx8Xu2LnJBWsRPWy8blL20qb96Q5UhdZtQYFkBI/edit#slide=id.g75707c75c\_0169)。

2. BST在随机情况下的高度大概率是  logN ，为什么不混淆输入数据以避免最坏情况的发生？
3. 用给定序列`A X C S E R H` 构造BST，画出最好情况下的图形

<figure><img src="../../.gitbook/assets/image (2) (1).png" alt=""><figcaption></figcaption></figure>

4. 删除上题BST的根节点

<figure><img src="../../.gitbook/assets/image (3) (1).png" alt=""><figcaption></figcaption></figure>



### B Level

1. 在一个key为1到10的BST中寻找5对应的搜索序列是？

答案是 b. 4, 10, 8, 7, 53

2. 待定
3. 待定
4. 从BST从删除根节点时用以替代根节点的节点为什么总有0个或1个子节点？

因为替代节点要么是左子树的最大节点，要么是右子树的最小节点，这个性质使得替代节点不可能是拥有两个子节点的节点。

5. 删除操作是否满足交换律？比如先删除y再删除x能否得到和先删除x再删除y一样的BST？

好像是满足的？

6. 在完全BST中搜索的时间复杂度是 $$\Theta(logN)$$，在每个节点只有0个或者1个子节点的BST中搜索的时间复杂度是$$\Theta(N)$$，问在每个节点只有0个或2个子节点的BST（一个general 的BST，可能也会是完全的BST）中搜索操作在最坏和最好情况下的时间复杂度是什么？

最好情况下这个BST就是完全BST，此时搜索操作的时间复杂度是 $$\Theta(logN)$$

最坏情况下这个BST$$\Theta(N)$$



### A+ Level

