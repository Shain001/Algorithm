package com.shain.others.island;

public class NumberOfIsland_200 {
    private char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    traverse(i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void traverse(int i, int j) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
            return;
        }

        if (grid[i][j] == '0')
            return;

        grid[i][j] = '0';

        traverse(i - 1, j);
        traverse(i + 1, j);
        traverse(i, j - 1);
        traverse(i, j + 1);
    }
}
