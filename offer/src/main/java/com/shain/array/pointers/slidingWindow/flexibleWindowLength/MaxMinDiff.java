package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 找到 所有 最大值最小值差值小于等于 K 的窗口， 返回窗口数量。
 *
 * 唯一tricky 在于 k >=0, 所有元素本身也是一个符合条件的答案。
 * 所以 更新ans时， 应该 ans += right-left+1
 *
 * 理解为： 当前right，left是一个绝对满足条件的窗口， 那么这个窗口中所有的 子窗口 肯定也满足条件。
 * right - left + 1：这个差值代表在当前的右边界下，从left到right-1可以形成的连续子数组的数量。简单地说，这是因为对于每个以right-1结尾的子数组，它的起始点可以是left, left+1, ..., right-1，总共有right - left种。
 */
public class MaxMinDiff {
    public static void main(String[] args) {
        var test = new MaxMinDiff();
        System.out.println(test.maxMinDiff(5, new int[]{4,4,4,4}));
    }
    public int maxMinDiff(int K, int[] A) {
        int left = 0;
        int right = 0;
        int ans = 0;
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        while (right < A.length) {
            int cur = A[right];

            // Maintain the deque for maximums
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < cur) {
                maxDeque.pollLast();
            }
            // Maintain the deque for minimums
            while (!minDeque.isEmpty() && minDeque.peekLast() > cur) {
                minDeque.pollLast();
            }

            maxDeque.offerLast(cur);
            minDeque.offerLast(cur);


            // Check the bounds and adjust the window
            while (!maxDeque.isEmpty() && !minDeque.isEmpty() &&
                    maxDeque.peekFirst() - minDeque.peekFirst() > K) {

                int leftVal = A[left++];
                if (maxDeque.peekFirst() == leftVal) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == leftVal) {
                    minDeque.pollFirst();
                }
            }

            ans += right - left+1;
            right++;
        }

        return ans;
    }
}
