package hw2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int N;
    private int T;
    private double[] fractionArray;
    private PercolationFactory pf;

    /**
     * perform T independent experiments on an N-by-N grid.
     * 
     * @param N
     * @param T
     * @param pf
     * @throws java.lang.IllegalArgumentException if N <=0 or T <= 0.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T must greater than 0!");
        }
        this.T = T;
        this.N = N;
        this.pf = pf;
        fractionArray = new double[T];
        simulate();
    }

    private void simulate() {
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                percolation.open(row, col);
            }
            fractionArray[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractionArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double tmp = CONFIDENCE_95 * stddev() / Math.sqrt(T);
        return mean() - tmp;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double tmp = CONFIDENCE_95 * stddev() / Math.sqrt(T);
        return mean() + tmp;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationFactory pf = new PercolationFactory();
        PercolationStats percolationStats = new PercolationStats(n, trials, pf);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");
    }

}
