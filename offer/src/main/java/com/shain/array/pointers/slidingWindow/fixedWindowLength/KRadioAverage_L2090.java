package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.Arrays;

public class KRadioAverage_L2090 {
    public int[] getAverages(int[] nums, int k) {
        if (k == 0) {
            return nums;
        }
        long curSum = 0;
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        if (2 * k + 1 > nums.length) {
            return result;
        }

        for (int right = 0; right < nums.length; right++) {
            int left = right - 2*k;
            int i = right - k;
            curSum += nums[right];

            // 注意运算精度问题
            if (left >= 0) {
                long avg = curSum / (2L * k + 1); // 先进行除法操作
                result[right - k] = (int)avg; // 将结果赋值给中间位置
                curSum -= nums[right - 2 * k]; // 移除窗口最左边的元素
            }
        }

        return result;
    }
}
