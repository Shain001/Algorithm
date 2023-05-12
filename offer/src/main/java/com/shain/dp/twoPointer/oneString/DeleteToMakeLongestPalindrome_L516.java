package com.shain.dp.twoPointer.oneString;

public class DeleteToMakeLongestPalindrome_L516 {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("abcdef"));
    }

    // Method 1, dp为使 i, j 变成回文穿的最少删除次数。
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        // 单个字符必为回文， 0次删除
        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }

        // 两个字符
        for (int i = 0; i < len; i++) {
            if (i+1 < len) {
                dp[i][i+1] = s.charAt(i) == s.charAt(i+1)? 0: 1;
            }
        }

        // 两个以上字符
        for (int curLen = 3; curLen <= len; curLen++) {
            for (int start = 0; start < len; start ++) {
                int end = start + curLen-1;

                if (end > len-1) {
                    continue;
                }
                System.out.println(start + " " + end);
                if (s.charAt(start) == s.charAt(end)){
                    dp[start][end] = dp[start+1][end-1];
                } else {
                    dp[start][end] = getMin(dp[start+1][end], dp[start][end-1], dp[start+1][end-1]);
                }
            }
        }

        return len-dp[0][len-1];
    }

    private static int getMin(int deleteR, int deleteL, int deleteBoth) {
        int temp = Math.min(deleteR, deleteL) + 1;
        return Math.min(temp, deleteBoth+2);
    }

    // v2
    // dp 为 使i-j 间最大回文串数量
    public int longestPalindromeSubseq_v2(String s) {
        int sLen = s.length();
        int[][] dp = new int[sLen][sLen];

        for (int i = 0; i < sLen; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < sLen; i++) {
            if (i + 1 <= sLen-1) {
                if (s.charAt(i) == s.charAt(i+1)){
                    dp[i][i+1] = 2;
                } else {
                    dp[i][i+1] = 1;
                }
            }
        }

        for (int curLen = 3; curLen<=sLen; curLen++) {
            for (int start = 0; start < sLen; start++) {
                int end = start + curLen -1;

                if ( end > sLen-1) {
                    continue;
                }

                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = dp[start+1][end-1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start+1][end-1], Math.max(dp[start+1][end], dp[start][end-1]));
                }
            }
        }

        return dp[0][sLen-1];
    }
}
