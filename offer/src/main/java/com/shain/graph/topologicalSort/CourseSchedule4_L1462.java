package com.shain.graph.topologicalSort;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule4_L1462 {
    private List<List<Integer>> adj;
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] p: prerequisites) {
            adj.get(p[0]).add(p[1]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries){
            ans.add(find(q[0], q[1],  new boolean[numCourses]));
        }
        return ans;
    }

    private Boolean find(int u, int v, boolean[] visited) {
        if (visited[u]) {
            return false;
        }

        visited[u] = true;
        if (u == v) {
            return true;
        }

        boolean flag = false;
        for (Integer i : adj.get(u)) {
            flag |= find(i, v, visited);
        }

        return flag;
    }
}
