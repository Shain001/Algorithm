package com.shain.dp.array.subArray;

/**
 * 这题也是跟 L300  一样， 注意dp数组的含义是 "以i 结尾的连续子数组的最大和， 而不是 0-i 之间的连续子数组最大和"。
 */
public class MaximumSubarray_L53 {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];


        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }


        return result;
    }

    // o1 space complexity
    public int maxSubArray_v2(int[] nums) {
        int preSum = nums[0];
        int result = preSum;


        for (int i = 1; i < nums.length; i++) {
            preSum = Math.max(preSum + nums[i], nums[i]);
            result = Math.max(result, preSum);
        }


        return result;
    }

    // use pre sum array
    // 写完了发现好像跟动态规划一样了。。
    public int maxSubArray_v3(int[] nums) {

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        // 初始化为0， 为了能处理 nums长度为1 的情况。
        int minPrefixSum = 0;
        // 初始化为第一位得值， 同样是为了nums长度为1
        int result = nums[0];

        for (int i = 1; i < prefixSum.length; i++) {
            // 更新前缀和数组
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            // 更新result
            result = Math.max(prefixSum[i] - minPrefixSum, result);
            // 更新到当前位为止， 最小的前缀和。
            minPrefixSum = Math.min(prefixSum[i], minPrefixSum);
        }
        return result;
    }

    // 另一种写法的前缀和， 但是更v3是一样的
    // 写成v3是为了 优化时间复杂度， 但是优化完发现变成了动态规划的形式， 一模一样
//    int maxSubArray(int[] nums) {
//        int n = nums.length;
//        int[] preSum = new int[n + 1];
//        preSum[0] = 0;
//        // 构造 nums 的前缀和数组
//        for (int i = 1; i <= n; i++) {
//            preSum[i] = preSum[i - 1] + nums[i - 1];
//        }
//
//        int res = Integer.MIN_VALUE;
//        int minVal = Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            // 维护 minVal 是 preSum[0..i] 的最小值
//            minVal = Math.min(minVal, preSum[i]);
//            // 以 nums[i] 结尾的最大子数组和就是 preSum[i+1] - min(preSum[0..i])
//            res = Math.max(res, preSum[i + 1] - minVal);
//        }
//        return res;
//    }
}
