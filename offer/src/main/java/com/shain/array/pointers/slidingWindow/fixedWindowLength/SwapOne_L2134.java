package com.shain.array.pointers.slidingWindow.fixedWindowLength;

/**
 * 1） 注意循环数组的窗口处理。 while 中 变成了 left 判断， 同时判断窗口大小的时候也需有变化。
 * 2） 最主要注意判断问题是要使用滑动窗口。 注意问题的转换。 这题用滑动窗口是因为任意两个数字都可以交换， 不用是相邻的元素才能交换。 所以 交换的次数
 *     实际就是 窗口中0的最小个数。 那么只需要得到数组中 1 的总数量， 就是window size。 然后 找到0的最小个数（等同于 1 的最大个数， 以下写法是找 1 的最大个数） 即可。
 */
public class SwapOne_L2134 {
    public int minSwaps(int[] nums) {
        int window = 0;

        for (Integer n: nums) {
            window = n == 1? window+1: window;
        }

        // 这种写法处理不了window=0的情况， while会死循环。 必须有这个if
        if (window == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int countOne = 0;
        int maxOne = 0;

        while (left < nums.length) {
            int rightMod = right % nums.length;
            if (nums[rightMod] == 1) {
                countOne++;
            }

            if (right - left + 1 == window || rightMod+1 + nums.length-left == window) {
                maxOne = Math.max(maxOne, countOne);
                if (nums[left] == 1) {
                    countOne--;
                }
                left++;
            }

            right++;
        }

        return window-maxOne;
    }
}
