package com.shain.others.island;

/**
 *  遍历过城中返回遍历了的格子的数量。
 */
public class BiggestIsland_L695 {
    private int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int cur = traverse(i, j);
                    result = Math.max(result, cur);
                }
            }
        }

        return result;
    }

    private int traverse(int i, int j) {
        if ( i < 0 || i > grid.length-1 || j < 0 || j > grid[0].length-1) {
            return 0;
        }

        if (grid[i][j] == 0)
            return 0;

        grid[i][j] = 0;

        return 1 + traverse(i-1, j) +
                traverse(i+1, j) +
                traverse(i, j-1) +
                traverse(i, j+1);
    }
}
