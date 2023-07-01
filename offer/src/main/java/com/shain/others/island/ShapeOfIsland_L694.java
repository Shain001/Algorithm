package com.shain.others.island;

import java.util.HashSet;
import java.util.Set;

public class ShapeOfIsland_L694 {
    private int[][] grid;
    private Set<String> result = new HashSet<>();

    // 在遍历过程中， 对遍历路径进行序列化， 进而得到岛屿形状
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    traverse(i, j, sb, 0);
                    result.add(sb.toString());
                }
            }
        }

        return result.size();
    }

    private void traverse(int i, int j, StringBuilder sb, int direction) {
        if ( i < 0 || i > grid.length-1 || j < 0 || j > grid[0].length-1) {
            return;
        }

        if (grid[i][j] == 0)
            return;

        sb.append(direction);
        sb.append(",");

        grid[i][j] = 0;

        traverse(i-1, j,sb, 1);
        traverse(i+1, j,sb, 2);
        traverse(i, j-1,sb, 3);
        traverse(i, j+1,sb, 4);

        // 回溯位置也许进行序列化
        sb.append(-direction);
        sb.append(",");

    }
}
