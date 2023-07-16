package com.shain.graph.unionFindSet;

public class NumberOfIsland_L200 {
    private int[][] directions = {{1, 0}, {-1,0}, {0, 1}, {0, -1}};

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind uf = new UnionFind(col * row);

        // total number of 1
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';

                    // 查看四周有没有1， 有的话则合并
                    for (int[] d : directions) {
                        int x = i+d[0];
                        int y = j+d[1];

                        if (x < 0 || x > row-1 || y < 0 || y > col-1)
                            continue;

                        if (grid[x][y] != '1')
                            continue;

                        uf.union(x*col + y, i*col + j);
                    }
                }
            }
        }

        return count - uf.getCount();

    }
}
