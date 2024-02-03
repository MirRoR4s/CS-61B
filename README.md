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

---

### [proj1b](https://sp18.datastructur.es/materials/proj/proj1b/proj1b)

本项目定义了一个双端队列接口 Deque，并用 proj1a 的数组队列和链表队列实现了该接口。此外，还编写了一个根据字符串构建队列的方法以及一个判断字符串是否回文的方法，并在编写这两个方法的实现之前先编写了对应的 Junit Test，这体现了测试驱动开发的思想。

有趣的是，判断回文方法利用到了 Deque 数据结构，而非常规的做法。具体地说，我首先将字符串转为对应的队列，然后编写了一个辅助方法判断队列是否回文。辅助方法的原理是同时在队列首尾出队，判断两个元素是否相等（长度为 0、1 时直接返回 true）。

之后还定义了一个 CharacterComparator 即字符比较器接口，然后用 OffByOne 和 OffByN 两个类实现了该接口的 `equalchars()` 方法，主要就是用不同的规则比较两个字符是否相等，本项目中采用的规则就是两字符的 ascii 码值差。

---

### [Lab 3](https://sp18.datastructur.es/materials/lab/lab3/lab3)

本实验巩固了 Junit 单元测试的基本用法，包括 assertTrue、assertFalse、assertEquals、assertNotEquals 等几个常用测试方法。同时还编写了针对 IntList 数据结构的反转方法 `reverse(IntList x)`，以破坏性地方式反转了一个 IntList 链表。此外，还介绍了 61B 的样式检查器，能够帮我们检验代码样式是否合规。

---

### [proj1gold](https://sp18.datastructur.es/materials/proj/proj1gold/proj1gold)

本项目编写了一个针对 proj1 `ArrayDeque` 的自动评分器。具体的工作原理是随机地调用正确实现和提交者的实现，并根据输出结果是否不同来判断提交者的实现是否正确。编写的自动评分器有价值的一点在于当发现提交者的实现不正确时，会将已调用的操作序列一并输出，这可以给予提交者一些有用的信息。

### lectures

#### [lec13](https://sp18.datastructur.es/materials/lectures/lec13/lec13)

本次讲座的练习一以数组作为核心数据结构实现了带泛型的 `ArrayMap` 类，有 `get(key)`、`put(key, value)` 等常见方法。

在实现中，需要定义两个泛型数组 Keys 和 Values，问题的关键在于需要在 Keys 和 Values 之间建立映射关系。在实现中我们指定某个 Key 在 Keys 中的索引等于其对应的 Value 在 Values 中的索引，通过这种方式，我们就可以获取到 Key 对应的 Value。

之后是一些针对 final 的练习，要注意用 final 关键字声明的变量只能够被赋值一次，第二次赋值会导致编译不通过。

---

### disc

#### disc05

本次讨论旨在用抽象数据类型解决一些现实世界中的问题，包括：

- 计算文章中单词的频率：采用 map 作为数据结构，以单词为 key，单词的出现次数为 value 构建一个映射，在这过程中维护一个变量记录单词总数，最后我们只需要将每个单词的出现次数除以单词总数就可以得到单词的出现频率。
