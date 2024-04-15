package com.shain.array.groupLoop;

public class WhichLonger_L1869 {
    public boolean checkZeroOnes(String s) {
        int one = 0;
        int zero = 0;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) != '1') {
                int j = i;
                while (i < s.length() && s.charAt(i) != '1') {
                    i++;
                }
                zero = Math.max(i - j, zero);
                continue;
            }

            int j = i;
            while (i < s.length() && s.charAt(i) == '1') {
                i++;
            }
            one = Math.max(one, i - j);
        }

        return one > zero;
    }
}
