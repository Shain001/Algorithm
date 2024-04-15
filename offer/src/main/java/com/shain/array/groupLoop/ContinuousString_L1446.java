package com.shain.array.groupLoop;

public class ContinuousString_L1446 {
    public int maxPower(String s) {
        int i = 1;
        int ans = 1;

        while (i < s.length()) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                i++;
                continue;
            }

            int j = i - 1;

            while (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                i++;
            }

            ans = Math.max(ans, i - j);
        }

        return ans;
    }
}
