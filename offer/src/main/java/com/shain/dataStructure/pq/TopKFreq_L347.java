package com.shain.dataStructure.pq;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFreq_L347 {
    class Node {
        protected int val;
        protected int count;

        public Node(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.count-o1.count);

        for (Integer n : nums) {
            int f = freq.getOrDefault(n, 0);
            f += 1;
            freq.put(n, f);
            Node node = new Node(n, f);
            pq.add(node);
        }

        int[] ans = new int[k];
        ans[0] = pq.poll().val;
        int i = 1;

        while (i < k && !pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek().val == ans[i-1]) {
                pq.poll();
            }

            ans[i] = pq.poll().val;
            i++;
        }

        return ans;
    }
}
