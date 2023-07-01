package com.shain.dp.array.subArray;

import java.util.Arrays;

public class LongestIncreasingSubseq_L300 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    /**
     * 可以二分查找优化 内循环的for。 要用额外空间。 没记在这。
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            // missed before and caused mistake
            int temp = 1;

            for (int j = i; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(dp[j] + 1, temp);
                }
            }

            dp[i] = temp;
            result = Math.max(result, dp[i]);
        }

        // update: 对于这种数组相关的题， 一定要注意 dp数组的含义。 该题dp数组含义是 从 下标x 到 i 位置的最大严格上升子序列长度， 而不是 从0-i。
        // 所以一定要维护 result， 记录最大值。 你经常写成直接返回 dp[-1]
        return result;
    }
}
