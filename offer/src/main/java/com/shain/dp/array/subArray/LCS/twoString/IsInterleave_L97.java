package com.shain.dp.array.subArray.LCS.twoString;

public class IsInterleave_L97 {
    // 学习一下题解的这个写法。
    // 省去了很多 if
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i < m + 1; ++i) {
            for (int j = 0; j < n + 1; ++j) {
                int p = i + j - 1;

                if (i > 0) {
                    f[i][j] = f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                // 这里要加 || 因为 上一个if中 fij可能已经为true了。 第一个if则不用加｜｜
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m][n];
    }
}
