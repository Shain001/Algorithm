package com.shain.graph.unionFindSet;

import java.util.stream.IntStream;

public class UnConnctablePairs_L2316 {
    class UnionFind2 {
        private int[] roots;
        private int[] rank;
        private int[] size;

        public UnionFind2(int n) {
            this.roots = IntStream.range(0, n).toArray();
            this.rank = IntStream.generate(() -> 1).limit(n).toArray();
            this.size = IntStream.generate(() -> 1).limit(n).toArray();
        }

        public void union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            if (pA == pB) {
                return;
            }

            if (rank[pA] >= rank[pB]) {
                roots[pB] = pA;
                rank[pA] = rank[pA] == rank[pB]? rank[pA]+1: rank[pB];
                size[pA] += size[pB];
            } else {
                roots[pA] = pB;
                size[pB] += size[pA];
            }

        }

        public int find(int a) {
            if (roots[a] == a) {
                return a;
            }
            int parent = find(roots[a]);
            roots[a] = parent;
            return parent;
        }

        public int getSize(int a) {
            return size[a];
        }
    }
    public long countPairs(int n, int[][] edges) {
        var uf = new UnionFind2(n);

        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }

        // calculate results.
        long ans = 0;

        // 遍历所有节点， 确定当前节点所属cluster的大小， 即为当前节点能够连接的节点数量，
        // 进而得到 每个节点不能连接的节点数量。
        // 相加即为 所有节点不能连接的节点数量的和。
        for (int i = 0; i < n; i++) {
            int parent = uf.find(i);
            int sizeOfCluster = uf.getSize(parent);
            int notConnected = n - sizeOfCluster;
            ans += notConnected;
        }

        // 由于题目求的是pair数量， 并且 （1，3） 和 （3，1） 是重复的， 所以除以2
        return ans/2;
    }
}
