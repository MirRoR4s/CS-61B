import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Union {

    public static int[] union(int[] A, int[] B) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        for (int num: B) {
            set.add(num);
        }
        int[] unionArray = new int[set.size()];
        int index = 0;
        for (int num : set) {
            unionArray[index] = num;
            index += 1;
        }
        return unionArray;
    }   

    /**
     * 
     * @param A a sorted array.
     * @param x
     * @return
     */
    public static boolean findSum(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            // find x - A[i]
            if (bSearch(A, A[i] - x)) {
                return true;
            }
        }
        return false;
    }

    private static boolean bSearch(int[] A, int target) {
        int low = 0;
        int hight = A.length;

        int mid = (low + hight) / 2;

        while (mid < A.length) {
            if (mid > target) {
                hight = mid - 1;
            } else if (mid < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int[] union(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        for (int i = 0; i < b.length; i++) {
            if (!list.contains(b[i])) {
                list.add(b[i]);
            }
        }

        // int[] array = list.toArray(new int[list.size()]);
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;

    }

    public static int[] intersect(int[] a, int[] b) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            set1.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            set2.add(b[i]);
        }
        set2.retainAll(set1);
        int[] array = new int[set2.size()];
        int cnt = 0;
        for (int i : set2) {
            array[cnt] = i;
            cnt++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 4 };
        int[] b = new int[] { 3, 4, 5, 6 };
        int[] c = union(a, b);
        int[] d = intersect(a, b);

        for (int i = 0; i < d.length; i++) {
            if (i != 0) {
                System.out.print(" " + d[i]);
            } else {
                System.out.print(d[i]);
            }
        }
    }
}