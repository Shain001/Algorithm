package com.shain.dp.string.oneString;

public class DecodeString_L91 {
    private String s;

    public int numDecodings(String s) {
        int m = s.length();

        this.s = s;
        int[] dp = new int[m+1];
        dp[m-1] = isValid(m-1, 1)? 1 : 0;
        // 这里得是1， 自己想的， 忘了咋想的了。 从dp方程带入个例子推一下就能确定。
        dp[m] = 1;

        for (int i = m-2; i >= 0; i--) {
            if (isValid(i,1)) {
                dp[i] += dp[i+1];
            }

            if (isValid(i, 2) && i+2 < m+1) {
                dp[i] += dp[i+2];
            }
        }

        return dp[0];
    }

    private boolean isValid(int start, int len) {
        if (s.charAt(start) == '0') {
            return false;
        }

        if (len == 1) {
            return true;
        }

        String s = this.s.substring(start, start+len);
        int n = Integer.parseInt(s);
        return n > 0 && n < 27;
    }
}
