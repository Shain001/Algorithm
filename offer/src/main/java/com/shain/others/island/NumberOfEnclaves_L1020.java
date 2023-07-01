package com.shain.others.island;

/**
 * 基本类型， 无非先消除边界上的1
 */
public class NumberOfEnclaves_L1020 {
    private int[][] grid;
    public int numEnclaves(int[][] grid) {
        this.grid = grid;

        for (int i = 0; i < grid.length; i++) {
            traverse(i, 0);
            traverse(i, grid[0].length-1);
        }

        for (int j = 0; j < grid[0].length; j++) {
            traverse(0, j);
            traverse(grid.length-1, j);
        }

        int count = 0;

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void traverse(int i, int j) {
        if (i< 0 || i > grid.length-1 || j < 0 || j > grid[0].length-1) {
            return;
        }

        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        traverse(i, j-1);
        traverse(i, j+1);
        traverse(i-1, j);
        traverse(i+1, j);

    }
}
