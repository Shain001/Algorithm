package com.shain.graph.unionFindSet;

import java.util.stream.IntStream;

/**
 * 分析题意，需要将所有网络连接成同一个网络。
 *
 * 假设最后形成了n个网络，说明存在n个连通分量，要将n个连通分量合并，很明显至少需要n-1个网络连接线。
 *
 * 那么，这n-1根网络连接线从哪来呢，只有从各个网络中多余的连接线拔过来。
 *
 * 所以在遍历Connections数组时，需要记录有多少根多余的网络连接线。
 *
 *
 */
public class MinimumConnection_L1319 {
    class UnionFind3 {
        private int[] roots;
        private int countTrees;
        private int[] rank;
        private int countRedundant;

        public UnionFind3(int n) {
            this.roots = IntStream.range(0, n).toArray();
            this.countTrees = n;
            this.rank = IntStream.generate(() -> 1).limit(n).toArray();
        }

        public void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (parentA == parentB) {
                countRedundant++;
                return;
            }

            if (rank[parentA] <= rank[parentB]) {
                this.roots[parentA] = parentB;
                rank[b] = rank[b] == rank[a] ? rank[b] + 1 : rank[b];
            } else {
                this.roots[parentB] = parentA;
            }

            countTrees--;
        }

        public int find(int a) {
            if (this.roots[a] == a) {
                return a;
            }
            int root = find(roots[a]);
            roots[a] = root;
            return root;
        }

        public int getCount() {
            return this.countTrees;
        }

        public int getRedundant() {
            return this.countRedundant;
        }
    }

    public int makeConnected(int n, int[][] connections) {
        var uf = new UnionFind3(n);

        for (int[] c : connections) {
            uf.union(c[0], c[1]);
        }

        return uf.getRedundant() >= uf.getCount()-1? uf.getCount()-1: -1;
    }
}
