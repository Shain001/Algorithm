package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 计数排序 与 sliding window 的结合应用。
 * 这题之所以能够用 计数排序， 因为 题目中 给了 nums中元素范围在 -50 到 50 之间。
 * 关于计数排序， 如果忘了的话 看 算法导论 p109
 * 计数排序的优点在于 时间复杂度是 O(n)
 *
 * 对于滑动窗口问题， 意识到 窗口中的元素需要进行排序的时候， 例如这道题 需要找 第x小， 就该想一想能不能用 线性时间排序的算法。
 *
 *
 * 如果没有给 -50 到 50 的条件， 则应该用 两个pq做，一个low， 一个high， 此时类似与找中位数。
 * 但是不同的是， 需要从pq中删除元素。 由于 pq 不支持删除特定元素的元素， 所以要用一个map记录待删除的元素。
 * 没写出来， 略了。
 */
public class CountSortWithSlidingWindow_L2653 {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        final int RANGE = 50;
        int[] count = new int[RANGE * 2 + 1];
        int[] ans = new int[nums.length-k+1];
        int left = 0;
        int right = 0;
        int p =0;

        while (right < nums.length) {
            // 增加当前element的计数， count下标即为 nums 中element的值
            // 注意下标的映射.
            count[nums[right]+RANGE]++;

            if (right - left + 1 == k) {

                // 开始在 count 数组中找 第x小的数，count中每个元素的值为 在nums 数组中， 等于 valueOfIndex  的个数
                int countTemp = 0;
                for (int i = 0; i < count.length; i++) {
                    countTemp += count[i];
                    if (countTemp >= x ) {
                        // i 即为当前找到的第x小的数
                        if (i - RANGE < 0) {
                            ans[p++] = i - RANGE;
                        } else {
                            p++;
                        }
                        break;
                    }
                }

                // 开始pop left， 只需在 count数组中 对 nums[left]-1 即可
                count[nums[left]+RANGE]--;
                left++;
            }

            right++;
        }

        return ans;
    }
}
