package com.shain.graph.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPath_L797 {
    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0);
        dfs(0, graph.length-1, graph);
        return result;
    }

    private void dfs(int cur, int destination, int[][] graph) {
        if (cur == destination) {
            result.add(new ArrayList<>(path));
            return;
        }

        int[] connects = graph[cur];

        for (int connect : connects) {
            path.add(connect);
            dfs(connect, destination, graph);
            path.removeLast();
        }
    }
}
