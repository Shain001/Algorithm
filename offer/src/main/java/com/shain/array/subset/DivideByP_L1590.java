package com.shain.array.subset;

public class DivideByP_L1590 {
    public static void main(String[] args) {
        var test = new DivideByP_L1590();
        System.out.println(test.minSubarray(new int[]{1000000000,1000000000,1000000000}, 3));
    }
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int ans = -1;
        for (int subset = 0; subset < (1 << n); subset++) {
            if (subset == 0) {
                continue;
            }
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if ((subset & (1<<j)) == (1<<j)) {
                    sum += nums[j];
                }
            }
            if (sum % p == 0) {
                ans = Math.max(ans, Integer.bitCount(subset));
            }
        }

        return ans == -1? ans : nums.length-ans ;
    }
}
