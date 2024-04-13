package com.shain.dp.array.subArray.LIS;

public class StringValue_L2606 {
    private char[] charArray;
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        charArray = chars.toCharArray();
        int[] dp = new int[s.length()];

        int ans = 0;
        for (int i = 0; i< dp.length; i++) {
            char cur = s.charAt(i);
            int inx = getCharIndex(cur);
            int val = inx == -1? cur-'a'+1: vals[inx];
            if (i == 0) {
                dp[i] = val;
            } else {
                dp[i] = Math.max(dp[i-1], 0) + val;
            }

            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    private int getCharIndex(char target) {
        for (int i = 0; i < charArray.length; i++) {
            if (target == charArray[i]) {
                return i;
            }
        }
        return -1;
    }
}
