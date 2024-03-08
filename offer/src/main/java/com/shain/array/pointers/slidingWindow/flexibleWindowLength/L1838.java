package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

import java.util.Arrays;

public class L1838 {
    // 官方题解
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            // 注意理解这里， 很巧妙。
            // right从0 到1， nums[0] 要加到 nums[1] ， 现在相当于 窗口内所有的元素都变成 nums[1]了。
            // right 从 1 到 2， 要把元素中所有元素都变成 nums[2]. 又因为现在所有元素都是 nums[1]了， 那么只需要把 total + 差值*窗口总大小
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
