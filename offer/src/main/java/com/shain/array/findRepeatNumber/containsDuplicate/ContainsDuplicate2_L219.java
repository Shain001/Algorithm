package com.shain.array.findRepeatNumber.containsDuplicate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate2_L219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> occured = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (occured.getOrDefault(nums[i], -1) != -1) {
                if (Math.abs(occured.get(nums[i]) - i) <= k)
                    return true;
            }
            occured.put(nums[i], i);
        }

        return false;
    }

    // method 2: sliding window
    // 依然要用set， 没什么太大意义
    public boolean containsNearbyDuplicate_v2(int[] nums, int k) {
        int left = 0;
        int right = 0;
        Set<Integer> occured = new HashSet<>();
        while (right < nums.length) {
            while (right - left <= k && right < nums.length) {
                if (occured.contains(nums[right])) {
                    return true;
                }

                occured.add(nums[right]);
                right++;
            }

            occured.remove(nums[left]);
            left++;
        }

        return false;
    }
}
