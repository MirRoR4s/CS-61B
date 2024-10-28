package hw3.hash;

import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
  public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
    /* TODO:
     * Write a utility function that returns true if the given oomages
     * have hashCodes that would distribute them fairly evenly across
     * M buckets. To do this, convert each oomage's hashcode in the
     * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
     * and ensure that no bucket has fewer than N / 50
     * Oomages and no bucket has more than N / 2.5 Oomages.
     */

    /*
       1. 声明并初始化一个长度为 M 的数组，数组中的元素类型是由 Oomage 构成的链表
       2. 遍历 oomages 求解每个 oomage 对应的数组索引并将 oomage 放入索引处
       3. 遍历数组，判断每个链表的长度是否合规
    */
    LinkedList<Oomage>[] buckets = new LinkedList[M];
    for (int i = 0; i < M; i++) {
      buckets[i] = new LinkedList<>();
    }
    for (Oomage o : oomages) {
      int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
      buckets[bucketNum].add(o);
    }
    int N = oomages.size();
    for (LinkedList<Oomage> bucket : buckets) {
      if (bucket.size() < N / 50 || bucket.size() > N / 2.5) {
        return false;
      }
    }
    return true;
  }
}
