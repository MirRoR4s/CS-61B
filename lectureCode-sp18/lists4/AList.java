/** Array based list.
 *  @author Josh Hug
 */

public class AList<T> {
    private T[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (T[]) new object[100];
        size = 0;
    }

    private void reSize(int cap) {
        int[] a = new int[size * cap];
        System.arraycopy(items, 0, a, 0, size);
        item = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            reSize(size + 1);
        }
        item[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];     
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;    
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int tmp = getLast();
        items[size - 1] = null;
        size -= 1;
        return tmp;
    }
} 
