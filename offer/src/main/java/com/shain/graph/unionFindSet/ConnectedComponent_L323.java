package com.shain.graph.unionFindSet;

/**
 * 能够复制粘贴的题：
 * leetcode 547 省份数量
 */
public class ConnectedComponent_L323 {

    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }

        return n - uf.getCount();

    }
}
