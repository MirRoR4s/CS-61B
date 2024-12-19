package lab11.graphs;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private IndexMinPQ<Integer> fringe;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe = new IndexMinPQ<>(maze.V());
        insertAllVertices();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        // 以曼哈顿距离作为 h(v)
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /**
     * 将所有顶点以 d(source, v) + h(v, goal) 为排序依据加入优先队列中。
     */
    private void insertAllVertices() {
        for (int i = 0; i < maze.V(); i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                fringe.insert(i, distTo[i]);
            } else {
                fringe.insert(i, distTo[i] + h(i));
            }
        }
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // 借助普林斯顿算法包提供的相关API实现A星算法
        while (!fringe.isEmpty()) {
            if (targetFound) {
                return;
            }

            int v = fringe.delMin();
            for (int e : maze.adj(v)) {
                relax(v, e, 1);
            }
        }

    }

    /**
     * 松弛以v为起点，w为终点的边
     * 
     * @param v      边的起点
     * @param w      边的终点
     * @param weight 边的权重
     */
    private void relax(int v, int w, int weight) {
        if (distTo[w] > distTo[v] + weight) {
            distTo[w] = distTo[v] + weight;
            edgeTo[w] = v;
            marked[v] = true;
            marked[w] = true;
            announce();
            if (fringe.contains(w)) {
                fringe.decreaseKey(w, distTo[w]);
            }
        }

        if (w == t) {
            targetFound = true;
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}
