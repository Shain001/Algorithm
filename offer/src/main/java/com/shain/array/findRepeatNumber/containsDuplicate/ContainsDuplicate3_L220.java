package com.shain.array.findRepeatNumber.containsDuplicate;

import java.util.TreeSet;

// todo: 看 treeSet & bucket -> 手写treeSet; 桶排序
public class ContainsDuplicate3_L220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> window = new TreeSet<>();

        int left = 0;
        int right = 0;

        while (right < nums.length) {
            while (right - left <= indexDiff && right < nums.length) {
                int cur = nums[right];

                if (window.ceiling(cur) != null && Math.abs(window.ceiling(cur) - cur) <= valueDiff) {
                    return true;
                }

                if (window.floor(cur) != null && Math.abs(window.floor(cur) - cur) <= valueDiff) {
                    return true;
                }

                window.add(cur);
                right++;
            }

            window.remove(nums[left]);
            left++;
        }

        return false;
    }

    // 利用桶排序思想
    public boolean bucket() {
        // todo: implement
        return false;
    }
}
