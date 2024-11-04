---
description: 为某个类编写 hashCode 和 equal 方法，并进行相应的测试。
---

# HW 3: Hashing

## 前言

在开始 HW3 之前，首先应该完成以下材料的阅读：

1. [章节12](https://joshhug.gitbooks.io/hug61b/content/chap12/chap121.html)
2. [PPT](https://docs.google.com/presentation/d/1hRUkaONWvWP7IZbINLP-G6uOyyulDqury5kop7638co/edit#slide=id.g3522bc225a\_0\_37)

* [作业地址](https://sp18.datastructur.es/materials/hw/hw3/hw3)

## 原理分析

### Equals 方法的开发

当比较两个对象时，一个符合直觉的想法是如果这两个对象包含的值相等，那么这两个对象就相等。

然而根据Java的规范，一个有效的 equals 方法必须满足以下的性质：

1. 反射性
2. 对称性
3. 传递性
4. 一致性
5. 如果对象 x 不为 null，那么 x.equals(null) 永远为 false

另外还要注意的点是 equals 方法接收一个 Object 类型的参数，所以在比较时要进行类型转换。如果此时不做检查可很能会导致程序崩溃。只有在两个对象的 getClass 方法相等（运行时类型相等）时才可以类型转换。具体的检查操作可以参看[此处](https://algs4.cs.princeton.edu/12oop/Date.java.html)。

### hashCode 方法的开发

在 Java 中，一条很重要的准测是当重写了 equals 方法，也必须要重写 hashCode 方法。这是因为 Java 规定如果 x.equals(y) 为 true，那么 x.hashCode() 应等于 y.hashCode()。

hashCode 方法的用途包括辅助判断一个对象是否位于某个集合中：

```
public void testHashCodeAndEqualsConsistency() {
    SimpleOomage ooA = new SimpleOomage(5, 10, 20);
    SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
    HashSet<SimpleOomage> hashSet = new HashSet<>();
    hashSet.add(ooA);
    assertTrue(hashSet.contains(ooA2)); // 调用 ooA2 的 hashCode，
}
```

