package com.google;

import java.util.*;

public class SlidingWindowKAverage {
    public static void main(String[] args) {
        var test = new SlidingWindowKAverage(5,3);
        test.add(1);
        test.add(2);
        System.out.println("should get -1: " + test.getAverage());
        test.add(3);
        System.out.println("should get -1: " + test.getAverage());
        test.add(4);
        test.add(5);
        System.out.println("should get 4: " + test.getAverage());
        test.add(6);
        System.out.println("should get 5: " + test.getAverage());
    }

    private final Deque<Integer> queue;
    private final LazyDeletePQ pq;
    private final int m;
    private final int k;

    // m = 窗口大小
    // k = 前k大
    public SlidingWindowKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.queue = new LinkedList<>();
        this.pq = new LazyDeletePQ(k);
    }

    public void add(int n) {
        queue.offerLast(n);

        if (pq.getActualSize() < k || pq.getKthLargest() <= n) {
            pq.add(n);
        }

        if (queue.size() > m) {
            int removed = queue.pollFirst();
            if (removed >= pq.getKthLargest()) {
                pq.remove(removed);
            }
        }
    }

    public int getAverage() {
        return queue.size() < m? -1 : pq.getAverage();
    }

    static class LazyDeletePQ {
        private int curSum;
        private int actualSize;
        private final int size;
        private final Map<Integer, Integer> toDelete;
        private final PriorityQueue<Integer> pq;


        public LazyDeletePQ(int size) {
            this.actualSize = 0;
            this.curSum = 0;
            this.size = size;
            this.toDelete = new HashMap<>();
            this.pq = new PriorityQueue<>();
        }

        public void add(int n) {
            if (actualSize < size) {
                pq.add(n);
                curSum += n;
                actualSize++;
                return;
            }

            pq.add(n);
            int poped = pq.poll();
            if (poped != n) {
                curSum += n;
                curSum -= poped;
            }

            // 因为pq中增加/删除了数字， pq的peek可能变化， 进行lazy delete
            checkAndPrune(this.pq);
        }

        // method to ensure peek is accurate
        private void checkAndPrune(PriorityQueue<Integer> pq) {
            if (pq.size() == 0) {
                return;
            }

            while (!pq.isEmpty() && toDelete.getOrDefault(pq.peek(), 0) > 0) {
                int deleted = pq.poll();
                toDelete.merge(deleted, -1, Integer::sum);
                if (toDelete.get(deleted) == 0) {
                    toDelete.remove(deleted);
                }
            }
        }

        public void remove(int n) {
            toDelete.merge(n, 1, Integer::sum);
            actualSize--;
            checkAndPrune(this.pq);
        }

        // 返回最小值， 代表， pq中元素还不够， 必定要添加。方便判断
        public int getKthLargest() {
            return pq.size() == size ? pq.peek() : Integer.MIN_VALUE;
        }

        public int getAverage() {
            return actualSize == size? curSum / size : -1;
        }

        public int getActualSize() {
            return this.actualSize;
        }
    }
}
