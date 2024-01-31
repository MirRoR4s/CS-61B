public class IntList {
    private int item;
    private IntList next;

    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    public static IntList of(int... args) {
        IntList L = null;
        for (int i = args.length - 1; i >= 0; i--) {
            L = new IntList(args[i], L);
        }
        return L;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IntList)) {
            return false;
        }
        IntList other = (IntList) o;
        IntList p = this, q = other;
        while (p != null && q != null) {
            if (p.item != q.item) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return p == null && q == null;
    }

    /**
     * 将旧链表中所有元素平方并以此返回一个新的链表（非破坏性）
     * 
     * @param L 原链表
     * @return 平方后的新链表
     */
    public static IntList square(IntList L) {
        if (L.next == null) {
            return new IntList(L.item * L.item, null);
        }
        return new IntList(L.item * L.item, square(L.next));
    }

    /**
     * 将旧链表中每个元素平方然后返回（破坏性）
     * 
     * @param L 原链表
     * @return 平方后的新链表
     */
    public static IntList squareMutative(IntList L) {
        IntList p = L;
        while (p != null) {
            p.item = p.item * p.item;
            p = p.next;
        }
        return L;
    }

    /**
     * 平方链表元素（破坏性-递归版）
     * 
     * @param L
     * @return
     */
    public static IntList squareMutativeRecursive(IntList L) {
        if (L.next == null) {
            L.item = L.item * L.item;
            return L;
        }
        L.item = L.item * L.item;
        squareMutativeRecursive(L.next);
        return L;
    }
}
