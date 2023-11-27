package com.shain.array.findRepeatNumber.singleNumberSeries;

// 有限状态机， 看blog
public class SingleNumber2_L137 {
    public int singleNumber(int[] nums) {
        int low = 0;
        int high = 0;

        for (Integer n: nums) {
            low = low ^ n & ~high;
            high = high ^ n & ~low;
        }

        return low;
    }
}
