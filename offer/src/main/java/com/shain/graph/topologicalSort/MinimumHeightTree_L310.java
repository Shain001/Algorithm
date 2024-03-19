package com.shain.graph.topologicalSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MinimumHeightTree_L310 {
    private List<List<Integer>> adj;
    private int[] degrees;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        this.adj = new ArrayList<>();
        this.degrees = new int[n];
        initialize(n, edges);

        Deque<Integer> queue = new LinkedList<>();

        // initialize degree 1 node to queue
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.add(i);

            }
        }


        while (n > 2) {
            int size = queue.size();
            // 这里必须是 n 直接减整个size， 如果n一个一个减的话会出现错误，因为条件时n>2;
            // 同时， 因为必须得是 n-size, 所以 必须多嵌套一个while，像bfs一样， 否则n的值的更新就不对了。
            // 对比 pseudotree 中L684 的while方式注释。
            n -= size;

            while (size > 0) {
                int cur = queue.poll();
                degrees[cur]--;
                for (Integer k : adj.get(cur)) {
                    degrees[k]--;
                    if (degrees[k] == 1)
                        queue.add(k);
                }
                size--;
            }

        }

        List<Integer> ans = new ArrayList<>();
        for (Integer m: queue) {
            ans.add(m);
        }

        if (ans.isEmpty()) {
            ans.add(0);
        }

        return ans;
    }

    private void initialize(int n, int[][] edges) {
        for (int i = 0; i < n; i++) {
            this.adj.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            this.degrees[from]++;
            this.degrees[to]++;
            this.adj.get(from).add(to);
            this.adj.get(to).add(from);
        }
    }
}
