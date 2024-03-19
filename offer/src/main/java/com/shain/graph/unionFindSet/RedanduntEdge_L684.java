package com.shain.graph.unionFindSet;

import java.util.stream.IntStream;

/**
 * 无向图， 正常应该n个节点n-1条边， 多了一条变则必定有环。
 *
 * 利用并查集找出 多余的一条边， 也即有环的两个节点。
 *
 * 注意， 能够使用并查集找出这条边的前提是 "无向图"。
 * 有向图用 拓扑排序检测环， 或者dfs。
 * 同时， 拓扑排序也可以解决这道题
 *
 */
public class RedanduntEdge_L684 {
    int[] roots;
    int[] rank;

    public int[] findRedundantConnection(int[][] edges) {

        roots = IntStream.range(0, edges.length).toArray();
        rank = IntStream.generate(() -> 1).limit(edges.length).toArray();

        for (int[] edge: edges) {
            if (merge(edge[0]-1, edge[1]-1) == -1)
                return edge;
        }

        return null;
    }

    private int find(int n) {
        if (roots[n] == n) {
            return n;
        }

        int root = find(roots[n]);
        roots[n] = root;
        return root;
    }

    private int merge(int n, int m) {
        int pn = find(n);
        int pm = find(m);

        if (pn == pm) {
            return -1;
        }

        if (rank[pm] <= rank[pn]) {
            roots[pm] = n;
            rank[pn] = rank[pm] == rank[pn]? rank[pn]+1: rank[pn];
        } else {
            roots[pn] = m;
        }

        return 1;
    }
}
