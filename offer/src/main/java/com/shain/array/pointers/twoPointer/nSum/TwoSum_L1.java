package com.shain.array.pointers.twoPointer.nSum;

import com.shain.common.linkList.ListNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSum_L1 {
    public static void main(String[] args) {
        int[] test = new int[]{3,2,4};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> occured = new HashMap<>();

        for (int i = 0; i < nums.length; i ++) {
            if (occured.containsKey(target-nums[i])) {
                return new int[]{i, occured.get(target-nums[i])};
            }
            occured.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}
