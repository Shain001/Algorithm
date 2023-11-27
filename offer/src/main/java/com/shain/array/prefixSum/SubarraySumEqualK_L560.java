package com.shain.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用前缀和判断 和为k的subarray个数。
 * 也可以演化成 没有一个区间，其和大于/小于 target 等
 *
 * todo: 前缀和 + hashtable 优化 -> 时间复杂度 可降到 O(n);
 */
public class SubarraySumEqualK_L560 {
    public static void main(String[] args) {
        var test = new SubarraySumEqualK_L560();
        System.out.println(test.v2(new int[]{1}, 0));
    }
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefix = new int[len +1];

        for (int i = 0; i < len; i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }

        int result = 0;
        for (int left = 0; left < prefix.length; left++) {
            // 注意+1， 左开右闭区间
            for (int right = left+1; right < prefix.length; right++) {
                int curSum = prefix[right] - prefix[left];
                result = curSum == k? result+1: result;
            }
        }

        return result;
    }

    public int v2(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        map.put(0, 1);

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
            if (map.containsKey(prefix[i] - k)) {
                result += map.get(prefix[i]-k);
            }

            map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }

        return result;
    }
}
