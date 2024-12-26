package com.shain.array.prefixSum;

import java.util.HashSet;
import java.util.Set;

public class L523 {
    public static void main(String[] args) {
        var test = new L523();
        System.out.println(test.checkSubarraySum(new int[]{1, 0}, 2));
    }
    public boolean checkSubarraySum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        set.add(0);
        int[] prefixSum = new int[nums.length+1];

        for (int i = 0; i < nums.length; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];

            if (prefixSum[i+1] % k == 0) {
                return true;
            }
            int n = 1;
            while (prefixSum[i+1] - n * k >= 0) {
                if (i >= 1 && set.contains(prefixSum[i+1] - n*k)) {
                    return true;
                }
                n++;
            }
            set.add(prefixSum[i+1]);
        }

        return false;
    }
}
