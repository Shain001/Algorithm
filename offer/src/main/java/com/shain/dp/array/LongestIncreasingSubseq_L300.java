package com.shain.dp.array;

import java.util.Arrays;

public class LongestIncreasingSubseq_L300 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

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

        return result;
    }

    // wrong version
    // [0, 1, 0, 3, 2, 3] 在这种情况下， 遍历到3时， 这种写法碰到第一个0就停了， 然而0这一位的dp值为1， 导致3 这一位dp值被更新为了2
    // 但是不应该在遇到第一个比3小的数就停， 因为前面的1也比3小， 而dp1的值是2。
//    public int lengthOfLIS(int[] nums) {
//        int[] dp = new int[nums.length];
//        Arrays.fill(dp, 1);
//        int result = 0;
//        for (int i = 1; i < nums.length; i++) {
//            int temp = 1;
//
//            for (int j = i; j >= 0; j--) {
//                if (nums[j] < nums[i]) {
//                    temp = Math.max(dp[j] + 1, temp);
//                }
//            }
//
//            dp[i] = temp;
//            result = Math.max(result, dp[i]);
//        }
//
//        return result;
//    }
}
