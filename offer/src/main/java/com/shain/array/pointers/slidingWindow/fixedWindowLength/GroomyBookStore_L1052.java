package com.shain.array.pointers.slidingWindow.fixedWindowLength;

/**
 * 计算窗口能因 minutes 挽留下的最大顾客数量，然后相加
 */
public class GroomyBookStore_L1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int left = 0;
        int right = 0;
        int count = 0;
        int max = 0;

        while (right < customers.length) {
            if (grumpy[right] == 1) {
                count += customers[right];
            }

            if (right - left + 1 == minutes) {
                max = Math.max(count, max);
                count = grumpy[left] == 1? count - customers[left] : count;
                left ++;
            }

            right++;
        }

        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                max += customers[i];
            }
        }
        return max;
    }
}
