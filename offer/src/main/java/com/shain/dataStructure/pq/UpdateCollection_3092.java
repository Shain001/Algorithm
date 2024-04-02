package com.shain.dataStructure.pq;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class UpdateCollection_3092 {
    class Node {
        protected long freq;
        protected int id;

        public Node(int id, long freq) {
            this.id = id;
            this.freq = freq;
        }
    }
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> map = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (int) (o2.freq - o1.freq));
        long[] ans = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int id = nums[i];
            int f= freq[i];

            long curFreq = map.getOrDefault(id, 0L);
            curFreq += f;
            map.put(id, curFreq);

            Node curNode = new Node(id, curFreq);
            pq.add(curNode);

            Node p = pq.peek();
            while (!pq.isEmpty() && map.get(p.id) != p.freq) {


                pq.poll();
                p = pq.peek();

            }
            ans[i] = pq.peek().freq;
        }

        return ans;
    }
}
