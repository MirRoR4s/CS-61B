package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private int N;
    private final int BLANK = 0;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     * 
     * @param tiles
     */
    public Board(int[][] tiles) {
        // 新建一个数组，将入参数组的值拷贝到上面，最后赋值给 this.tiles
        N = tiles.length;
        this.tiles = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * 
     * @param i
     * @param j
     * @throws java.lang.IndexOutOfBoundsException if i and j are not between 0 and
     *                                             N-1.
     * @return
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new java.lang.IndexOutOfBoundsException("index error");
        }

        return tiles[i][j];
    }

    /**
     * Returns the board size N
     * 
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board.
     * code from `https://joshh.ug/neighbors.html`
     * 
     * @return the neighbors of the current board
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * Hamming estimate described below
     * 
     * @return
     */
    public int hamming() {
        int cnt = 0;
        int num = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i == N - 1 && j == N - 1) {
                    continue;
                }

                if (tiles[i][j] != num) {
                    cnt += 1;
                }

                num += 1;
            }
        }

        return cnt;
    }

    public int manhattan() {
        int sum = 0;
        int num = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int v = tiles[i][j];
                if (v == 0) {
                    continue;
                }

                int targetRow = (v - 1) / N;
                int targetCol = (v - 1) % N;

                sum += (Math.abs(targetRow - i) + Math.abs(targetCol - j));

                num += 1;
            }
        }

        return sum;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * Gradescope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board board = (Board) y;
        int n = board.size();
        if (N != n) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != board.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        if (tiles == null) {
            return 0;
        }

        int result = 1; // 初始哈希值
        for (int[] row : tiles) {
            int rowHash = 1; // 每一行的哈希值
            if (row != null) {
                for (int value : row) {
                    rowHash = 31 * rowHash + value; // 使用素数生成哈希值
                }
            }
            result = 31 * result + rowHash; // 合并行哈希值
        }
        return result;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
