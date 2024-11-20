package Test;

interface Disjoint {

    public void connect(int p, int q);

    public boolean isConnected(int p, int q);
}

public class WeightedQuickUnionDS implements Disjoint {
    private int[] parent;
    private int[] size;

    public WeightedQuickUnionDS(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void connect(int p, int q) {
        int rootP = find[p];
        int rootQ = find[q];

        if (rootP == rootQ) {
            return;
        }

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = parent[rootQ];
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = parent[rootQ];
            size[rootP] += size[rootQ];
        }

    }

    public boolean isConnected(int p, int q) {
        return find[p] == find[q];
    }

    public static void main(String[] args) {

    }

}
