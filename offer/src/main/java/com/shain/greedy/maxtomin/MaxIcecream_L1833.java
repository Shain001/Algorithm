package com.shain.greedy.maxtomin;

public class MaxIcecream_L1833 {
    public int maxIceCream(int[] costs, int coins) {
        final int MAX = 100001;
        int[] count = new int[MAX];

        for (Integer n : costs) {
            count[n]++;
        }

        int i = 1;
        int ans = 0;
        while (i < MAX && coins - count[i] * i >= 0) {
            coins -= count[i] * i;
            ans += count[i];
            i++;
        }

        while (i < MAX && coins - i >= 0) {
            ans++;
            coins -= i;
        }

        return ans;
    }
}
