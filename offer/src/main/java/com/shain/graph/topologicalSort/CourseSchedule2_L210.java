package com.shain.graph.topologicalSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule2_L210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int[] p: prerequisites) {
            adj.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        // 寻找入度为0的节点。
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int i = 0;
        while (queue.size() != 0) {
            int cur = queue.poll();
            result[i++] = cur;

            for (Integer c : adj.get(cur)) {
                inDegree[c]--;
                if (inDegree[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        return i == numCourses? result: new int[0];
    }
}
