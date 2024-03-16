package com.shain.array.pointers.twoPointer.binarySearch;

/**
 * 证明更重要， 看题解：
 * https://leetcode.cn/problems/find-a-peak-element-ii/solutions/2571587/tu-jie-li-yong-xing-zui-da-zhi-pan-duan-r4e0n/
 */
public class FindPeak2_L1901 {
    public int[] findPeakGrid(int[][] mat) {
        // 找每一行的最大值， 看题解吧。
        int up = 0;
        int bot = mat.length-1;

        // 这里要加=， 因为里面直接return， 不加会漏判。
        while (up <= bot) {
            int mid = up + (bot-up)/2;
            int max = getMax(mat[mid]);
            int b = mid == mat.length-1? -1: mat[mid+1][max];
            int u = mid == 0? -1: mat[mid-1][max];

            if (mat[mid][max] > b && mat[mid][max] > u) {
                return new int[]{mid, max};
            }

            if (mat[mid][max] > b) {
                bot = mid-1;
            } else {
                up = mid+1;
            }
        }

        return new int[]{-1,-1};
    }

    private int getMax(int[] nums) {
        int ans = 0;
        int cur = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > cur) {
                ans = i;
                cur = nums[i];
            }
        }
        return ans;
    }
}
