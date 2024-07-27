package com.shain.greedy.maxtomin;

import java.util.Arrays;

public class Max_L1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ans) {
                continue;
            } else {
                ans++;
            }
        }

        return ans;
    }
}
