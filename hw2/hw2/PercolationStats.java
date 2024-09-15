package hw2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int n;
    private int trials;
    private double[] fractionArray;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n 或 trials 不能小于等于 0");
        }
        this.trials = trials;
        this.n = n;
        fractionArray = new double[trials];
        simulate();
    }

    private void simulate() {
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (percolation.percolates()) {
                int randomNum = StdRandom.uniformInt(n * n);
                int row = randomNum / n + 1;
                int col = randomNum % n + 1;
                percolation.open(row, col);
            }
            fractionArray[i] = (double) percolation.numberOfOpenSites() / (n * n);
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
        double tmp = CONFIDENCE_95 * stddev() / Math.sqrt(trials);
        return mean() - tmp;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double tmp = CONFIDENCE_95 * stddev() / Math.sqrt(trials);
        return mean() + tmp;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");
    }

}
