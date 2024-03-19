package com.shain.graph.Kruskal;

import com.design.snakeGame_L353.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class MinCostConnectAllPoint {
    class Edge {
        private int from;
        private int to;
        private int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    // point 的索引值即当成节点的编号。
    public int minCostConnectPoints(int[][] points) {
        // 1. initialize all edges and add them to a list.
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                int a1 = points[i][0];
                int b1 = points[i][1];
                int a2 = points[j][0];
                int b2 = points[j][1];

                int distance = Math.abs(a1 - a2) + Math.abs(b1 - b2);
                edges.add(new Edge(i, j, distance));
            }
        }

        // 2. 将 edges 按照升序排序
        edges.sort(Comparator.comparingInt(o -> o.distance));

        // 3. 从距离最小的边开始遍历， 直到所有的点都已经联通。
        var uf = new UnionFind3(points.length);
        int ans = 0;

        for (Edge edge: edges) {

            if (uf.union(edge.from, edge.to) == 1) {
                ans += edge.distance;
            }
            if (uf.countTrees == 1) {
                return ans;
            }
        }

        return -1;
    }

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

        public int union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (parentA == parentB) {
                countRedundant++;
                return -1;
            }

            if (rank[parentA] <= rank[parentB]) {
                this.roots[parentA] = parentB;
                rank[b] = rank[b] == rank[a] ? rank[b] + 1 : rank[b];
            } else {
                this.roots[parentB] = parentA;
            }

            countTrees--;
            return 1;
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
}
