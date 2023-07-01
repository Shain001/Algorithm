package com.shain.others.island;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * dfs 过程中， 将每个岛屿标号， 并且计算面积， 然后放入map。
 * <p>
 * 第二次遍历时， 只需找到仍然为0 的元素， 得到四周的相邻元素，如果是岛屿的话通过map得到面积， 然后计算 相加以后的面积。
 * 但是需要注意不要重复计算， 即一个 0 可能四周都是被 岛屿包围的， 但是可能是同一个岛屿。
 */
public class MakingLargeIsland_L827 {
    private Map<Integer, Integer> map;
    private int[][] grid;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.map = new HashMap<>();
        map.put(0, 0);

        int result = 0;
        int mark = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = traverse(i, j, mark);
                    map.put(mark, area);
                    mark++;
                    // 防止输入全部是 1， 没有0. 进而导致无法进入第二个forloop， 无法更新result。
                    result = Math.max(result, area);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    // 要用set， 防止一个0周围被同一个岛屿包围导致重复计算。
                    Set<Integer> neighbors = new HashSet<>();

                    neighbors.add(getConnectedArea(i, j - 1));
                    neighbors.add(getConnectedArea(i, j + 1));
                    neighbors.add(getConnectedArea(i - 1, j));
                    neighbors.add(getConnectedArea(i + 1, j));

                    int area = 0;
                    for (Integer n : neighbors) {
                        area += map.get(n);
                    }
                    result = Math.max(result, 1 + area);
                }
            }
        }

        return result;
    }

    private int getConnectedArea(int i, int j) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
            return 0;
        } else {
            return grid[i][j];
        }
    }

    private int traverse(int i, int j, int mark) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
            return 0;
        }

        if (grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = mark;
        return 1 + traverse(i, j + 1, mark) + traverse(i, j - 1, mark) + traverse(i + 1, j, mark) + traverse(i - 1, j, mark);
    }
}
