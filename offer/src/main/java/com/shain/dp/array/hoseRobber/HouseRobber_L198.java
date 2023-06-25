package com.shain.dp.array.hoseRobber;

public class HouseRobber_L198 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }

    public int rob_simplified(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int dp0 = nums[0];
        int dp1 = Math.max(nums[1], dp0);

        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(nums[i] + dp0, dp1);
            dp0 = dp1;
            dp1 = cur;
        }

        return dp1;
    }
}
