package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * estimate system percolation threshold by simulator.
 */
public class Percolation {
    // squre grid weight and height.
    private final int gridSize;
    private final boolean[][] sites;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFFullSite;
    private int openSiteNum = 0;
    private final int[] dx = { -1, 0, 0, 1 };
    private final int[] dy = { 0, -1, 1, 0 };

    /**
     * creates gridSize-by-gridSize grid, with all sites initially blocked
     * 
     * @throws java.lang.IllegalArgumentException if N less than 0.
     * @param N number of sites.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("gridSize 不能小于等于 0");
        }
        this.gridSize = N;
        sites = new boolean[gridSize][gridSize];

        // 除了 N * N 个网格节点外，顶部和底部分别各有一个虚拟节点，
        // 索引分别是 N * N 和 N * N + 1 (index start from 0!)
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        weightedQuickUnionUFFullSite = new WeightedQuickUnionUF(N * N + 2);
        int index = N * N;
        for (int j = 0; j < N; j++) {
            // 将虚拟的 top site 和首行的 site 连通，无论这些 site 是 blocked 还是 opened 的。
            weightedQuickUnionUF.union(index, j);
            // 底部的虚拟节点和底部的 site 连通
            weightedQuickUnionUF.union(index + 1, (N - 1) * N + j);

            weightedQuickUnionUFFullSite.union(N * N, j);
        }
    }

    /**
     * open the size (row, col) is it is not open already.
     * 
     * @throws java.lang.IndexOutOfBoundsException if row or col greater than grid
     *                                             size.
     * @param row row coordinate.
     * @param col column coordinate.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            sites[row][col] = true;
            openSiteNum += 1;
            // 打开 site 要将该 site 和周围已打开的 site 连通起来
            connectNeighbours(row, col);
        }
    }

    /**
     * is the site (row, col) open?
     * 
     * @param row 行坐标，从 0 开始
     * @param col 列坐标，从 0 开始
     * @return True if site (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row][col];
    }

    /**
     * is the site (row, col) full?
     * 
     * @param row site 的行坐标，从 0 开始
     * @param col site 的列坐标，从 0 开始
     * @return True if site (row, col) is full.
     */
    public boolean isFull(int row, int col) {
        /*
         * A full site is an open site that can be connected to an open site in the
         * top row via a chain of neighboring (left, right, up, down) open sites.
         */
        if (!isOpen(row, col)) {
            return false;
        }
        // int p = (row - 1) * gridSize + col - 1;
        int p = convert(row, col);
        // return weightedQuickUnionUFFullSite.find(p) ==
        // weightedQuickUnionUFFullSite.find(gridSize * gridSize);
        return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(gridSize * gridSize);
    }

    /**
     * number of open sites.
     * 
     * @return number of open sites.
     */
    public int numberOfOpenSites() {
        return openSiteNum;
    }

    /**
     * does the system percolate?
     * 
     * @return True when system percolate.
     */
    public boolean percolates() {
        if (openSiteNum == 0) {
            return false;
        }
        // 当顶部和底部的两虚拟节点连通时则系统是渗滤的
        return weightedQuickUnionUF.find(gridSize * gridSize) == weightedQuickUnionUF.find((gridSize * gridSize) + 1);
    }

    /**
     * 判断指定坐标是否在网格中
     * 
     * @param row 行坐标，从 1 开始
     * @param col 列坐标，从 1 开始
     * @return 返回真，如果坐标在网格中
     */
    private void validate(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            throw new java.lang.IndexOutOfBoundsException(
                    "row or col greater than grid size " + gridSize + ", row: " + row + " col: " + col);
        }
    }

    /**
     * 将指定坐标的 site 和其周围已打开的 site 相连
     * 
     * @param row 行坐标，从 0 开始
     * @param col 列坐标，从 0 开始
     */
    private void connectNeighbours(int row, int col) {
        // 将当前 site 的二维坐标转为对应的一维坐标
        int p = convert(row, col);
        int newRow, newCol;
        for (int i = 0; i < 4; i++) {
            newRow = row + dx[i];
            newCol = col + dy[i];
            if (newRow < 0 || newRow >= gridSize || newCol < 0 || newCol >= gridSize) {
                continue;
            }
            if (isOpen(newRow, newCol)) {
                weightedQuickUnionUF.union(p, convert(newRow, newCol));
            }
        }
    }

    /**
     * convert the two dimentional index to one dimentional index.
     * 
     * @param row
     * @param col
     * @return
     */
    private int convert(int row, int col) {
        return row * gridSize + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(n);
            int col = StdRandom.uniform(n);
            percolation.open(row, col);
        }
    }
}
