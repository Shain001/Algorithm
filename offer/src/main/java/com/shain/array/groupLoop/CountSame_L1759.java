package com.shain.array.groupLoop;

public class CountSame_L1759 {
    public int countHomogenous(String s) {
        final int MOD = (int) 1e9 + 7;

        int i = 0;
        int ans = 0;
        while (i < s.length()) {
            int j = i;
            // 注意细节， i 在这里++ 且初始化为0， 否则会出bug
            i++;

            while (i > 0 && i < s.length() && s.charAt(i) == s.charAt(i-1)) {
                i++;
            }

            ans += ((long)(1+ (i-j)) * (long)(i-j) / 2) % MOD;

        }

        return ans;
    }
}
