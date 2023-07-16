package com.shain.graph.unionFindSet;

/**
 * 利用并查集， 只利用到了 isConnected 方法， 而不是利用count计算连通量的类型
 *
 * todo: l785 839 399 1631 684
 */
public class SurroundedArea_L130 {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public void solve(char[][] board) {
        int r = board.length;
        int c = board[0].length;

        // +1 因为assume 边界上的 0 的跟为虚拟节。 为计算方便， 以uf中parent的最后一位记为 虚拟节点， 使得所有边界上的0与虚拟节点union。
        UnionFind uf = new UnionFind(r*c + 1);

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'O') {
                    // 边界上的o 与虚拟头节点union
                    if (i == r-1 || j == 0 || i == 0 || j == c-1) {
                        uf.union(r*c, i * c + j);
                    } else {
                        // 非边界的0 与相邻的0 union， 进而能够知道每个o是否跟 边界上的o 是相连的。
                        for (int[] d : directions) {
                            int x = i + d[0];
                            int y = j + d[1];

                            if (x < 0 || x > r-1 || y < 0 || y > c-1)
                                continue;
                            if (board[x][y] != 'O')
                                continue;

                            uf.union(i * c + j, x * c + y);
                        }
                    }
                }
            }
        }

        for (int i = 1; i < r-1; i++) {
            for (int j = 1; j < c-1; j++) {
                if (board[i][j] == 'O') {
                    if (!uf.isConnected(r*c, i*c + j)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}
