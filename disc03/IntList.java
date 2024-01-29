public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    @Override
    public boolean equals(Object o) {
        IntList p = this;
        IntList q = (IntList)o;
        while (p != null) {
            if (p.first != q.first) {
                return false;
            }
            p = p.rest;
            q = q.rest;
        }
        return true;
    }

    public static IntList list(int... args) {
        IntList L = null;
        for (int i = args.length - 1; i >=0; i--) {
            L = new IntList(args[i], L);
        }
        return L;
    }

    /**
     * 按照以下规则修改当前链表
     * 遍历一个元素跳过一个节点
     * 遍历两个元素跳过两个节点
     * 遍历三个元素跳过三个节点，以此类推
     */
    public void skippify() {
        IntList p = this;
        int n = 1;
        // while (p != null && p.rest != null) {
        //     IntList next = p.rest;
        //     for (int i = 0; i < n; i++) {
        //         if (next != null) {
        //             next = next.rest;
        //         }
        //         p.rest = next;
        //     }
        //     p = next;
        //     n++;
        // }
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i += 1) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = p.rest;
            n++;
        }
    }

    /**
     * 删除有序链表 p 中所有的重复元素
     * @param p 有序链表（链表中的整数从小到大排列）
     */
    public static void removeDuplicates(IntList p ) {
        if (p == null) {
            return;
        }
        IntList current = p.rest;
        IntList previous = p;
        while (current != null && previous != null) {
            if (current.first == previous.first) {
                previous.rest = current.rest;
            } else {
                previous = current;
            }
            current = current.rest;
        }
    }
}


