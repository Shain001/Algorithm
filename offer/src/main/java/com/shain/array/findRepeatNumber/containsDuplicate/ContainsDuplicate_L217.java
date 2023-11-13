package com.shain.array.findRepeatNumber.containsDuplicate;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate_L217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> occured = new HashSet<>();

        for (Integer n: nums) {
            if (occured.contains(n)) {
                return true;
            }
            occured.add(n);
        }

        return false;
    }

    // method2: 排序 后遍历
}
