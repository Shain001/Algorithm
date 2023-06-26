package com.shain.dp.string.oneString;

public class LongestPalindrome_L5 {
    private static int max = 1;
    private static int start = 0;

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }

    // 写傻逼了
    // 只需要把dp 数组初始化成 bool 即可， 代表 0-i是不是回文字符串。 Then every time you got a true, decides if needs to update
    // start and max.
    // 开始的时候没看题， assume 要返回的是最长回文串长度， 导致后面没反应过来改。
    // updated version is copied from leetcode and can be seen below.
    public static String longestPalindrome(String s) {
        int sLen = s.length();
        int[][] dp = new int[sLen][sLen];
        boolean[][] isPalindrome = new boolean[sLen][sLen];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            isPalindrome[i][i] = true;
            if (i + 1 < sLen) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 0;
                isPalindrome[i][i + 1] = dp[i][i + 1] == 2;
                if (isPalindrome[i][i + 1]) updateResult(2, i);
            }
        }

        for (int curLen = 3; curLen <= s.length(); curLen++) {
            for (int i = 0; i < sLen; i++) {
                int j = i + curLen - 1;

                // 判断超界
                if (j > sLen - 1)
                    continue;

                // 如果当前两字符不相等， 直接跳过
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    continue;
                }

                // 如果相等， 并且当前长度为3
                if (curLen == 3) {
                    dp[i][j] = 3;
                    isPalindrome[i][j] = true;
                    updateResult(curLen, i);
                    continue;
                }

                // 如果长度已经超过3， 不仅需要判断当前字符相不相等， 还要判断i+1， j-1是不是回文串。
                if (isPalindrome[i + 1][j - 1]) {
                    dp[i][j] = curLen;
                    isPalindrome[i][j] = true;
                    updateResult(curLen, i);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return s.substring(start, start + max);
    }

    private static void updateResult(int curLen, int index) {
        if (curLen > max) {
            start = index;
            max = curLen;

            System.out.println("start: " + start + ", curLen: " + curLen);
        }
    }

    public String longestPalindrome_fromLeetCode(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
