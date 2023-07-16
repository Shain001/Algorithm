package com.shain.array.others;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 */
public class LogestContinuesSeq_Offer2_119 {

    // 即遍历每个元素， 对于每个元素， 都数一下最多能到多长。
    // 但是优化的点在于： 1. 用set， 2. if (set.contains(n-1))
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxLen = 0;

        for (Integer n : nums) {
            set.add(n);
        }

        for (Integer n : set) {
            // 跳过会重复遍历到的数据， 优化复杂度
            // e.g.  nums中有 1，2，3，4，5，6，7 -> 只会从1开始数这个序列有多长， 2-7均不会进入下面的循环。
            if (set.contains(n-1))
                continue;

            int cur = n;
            int curLen = 1;
            while (set.contains(cur+1)) {
                cur += 1;
                curLen += 1;
            }

            maxLen = Math.max(maxLen, curLen);
        }

        return maxLen;
    }
}
