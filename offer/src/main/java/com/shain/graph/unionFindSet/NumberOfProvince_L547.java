package com.shain.graph.unionFindSet;

import java.util.stream.IntStream;

public class NumberOfProvince_L547 {
    class UnionFind {
        private int[] roots;
        private int countTrees;
        private int[] rank;

        public UnionFind(int n) {
            this.roots = IntStream.range(0, n).toArray();
            this.countTrees = n;
            this.rank = IntStream.generate(() -> 1).limit(n).toArray();
        }

        public void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (parentA == parentB) {
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
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        UnionFind union = new UnionFind(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    union.union(i, j);
                }
            }
        }

        return union.getCount();

    }
}
