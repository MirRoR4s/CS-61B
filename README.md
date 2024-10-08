# CS-61B-2018

我的 CS 61B 之旅，学习的是 18 年的，这一年的作业和项目开放的最齐全。21 年仅开放了一部分。

> 本课程全程需要科学上网，否则会相当卡顿。
> 一个重要的注意事项：网站有时候会跳到 19 年的学习资料中，此时应该手动将地址栏的 19 改成 18.

---

## 学习资源地址

- [课程介绍](https://sp18.datastructur.es/about.html#auditing-cs61b)
- [课程主页](https://sp18.datastructur.es/)
- [github](https://github.com/Berkeley-CS61B)
- [gradescope](https://www.gradescope.com/)
- [wiki](https://csdiy.wiki/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/CS61B/#_3)

## 环境搭建

新建 git 仓库，然后从 [61b](https://sp18.datastructur.es/materials/lab/lab2setup/lab2setup) 拉取代码。

---

## 学习总结

### project

#### proj1a

基于数组和链表实现双端队列，具备常数时间的首尾出入队、获取长度等功能。

在链表双端队列中，每个节点都有一个前向和后向指针，分别指向其前后节点。同时为了实现上的方便，我利用到了哨兵节点，其位于链表第一个节点之前。同时我采用环形链表的思想，让哨兵节点的前向指针指向链表最后一个节点，同时让链表最后一个节点的后向指针指向哨兵。

在数组双端队列中，我维护了两个整数 `front` 和 `backNext`。front 始终代表队首元素在数组中的索引，backNext 始终代表队尾元素索引的下一个索引。在进行入队操作时，根据队列长度是否已达到数组的长度来动态地调整数组的大小，这也能够确保 front 和 backNext 不会冲突。

---

#### [proj1b](https://sp18.datastructur.es/materials/proj/proj1b/proj1b)

本项目定义了一个双端队列接口 `Deque`，并用 proj1a 的数组队列和链表队列实现了该接口。此外，还编写了一个根据字符串构建队列的方法以及一个判断字符串是否回文的方法，并在编写这两个方法的实现之前先编写了对应的 Junit Test，这体现了测试驱动开发的思想。

有趣的是，判断回文方法利用到了 Deque 数据结构，而非常规的做法。具体地说，我首先将字符串转为对应的队列，然后编写了一个辅助方法判断队列是否回文。辅助方法的原理是同时在队列首尾出队，判断两个元素是否相等（长度为 0、1 时直接返回 true）。

之后定义了一个字符比较器接口，然后用两个类实现了该接口的 `equalchars()` 方法，用于比较两字符是否相等。（比较规则是两字符的 ascii 码差值）

---

#### [proj1gold](https://sp18.datastructur.es/materials/proj/proj1gold/proj1gold)

本项目编写了一个针对 proj1 `ArrayDeque` 的自动评分器。具体的工作原理是随机调用正确实现和提交者的实现，并比较两者的输出结果来判断提交者的实现是否正确。编写的自动评分器需要在提交者的实现不正确时，将已调用的操作序列反馈给提交者，这可以给予他们一些有用的信息。

---

#### [proj 2, Phase 1](https://sp18.datastructur.es/materials/proj/proj2/proj2)

本项目旨在利用瓦片制作一个二维网格世界，玩家可与世界进行互动。

二维瓦片世界的生成算法参见[此处](https://gamedev.stackexchange.com/questions/82059/algorithm-for-procedural-2d-map-with-connected-paths)

- 阶段一要求生成一个基于瓦片的世界，但是我并不知道该怎样做。

- 使用 tile 引擎绘制 2d 世界
- 世界必须是伪随机生成的
- 生成的世界必须包括房间和走廊，也可以包括室外的空间
- 房间形状不固定，但至少必须有部分房间是矩形
- 世界必须包含转弯的走廊
- 世界应包含随机数量的房间和走廊
- 房间的宽度和高度应是随机的
- 走廊的长度应是随机的
- 房间和走廊的墙壁必须在视觉上和地面区分开来，墙壁和地面应在视觉上和闲置空间区分开来
- 房间和走廊应相互连接，即相邻房间或走廊之间的地板不应有缝隙
- 生成的世界每次都应不一样
- \# 代表墙壁，. 代表地板，金色墙段代表上锁的门

---

### Lab

#### [Lab 3](https://sp18.datastructur.es/materials/lab/lab3/lab3)

本实验巩固了 Junit 单元测试的基本用法，包括 `assertTrue`、`assertFalse`、`assertEquals`、`assertNotEquals` 等几个常用测试方法。同时还编写了针对 IntList 数据结构的反转方法 `reverse(IntList x)`，以破坏性地方式反转 IntList 链表。此外，还介绍了 61B 的样式检查器，能够帮我们检验代码样式是否合规。

---

### [Lab 5](https://sp18.datastructur.es/materials/lab/lab5/lab5)

编写方法实现六边形的绘制，该实验并未涉及到数据结构和算法，是对项目 2 即将用到的 API 进行的实践练习。

> 像这种绘制图形的程序，“看不见并不代表不存在”，看不见的部分或许仅是用黑色进行了填充，但并不代表此处没有东西。

`addHexagon()` 方法用于在世界中绘制一个六边形，解决问题的关键在于需要弄清楚六边形的边长和其所占的总行数、当前行数的边长之间的关系。


---

### lectures

#### [lec13](https://sp18.datastructur.es/materials/lectures/lec13/lec13)

本次讲座的练习一以数组作为核心数据结构实现了带泛型的 `ArrayMap` 类，有 `get(key)`、`put(key, value)` 等常见方法。

在实现中，需要定义两个泛型数组 `Keys` 和 `Values`，问题的关键在于如何在 Keys 和 Values 之间建立映射关系。在实现中我们指定某个 key 在 Keys 中的索引等于其对应的 value 在 Values 中的索引，通过这种方式，我们就可以获取到 key 对应的 value。

之后是一些针对 `final` 的练习，要注意用 `final` 关键字声明的变量只能够被赋值一次，第二次赋值会导致编译不通过。

---

### disc

#### [disc05](https://sp18.datastructur.es/materials/discussion/disc05.pdf)

本次讨论旨在用抽象数据类型解决一些现实世界中的问题，包括：

- 计算文章中所含单词的出现频率：采用 `map` 作为数据结构，以单词为 key，单词的出现次数为 value 构建一个 `map`。最后我们只需将每个单词的出现次数除以单词总数就可以得到每个单词的出现频率。

- 从小到大排序一个整型数组：利用 `PriorityQueue` 优先队列的特性，出队时总弹出最大或最小的元素，此时将其插入新数组的首部或尾部，遵循这个范式最终就可以得到一个排序后的新数组。值得注意的是，我在使用 `Integer` 的优先队列时并未定义优先级规则，但弹出的是最小的元素，所以猜测 Integer 优先队列的优先级规则是最小的元素具备最高优先级。

- 实现 web 浏览器的前进和后退按钮：

    按下后退按钮代表回到当前网页的上一个页面，按下前进按钮则代表去到当前网页的下一个页面。初始时只有一个网页，点击另一个网页相当于**前进操作**。

    定义两个栈，分别用于存储前进和后退按钮对应的网页。每当我们访问一个新的页面（无跳转），就把之前的网页加入到后退按钮对应的栈中。

    当点击后退按钮，就将当前的页面加入前进按钮对应的栈中，并从后退按钮对应的栈中弹出一个网页，这个网页自然就是我们要后退到的网页。

    当点击前进按钮，就将当前的页面加入到后退按钮对应的栈中，并从前进按钮对应的栈中弹出一个网页，这个网页自然就是我们要前进到的网页。

    最后，当我们访问了一个新页面（有跳转），清空前进按钮对应的栈。

- 基于 Java 现有 ADT，实现一个支持双向查找、能返回小于指定 key K 的 key 数量 的 map（ numLessThan 方法）：

    定义两个 map，一个 key 到 value，一个 value 到 key，从而轻松实现双向查找。之后获取 map 的所有 key，和指定 key 比较，在这过程中动态更新数量值即可。要改进的地方在 `numLessThan()` 方法，初代改进版本是先对 keys 排序（从小到大），之后利用二分查找搜索 K，返回其索引，此方法的运行时间主要受排序的影响。更好的改进是动态维护一个已排序的 key 列表。当我们 put 时，要将插入的 key 有序地放入 key 列表中，总之就是要保持 key 列表的有序。之后当我们调用 `numLessThan()` ，如果 K 在 map 中，返回其在 key 列表中的索引。不过还有另一种情况就是 K 并不在 map 中，此时我们要利用二分在 key 列表中查找首个大于等于 K 的 key，并返回其索引。

- 使用 `Stack` 来定义一个 `Queue` 类，实现 ADT 中的 `push()` 和 `poll()` 方法。由于栈是后进先出的，所以用栈实现队列的话，队首元素在栈底。`poll()` 时为了获取队首即栈底，我们可以用一个辅助栈来保存栈底元素之上的所有元素，在弹出栈底元素后再将这些元素放回来。



### [HW 1](https://sp18.datastructur.es/materials/hw/hw1/hw1)

#### Task 1

定义 `BoundedQueue` 接口，仅能在队尾入队，队首出队；若队列已满则不允许任何元素入队。

---

#### Task 2

定义 `AbstractBoundedQueue` 抽象类，实现 `BoundedQueue` 接口，能够做到获取队列容量、队列长度、出队、入队、获取队首元素等。

---

#### Task 3

扩展 `AbstractBoundedQueue`实现环形数组队列 `ArrayRingBuffer`，具备上述所说的功能。

实现思路：

- `first` 存储队首元素索引，`last` 存储队尾元素索引的**下一个**索引。（指向即将插入的元素）

- 入队时，应在 `last` 索引处入队，然后将 `last` 加 1 ；当出队时，将 `first` 索引处的元素取出，并将 `first` 加 1。

- 对于 `last` 和 `first` 的递增操作都要对数组的长度取余。（环形数组逻辑定义）

- 当队列为空调用出队方法或是队列满员调用入队方法时 抛出 `run-time` 异常。

---

#### Task 4: GuitarString

利用 `ArrayRingBuffer` 存储拨弦音对应的数据，最终的效果应是模拟出弹吉他的声音。

作业实用 `Karplus` 算法模拟弦乐器的声音，该算法如下：

1. 用随机的噪音（介于 -0.5 到 0.5 之间的 double）替换 `BoundedQueue` 中的每个 item。
2. 移除 `BoundedQueue` 的队首元素，并将其和当前队首元素相加再除以 2，最后乘以 0.996。
3. `播放`上一步出队的队首元素，并将上一步计算的结果加入队列中
4. 返回第 2 步继续执行

> Karplus-Strong 算法是一种物理建模合成的方法，主要用于模拟弦乐器的声音，如吉他和钢琴。它是由 Kevin Karplus 和 Alex Strong 在 1983 年提出的。

---

#### Task 5: Iteration and Exceptions

增加迭代 `BoundedQueue` 的功能，并确保在输入无效时抛出异常。

> 关于如何为一个自定义类增加迭代功能，参看[此处](https://docs.google.com/presentation/d/1LIz15B3VmMMhllz1t5HNo9gkOPTGgmAynHNEt6Rzng0/edit#slide=id.g11a6f75162_0_250)

---

