# CS-61B-2018

我的 CS 61B 之旅，学习的是 18 年的，这一年的作业和项目开放的最齐全。21 年仅开放了一部分。

> 本课程全程需要科学上网，否则会相当卡顿。
> 一个重要的注意事项：网站有时候会跳到19年的学习资料中，此时应该手动将地址栏的19改成18.

---

## 学习资源地址

- [课程介绍](https://sp18.datastructur.es/about.html#auditing-cs61b)
- [课程主页](https://sp18.datastructur.es/)
- [github](https://github.com/Berkeley-CS61B)
- [gradescope](https://www.gradescope.com/)
- [wiki](https://csdiy.wiki/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/CS61B/#_3)

## 环境搭建

新建 git 仓库，然后从 61b 拉取代码。
> 注意冲突问题

## 学习计划

预期两个月完成，从 24 年 1 月 18 日开始，预计 3 月 18 日结束。

---

## 学习总结

### proj1a

本项目基于数组和链表这两种不同的数据结构实现了一个双端队列，具备常量时间的首尾出入队、获取长度等功能。

在链表双端队列中，每个节点都有一个前向和后向指针，分别指向其前后节点。同时为了实现上的方便，我利用到了哨兵节点，其位于链表第一个节点之前。同时我采用环形链表的思想，让哨兵节点的前向指针指向链表最后一个节点，同时让链表最后一个节点的后向指针指向哨兵。

在数组双端队列中，我维护了两个整数 front 和 backNext。front 始终代表队首元素在数组中的索引，backNext 始终代表队尾元素在数组中的索引。在进行入队操作时，根据队列长度是否已达到数组的长度来动态地调整数组的大小，这也能够确保 front 和 backNext 不会冲突。

### [Lab 3](https://sp18.datastructur.es/materials/lab/lab3/lab3)

本实验学习了 Junit 单元测试的基本用法，包括 assertTrue、assertFalse、assertEquals、assertNotEquals 等几个常用测试方法。同时还编写了针对 IntList 数据结构的反转方法 `reverse(IntList x)`，以破坏性地方式反转了一个 IntList 链表。此外，还介绍了 61B 的样式检查器，能够帮我们检验代码样式是否合规。

---

### [proj1gold](https://sp18.datastructur.es/materials/proj/proj1gold/proj1gold)

本项目编写了一个针对 proj1 `ArrayDeque` 的自动评分器。具体的工作原理是随机地调用正确实现和提交者的实现，并根据输出结果是否不同来判断提交者的实现是否正确。编写的自动评分器有价值的一点在于当发现提交者的实现不正确时，会将已调用的操作序列一并输出，这可以给予提交者一些有用的信息。
