package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.HashMap;
import java.util.Map;

/**
 * 用map追踪重复元素上一次出现的坐标。
 *
 * 适用于 窗口内 元素要求都唯一的情况。
 */
public class KLengthMaxSum_L2461 {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        long sum = 0;
        long result = 0;

        while (right < nums.length) {
            sum += nums[right];

            if (map.containsKey(nums[right])) {
                long nextLeft = map.get(nums[right]);
                map.put(nums[right], right);
                while (left <= nextLeft) {
                    sum -= nums[left++];
                }
            } else {
                map.put(nums[right], right);
            }

            if (right - left + 1 == k) {
                result = Math.max(sum, result);
                sum -= nums[left];
                map.remove(nums[left]);
                left++;
            }

            right++;
        }

        return result;
    }
}
