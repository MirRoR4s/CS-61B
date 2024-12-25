package hw4.puzzle;

import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.LinearProbingHashST;

public class Solver {
    private class SearchNode {
        private WorldState ws;
        private int moves; // the number of moves made to reach this world state-
        // from the initial state.
        private SearchNode prev; // a reference to the previous search node.
        private int distance;

        private SearchNode(WorldState ws, int moves, SearchNode prev) {
            this.ws = ws;
            this.moves = moves;
            this.prev = prev;

            Integer v = table.get(ws);
            if (v == null) {
                int d = ws.estimatedDistanceToGoal();
                table.put(ws, d);
                distance = d;
            } else {
                distance = v;
            }
        }

    }

    // 升序排列SearchNode
    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode node1, SearchNode node2) {
            int cost1 = node1.moves + node1.distance;
            int cost2 = node2.moves + node2.distance;
            return Integer.compare(cost1, cost2);
        }
    }

    private Stack<WorldState> solution;
    private LinearProbingHashST<WorldState, Integer> table;

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * 
     * @param initial
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        solution = new Stack<>();
        table = new LinearProbingHashST<>();
        SearchNode initialNode = new SearchNode(initial, 0, null);

        pq.insert(initialNode);

        while (!pq.isEmpty()) {
            SearchNode x = pq.delMin();

            if (x.ws.isGoal()) {
                SearchNode p = x;
                while (p != null) {
                    solution.push(p.ws);
                    p = p.prev;
                }

                return;
            }

            for (WorldState neighbor : x.ws.neighbors()) {
                SearchNode node = new SearchNode(neighbor, x.moves + 1, x);
                // 如果 neighbor 不是 x 的父节点则将其加入队列中。
                if (x.prev == null || !x.prev.ws.equals(neighbor)) {
                    pq.insert(node);
                }
            }
        }
    }

    // Returns the minimum number of moves to solve the puzzle starting at the
    // initial WorldState.
    public int moves() {
        return solution.size() - 1;
    }

    // Returns a sequence of WorldStates from the initial WorldState to the
    // solution.
    public Iterable<WorldState> solution() {
        return solution;
    }
}
