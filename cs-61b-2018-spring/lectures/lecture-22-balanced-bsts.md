# Lecture 22 Balanced BSTs

* [PPT](https://docs.google.com/presentation/d/1j7kiMfMzmhdi6AoKSuYdZr65iH7ckeWMKGQAyI4M61c/edit#slide=id.g465b5392c\_00)
* [文章](https://joshhug.gitbooks.io/hug61b/content/chap11/)

***

## 前言

BST 在树比较高时可能会有性能问题（特别是按序插入元素导致树变得很瘦长时），所以为了保持良好的性能，需要尽可能地让树保持**平衡**。

对于一颗 BST，可以通过旋转（rotation）的方式使其变得平衡。具体地旋转方式包括左旋（）和右旋。（）

尽管通过旋转保持平衡的想法很美好，但在实际操作时具体该采用哪种旋转方式是不明显的。所以能否在不旋转的情况下也能维持平衡呢？这就引入了其他类型的搜索树。（search tree）

***

## B-tree/ 2-3 tree/ 2-3-4 tree

首先一种简单的想法是在插入时不添加新的叶节点，而是直接将待插入元素放入当前位置上的叶节点中（但是如果插入的位置没有节点的话，就新建一个叶节点之间插入）。

<figure><img src="../../.gitbook/assets/image (28).png" alt=""><figcaption><p>在节点 <strong>p</strong> 的位置上插入节点 q</p></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (27).png" alt=""><figcaption><p>插入 <strong>q</strong></p></figcaption></figure>

这样一来寻找某元素的操作就分为了两步：

1. 找到该元素所在的节点
2. 遍历该节点的元素列表

显然地，当元素列表过长时会导致搜索操作变得很慢，所以这种方法在实际中不可取。

对应地解决办法是**为元素列表设置一个最大容量**，当节点的元素个数超过了这个最大容量，则将该节点的一个元素分给其父节点。（具体分哪个元素呢？此处是位于**左中**位置的那个元素）

比如下图设置元素列表的最大容量为 **3**：

<figure><img src="../../.gitbook/assets/image (31).png" alt=""><figcaption><p>节点 p q r s 超过最大容量</p></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (30).png" alt=""><figcaption><p>将 q 元素分给父节点</p></figcaption></figure>

但是上面的操作又导致了新的问题，那就是位于 o 和 q 之间的元素没有位置插入了，解决该问题的办法就是**允许节点拥有超过 2 个子节点**。

具体地说，当节点超容时，我们首先将节点的左中元素放入其父节点中（放入时注意保持次序），然后再将节点分裂成两个同级的新节点（第一个新节点包含原节点第一个元素，第二个新节点则包含原节点的剩余元素），之后再让节点的父节点指向这两个新节点。这样一来，父节点就拥有了 **3** 个子节点。

<figure><img src="../../.gitbook/assets/image (32).png" alt=""><figcaption><p>拥有三个子节点的树</p></figcaption></figure>

现在如果想在树中搜索元素 r，则可按以下步骤进行：

1. r > m，右移到 o q
2. r > o，与 q 比较
3. r > q，右移到 r s

因为现在每个节点所能包含的元素个数是有限的，所以该方法能够保证所有操作的性能是 $$\Theta(log_N)$$

上面所讲的树具备完美的平衡，并且可以观察到以下性质：

1. 分裂根节点，树高加 1
2. 分裂其他节点，树高不变

上方所讲的树称为 **B-tree**。具体地说，我们把树中任意一个节点至多只会拥有 3 个子节点并且节点至多只会包含 2 项的树称为 **2-3 tree。**



<figure><img src="../../.gitbook/assets/image (25).png" alt=""><figcaption><p>2-3 tree</p></figcaption></figure>

把树中任意一个节点至多只会拥有 4 个子节点并且节点至多只会包含 3 项的树称为 **2-3-4 tree**。

<figure><img src="../../.gitbook/assets/image (26).png" alt=""><figcaption><p>2-3-4 tree</p></figcaption></figure>

尽管 B 树在平衡性方面特别完美，但是其实现起来过于麻烦并且也会遭受性能问题，所以又引出了一种新的树——红黑树（red black tree）

## Red-Black Tree

红黑树的思想就是**将 B 树用二叉搜索树 BST 来表示**。

具体地说，我们想要构建一个在结构上跟 B 树完全相同（称为 isometric 等距？）的 BST。

*   当 B 树所有节点仅包含 1 个元素，此时 B 树 和 BST 相同，无需做任何操作。

    <figure><img src="../../.gitbook/assets/image (33).png" alt=""><figcaption></figcaption></figure>
* 当 B 树有节点包含多个元素，此时需分裂该节点

<figure><img src="../../.gitbook/assets/image (34).png" alt=""><figcaption></figcaption></figure>

> 红黑树的名称或许就来源于上图的红色链接和黑色链接

与 B 树具备等距关系的 BST 具备以下性质：

1. 没有节点拥有两个红色链接
2. 从根节点到叶节点的每条路径都拥有相同数量的黑色链接（黑色链接的数量至多为树高）
3. 红色链接总指向左子节点

满足以上性质的 BST 就被称为 **left leaning red black binary search tree**（LLRB）

<figure><img src="../../.gitbook/assets/image (35).png" alt=""><figcaption></figcaption></figure>

如果说 B 树的树高是 h，那么在**最坏情况**下在其对应的 BST 中搜索元素所需的比较次数是 2h。最坏情况意味着要搜索的元素位于叶节点的位置上，并且从根节点到该叶节点的黑色链接路径上的所有节点都包含两个元素，此时将其转为 BST 会导致高度翻倍。
