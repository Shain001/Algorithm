package com.shain.graph.topologicalSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule_L207 {
    public static void main(String[] args) {
        var test = new CourseSchedule_L207();
        System.out.println(test.canFinish(2, new int[][]{{1, 0}}));
    }
    // 通过入度判断
    // https://leetcode.cn/problems/course-schedule/solutions/250377/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        while (queue.size() != 0) {
            int cur = queue.poll();

            for (Integer c : adj.get(cur)) {
                inDegree[c]--;
                if (inDegree[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        // 如果把所有能上的课都上了以后， 仍然有入度不为0的节点，则代表不行
        for (Integer n: inDegree) {
            if (n != 0)
                return false;
        }

        return true;
    }

    // 通过图中是否有环判断解题 -> 略

}
