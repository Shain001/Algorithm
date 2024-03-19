package com.shain.graph.unionFindSet;

public class CheckConnectivity_L1971 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind union = new UnionFind(200000);
        for (int[] edge : edges) {
            union.union(edge[0], edge[1]);
        }

        return union.find(source) == union.find(destination);
    }
}
