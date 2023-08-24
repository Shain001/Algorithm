package com.shain.array.prefixSum;

/**
 * 利用前缀和判断 和为k的subarray个数。
 * 也可以演化成 没有一个区间，其和大于/小于 target 等
 */
public class SubarraySumEqualK_L560 {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefix = new int[len +1];

        for (int i = 0; i < len; i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }

        int result = 0;
        for (int left = 0; left < prefix.length; left++) {
            // 注意+1， 左开右闭区间
            for (int right = left+1; right < prefix.length; right++) {
                int curSum = prefix[right] - prefix[left];
                result = curSum == k? result+1: result;
            }
        }

        return result;
    }
}
