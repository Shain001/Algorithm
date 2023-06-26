package com.shain.dp.array.subArray;

public class LongestContinueIncreaseSubArrary_L674 {
    // dp[i] = dp[i - 1] + 1
    public int findLengthOfLCIS(int[] nums) {
        int dp = 1;
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp++;
            } else {
                dp = 1;
            }

            result = Math.max(dp, result);
        }

        return result;
    }

    /**
     * 1.dp[i] 代表当前下标最大连续值
     * 2.递推公式 if（nums[i+1]>nums[i]） dp[i+1] = dp[i]+1
     * 3.初始化 都为1
     * 4.遍历方向，从其那往后
     * 5.结果推导 。。。。
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS_copied(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                dp[i + 1] = dp[i] + 1;
            }
            res = res > dp[i + 1] ? res : dp[i + 1];
        }
        return res;
    }
}
