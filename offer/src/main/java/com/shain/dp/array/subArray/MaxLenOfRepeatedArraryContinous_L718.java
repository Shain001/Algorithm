package com.shain.dp.array.subArray;

// 找的重复自数组需要连续
public class MaxLenOfRepeatedArraryContinous_L718 {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int result = 0;

        for (int i = 1; i < dp.length; i++){
            for (int j = 1; j < dp[0].length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }

                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    public int findLength_simplified(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length+1];
        int result = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = dp.length-1; j>0; j--) {
                if (nums1[i] == nums2[j-1]) {
                    dp[j] =dp[j-1] + 1;
                    result = Math.max(result, dp[j]);
                } else {
                    dp[j] = 0;
                }

            }
        }


        return result;
    }

}
