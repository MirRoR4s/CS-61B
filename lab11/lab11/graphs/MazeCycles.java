package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /*
     * Inherits public fields:
     * public int[] distTo;
     * public int[] edgeTo;
     * public boolean[] marked;
     */
    private boolean flag; // 标记是否找到了环，如果找到了环则立即停止DFS。

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        for (int v = 0; v < maze.V(); v++) {
            if (!marked[v]) {
                if (dfs(v, -1)) {
                    return;
                }
            }
        }

    }

    // Helper methods go here
    private boolean dfs(int v, int parentV) {
        marked[v] = true;

        if (flag) {
            return true;
        }

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                if (dfs(w, v) && !flag) {
                    if (edgeTo[w] == Integer.MAX_VALUE) {
                        edgeTo[w] = v;
                        announce();
                    } else {
                        flag = true;
                    }

                    return true;
                }
            } else if (w != parentV) {
                edgeTo[w] = v;
                announce();
                return true;
            }
        }

        return false;
    }
}
