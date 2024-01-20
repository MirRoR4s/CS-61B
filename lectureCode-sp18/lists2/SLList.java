 /** An SLList is a list of integers, which hides the terrible truth
   * of the nakedness within. */
  public class SLList {
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
			//System.out.println(size);
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	private static void lectureQuestion() {
		SLList L = new SLList();
		IntNode n = new IntNode(5, null);
	}

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(63, null);
		size = 0;
	}

	public SLList(int x) {
		sentinel = new IntNode(63, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

    public SLList(int[] ints) {
        sentinel = new IntNode(63, null);
        size = 0;
        for (int i: ints) {
            addLast(i);
        }
    }

 	/** Adds x to the front of the list. */
 	public void addFirst(int x) {
 		sentinel.next = new IntNode(x, sentinel.next);
 		size = size + 1;
 	}

 	/** Returns the first item in the list. */
 	public int getFirst() {
 		return sentinel.next.item;
 	}

 	/** Adds x to the end of the list. */
 	public void addLast(int x) {
 		size = size + 1;

 		IntNode p = sentinel;

 		/* Advance p to the end of the list. */
 		while (p.next != null) {
			 p.next.next = new IntNode(p.next.item, p.next.next);
			 size += 1;
			 p = p.next.next;
 		}

 		p.next = new IntNode(x, null);
 	}

 	/** Returns the size of the list. */
 	public int size() {
 		return size;
 	}

    /*
     * 删除列表的第一个元素
     */
    public void deleteFirst() {
        // 将哨兵指向第一个元素的下一个元素即可
        sentinel.next = sentinel.next.next;
        size -= 1;
    }

	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10 */
 		SLList L = new SLList();
// 		L.addLast(20);
// 		System.out.println(L.size());
//        L.deleteFirst();
//        System.out.println(L.size);
//
//        SLList L1 = new SLList(new int[] {1,2,3});
//        System.out.println(L1.size);
		L.addLast(1);
		L.addLast(2);
		L.addLast(5);
 	}
}
