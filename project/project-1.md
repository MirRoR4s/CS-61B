# Project 1

## [Project 1A: Data Structures](https://sp18.datastructur.es/materials/proj/proj1a/proj1a)

基于数组和链表实现双端队列，具备常数时间的首尾出入队、获取长度等功能。

在链表双端队列中，每个节点都有一个前向和后向指针，分别指向其前后节点。同时为了实现上的方便，我利用到了哨兵节点，其位于链表第一个节点之前。同时我采用环形链表的思想，让哨兵节点的前向指针指向链表最后一个节点，同时让链表最后一个节点的后向指针指向哨兵。

在数组双端队列中，我维护了两个整数 `front` 和 `backNext`。front 始终代表队首元素在数组中的索引，backNext 始终代表队尾元素索引的下一个索引。在进行入队操作时，根据队列长度是否已达到数组的长度来动态地调整数组的大小，这也能够确保 front 和 backNext 不会冲突。

* 双端队列的具体实现可以参考[ JDK 源码](https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/util)
