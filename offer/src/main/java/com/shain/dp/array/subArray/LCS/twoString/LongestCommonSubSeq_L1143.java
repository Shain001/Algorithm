package com.shain.dp.array.subArray.LCS.twoString;

public class LongestCommonSubSeq_L1143 {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    }


    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // i -> text1
        for (int i = 1; i < dp.length; i++) {
            // j -> text2
            for (int j = 1; j < dp[0].length; j++) {

                char t1 = text1.charAt(i - 1);
                char t2 = text2.charAt(j - 1);
                if (t1 == t2) {
                    // wrong:
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
                    // 如果相等，则两个字符都应该加入公共子序列
                    // 那么当前序列最大子序列的值， 就等于前i-1， j-1 个字符的最大子序列长度加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else
                    // 如果不相等，则当前i，j的最长公共子序列的值有几种可能性：
                    // 1. 不要 t1, 看0-t2 （j） 和 0- (i-1) 最长公共子序列有多长。
                    // 2. 不要t2, 看 0-j-1, 0-i 最长有多长。
                    // 3. 两者都不要， 看 0-j-1, 0-i-1 最长有多长
                    // 最后三者取大。
                    // 但是可以发现， 0- j-1， 0-i-1 的长度 必定没有其他两种可能性长， 所以可以省略比较。
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
