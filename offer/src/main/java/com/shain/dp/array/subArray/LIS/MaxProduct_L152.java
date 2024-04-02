package com.shain.dp.array.subArray.LIS;

public class MaxProduct_L152 {
    public static void main(String[] args) {
        var test = new MaxProduct_L152();
        System.out.println(test.maxProduct(new int[]{2,3,-2,4}));
    }
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            max[i] = Math.max(cur, Math.max(cur * max[i-1], cur * min[i-1]));
            min[i] = Math.min(cur, Math.min(cur * max[i-1], cur * min[i-1]));
            ans = Math.max(ans, max[i]);
        }

        return ans;
    }
}
