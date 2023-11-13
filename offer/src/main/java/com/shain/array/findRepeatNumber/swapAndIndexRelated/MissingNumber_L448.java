package com.shain.array.findRepeatNumber.swapAndIndexRelated;

import java.util.ArrayList;
import java.util.List;

public class MissingNumber_L448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]-1) {
                result.add(i+1);
            }
        }

        return result;
    }
}
