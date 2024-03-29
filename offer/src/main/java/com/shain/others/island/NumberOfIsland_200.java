package com.shain.others.island;

import java.util.stream.IntStream;

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

    // review
    class UF {
        private final int[] parent;
        private final int[] rank;
        private int clusters;

        public UF (int n) {
            this.parent = IntStream.range(0, n).toArray();
            this.rank = IntStream.generate(() -> 1).limit(n).toArray();
            this.clusters = n;
        }

        public void merge(int x, int y) {
            int px = getParent(x);
            int py = getParent(y);
            if (px == py)
                return;

            if (rank[px] >= rank[py]) {
                parent[py] = px;
                rank[px] = rank[px] == rank[py]? rank[px]+1: rank[px];
            } else {
                parent[px] = py;
            }

            clusters--;
        }

        private int getParent(int x) {
            if (parent[x] == x) {
                return x;
            }

            int p = getParent(parent[x]);
            parent[x] = p;
            return p;
        }

        public int getClusters() {
            return clusters;
        }
    }
    public int numIslands_v2(char[][] grid) {
        int[][] directions = new int[][]{{0,1}, {0,-1},{1,0},{-1,0}};
        int countWater = 0;
        int cols = grid[0].length;
        int rows = grid.length;
        int n = cols * rows;
        UF uf = new UF(n);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    countWater++;
                    continue;
                }

                for (int[] d : directions) {
                    int nc = i + d[0];
                    int nr = i + d[1];

                    if (nc < 0 || nc >= cols || nr < 0 || nr >= rows)
                        continue;

                    if (grid[nc][nr] == '1') {
                        int x = i * cols + j;
                        int y = nc * cols + nr;
                        uf.merge(x, y);
                    }
                }
            }
        }

        return uf.getClusters() - countWater;
    }
}
