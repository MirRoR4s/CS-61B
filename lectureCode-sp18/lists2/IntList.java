public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}

    public void addAdjacent() {
		/*
		以下方法存在bug，对于 20 10 10 来说，处理后会变成 20 20
		 */
        IntList p = this;
        while (p.rest != null) {
            if (p.first == p.rest.first) {
                p.first *= 2;
                p.rest = p.rest.rest;
            }
            else {
                p = p.rest;
            }
        }
    }

	public static void main(String[] args) {
		IntList L = new IntList(3, null);
		L = new IntList(2, L);
		L = new IntList(1, L);
		L = new IntList(1, L);

		// System.out.println(L.get(1));
        System.out.println(L.size());
        L.addAdjacent();
        System.out.println(L.size());
	}
}
