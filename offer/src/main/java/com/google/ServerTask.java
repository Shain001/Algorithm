package com.google;

import java.util.ArrayList;
import java.util.List;

class ServerTask {
    public static void main(String[] args) {
        var test = new ServerTask();
        var l = List.of(new int[]{0, 2}, new int[]{0,3}, new int[]{2,1});
        System.out.println(test.getTaksId(l, 4, 3));
    }

    public int getTaksId(List<int[]> logs, int n, int target) {
        // 0 = server Id; 1 = out degree
        List<List<Integer>> adj = new ArrayList<>();
        int root = 0;
        int[] outDegree = new int[n];
        // 见图
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] log : logs) {
            int from = log[0];
            int to = log[1];

            outDegree[from]++;
            adj.get(from).add(to);
        }

        // 对所有的child根据outdegree排序
        int i = 0;
        for (List<Integer> c : adj) {
            c.sort((o1, o2) -> outDegree[o2] - outDegree[o1]);
            if (c.size() > adj.get(root).size()) {
                root = i;
            }
            i++;
        }

        // dfs, up to bottom,
        return dfs(adj, 0, n, target, root);
    }


    // start => start of taskId
    // len => number of tasks for current server
    private int dfs(List<List<Integer>> adj, int start, int len, int targetServer, int cur) {
        if (cur == targetServer) {
            return start;
        }

        if (adj.get(cur).isEmpty()) {
            return -1;
        }

        List<Integer> children = adj.get(cur);

        for (Integer c : children) {
            int nextStart = start + len / 2;
            len = len / 2;
            int ans = dfs(adj, nextStart, len, targetServer, c);
            if (ans != -1)
                return ans;
        }

        return -1;
    }

}