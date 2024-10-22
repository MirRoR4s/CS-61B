# Hashing

## Warmup

1. 5 的 hashCode 返回 5
2. 5 对 8 取模还是 5
3. 所以 5 放到索引为 5 的 bucket

0 -> 8

1 -> 25

2 -> 10 -> 18

3 ->

4 ->

5 -> 5

6 ->

7 -> 15


---

## Hashtable Runtimes

1. 在已知 load factor 为 1 或 2 的前提下，问在最坏情况下向 hash table 中添加元素的时间复杂度是多少？

在最坏的情况下，所有的元素都存储在一个 bucket 中，所以：

Bound for load factor 1: $$\Theta(N)$$

Bound for load factor 2: $$\Theta(N)$$

2. 假设哈希函数可将所有元素均匀地映射到各个 bucket 中，问此时在最坏情况下向 hash table 添加元素的时间复杂度？

Bound for load factor 1: $$\Theta(1)$$

Bound for load factor 2: $$\Theta(1)$$

3. 现在不用链表将 bucket 中的元素串联起来，而是改用 bushy BST（哈希函数的好坏不做假设），问在这样的前提下查找操作在最坏情况下的时间复杂度是多少？

Bound: $$\Theta(log^N)$$

4. 和上题一样，但采用一个非常好的哈希函数，问在这样的前提下查找操作最坏情况时间复杂度。

Bound: $$\Theta(1)$$

---

## Zubat

### DynamicString



---

## 来源

- <https://sp18.datastructur.es/materials/discussion/examprep09.pdf>
