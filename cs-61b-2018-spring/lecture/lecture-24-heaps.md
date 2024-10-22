# Lecture 24 Heaps

* [PPT](https://docs.google.com/presentation/d/1ySYTxnvoHJc7\_2U0L90WH3kx0toWA4vpNiIR2r1vqKU)

## 简介

本节课主要学习以下内容：

* 优先队列 Priority queues
* 堆 heap
* 树的表示
* 数据结构总结

## 优先队列

优先队列即队列中的元素具备一定的优先级或顺序（递增或者递减）的队列，如果是递增顺序，那么则为最小优先队列，否则则为最大优先队列。本节课希望对以下的最小优先队列接口进行实现：

```java
public interface MinPQ<Item> {
    public void add(Item x); // 向优先队列中添加元素
    public Item getSmallest(); // 获取队列中的最小元素
    public Item removeSmallest(); // 删除队列中的最小元素
    public int size(); // 获取队列的大小
}
```

### 应用场景

假如你是幸福委员会的成员，负责监视市民谈话以查看他们是否有不和谐的交谈。你每天需要写一份报告汇报当天最不和谐的 M 条消息。

一个平凡的解决方案是用一个列表存储当天的所有消息，然后排序列表返回前 M 条最不和谐的消息。这个方案的缺点在于**内存消耗过多**——正比于消息的数量。

```java
public List<String> unharmoniousTexts(Sniffer sniffer, int M) {
    ArrayList<String> allMessages = new ArrayList<String>();
    
    for (Timer timer = new Timer(); timer.hours() < 24; ) {
         allMessages.add(sniffer.getNextMessage());   
    }
    
    Comparator<String> cmptr = new HarmoniousnessComparator();
    Collections.sort(allMessage, cmptr, Collections.reverseOrder());
    return allMessage.sublist(0, M);
```

一个更好的解决方案是利用上述提到的**优先队列**。我们可以动态地维护一个优先队列，每收到一个消息就向队列中插入，这里的关键在于当队列的长度超过了 M，就移除掉队列中最小（最和谐）的元素。

```java
public List<String> unharmoniousTexts(Sniffer sniffer, int M) {
    Comparator<String> cmptr = new HarmoniousnessComparator();
    MinPQ<String> unharmoniousTexts = new HeapMinPQ<Transaction>(cmptr);
    for (Timer timer = new Timer(); timer.hours() < 24; ) {
    unharmoniousTexts.add(sniffer.getNextMessage());
        if (unharmoniousTexts.size() > M) 
           { unharmoniousTexts.removeSmallest(); }
   }
   ArrayList<String> textlist = new ArrayList<String>();
   While (unharmoniousTexts.size() > 0) {
           textlist.add(unharmoniousTexts.removeSmallest());
   }
   return textlist;
```

***

## 堆

下面描述实现优先队列的一些数据结构：

1. 有序列表（ordered list）：删除最小元素过慢
2. 稠密的BST（bushy BST）：难以处理相同优先级的元素和保持稠密
3. 哈希表（hash table）：查找、删除最小元素过慢

<figure><img src="../../.gitbook/assets/image (4) (1).png" alt=""><figcaption></figcaption></figure>

这就引入了一种新的数据结构——堆（heap）：

堆实际上是一个满足以下两种性质的二叉树：

1. 每个节点小于/大于等于其子节点。（该性质是递归的，所以**根节点**是树中**最小/最大**的节点，对应的堆也称为最小堆和最大堆）
2. 完全的（complete），叶节点只会出现在树的底部。如果树不是满的，那么叶节点总依靠在左边。

<figure><img src="../../.gitbook/assets/image (5).png" alt=""><figcaption><p>堆的示例，绿色的是堆，红色的不是</p></figcaption></figure>

堆的这些性质使得它可以很自然地用来实现优先队列，下面介绍基于最小堆的最小优先队列接口的具体实现，详细的讲解可以参看[此处](https://docs.google.com/presentation/d/1VEd2Pm\_3OuvkC1M8T5XAhsBTQFxVHs386L79hktkDRg/pub?start=false\&loop=false\&delayms=3000\&slide=id.g11ecaeaf56\_0\_0)

### 获取最小节点

根据堆的性质，树的根节点就是最小节点。

### 添加节点

暂时将待添加节点放入堆的末尾，之后一步步上浮该节点直至其处于正确的位置。

### 删除最小节点

将最后一个节点和根节点交换，然后删除原有根节点，之后一步步下沉最后一个节点，直至该节点处于正确的位置。

***

## 树的表示

因为堆是完全的（complete），所以我们只需要用一个数组存储 keys，而无需存储与树结构相关的信息。

```java
public class Tree3<Key> {
    Key[] keys;
}
```

> 注意该方法仅在树是完全的情况下才有效。

<figure><img src="../../.gitbook/assets/image (10).png" alt=""><figcaption></figcaption></figure>

根据以上的表示，写出对应的上浮方法 swim：

```java
public void swim(int k) { // k 是节点在数组中的索引
    if (keys[parent(k)] > keys[k]) {
        swap(k, parent(k));
        swim(parent(k));
    }
}

```

所以在当前的结构下如何根据节点的索引找到其父节点的索引？

```java
public int parent(int k) {
    return (k - 1) / 2;
}
```

最后还可以稍微优化一下上面的表示，具体地说，我们让节点的索引从1开始，0则空着。

<figure><img src="../../.gitbook/assets/image (11).png" alt=""><figcaption></figcaption></figure>

这样就可以使得获取节点的左/右子节点以及父节点变得更加自然：



<figure><img src="../../.gitbook/assets/image (12).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (13).png" alt=""><figcaption></figcaption></figure>
