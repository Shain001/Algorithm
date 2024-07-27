package com.shain.greedy.maxtomin;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HaveArray_L1338 {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();

        for (Integer a : arr) {
            count.put(a, count.getOrDefault(a, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.add(entry.getValue());
        }

        int ans = 0;
        int countLen = 0;

        while (countLen < arr.length/2 && !pq.isEmpty()) {
            countLen += pq.poll();
            ans++;
        }

        return ans;
    }
}
