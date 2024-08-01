package com.shain.array.prefixSum;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-subsequence-with-limited-sum/solutions/1781111/fei-bao-li-zuo-fa-qian-zhui-he-er-fen-by-ny4m/
 *
 * 有贪心的思想。
 *
 * 子序列不能排序是障眼法， 因为子序列中可以删除元素， 并且只需要返回长度。
 *
 * 可以在nums数组上操作， 不创建单独的prefix数组来improve 空间复杂度。 略， 有疑问看题解， 没啥区别。
 */
public class LongestSubArray_L2389 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] prefix = new int[nums.length+1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }

        int i = 0;
        int[] ans = new int[queries.length];
        for (Integer q : queries) {
            ans[i++] = binarySearch(q, prefix);
        }

        return ans;
    }

    private int binarySearch(int n, int[] prefix) {
        int left = 0;
        int right = prefix.length-1;

        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (prefix[mid] <= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
