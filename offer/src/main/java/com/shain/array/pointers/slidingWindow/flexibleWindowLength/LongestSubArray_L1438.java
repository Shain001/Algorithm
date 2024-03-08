package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 窗口内元素需要有序的类型。
 * 注意 treemap的使用。
 * 以及单调队列的使用
 */
public class LongestSubArray_L1438 {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        int right = 0;
        int ans = 0;

        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left])-1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            ans = Math.max(ans, right-left+1);
            right++;
        }

        return ans;
    }

    public int twoQueues(int[] nums, int limit) {
        // 维护单调栈是为了始终能够找到当前窗口中的最小值， 或者说 能够使用记录第1小， 第2小。。。
        // 因为， imagine that 当pop左边窗口的时候， 当前窗口的最小值被pop掉了， 那么你要能够找到新窗口中的最小值
        Deque<Integer> max = new LinkedList<>();
        Deque<Integer> min = new LinkedList<>();
        int right = 0;
        int left = 0;
        int ans = 0;

        while (right < nums.length) {
            // 判断当前元素是否是新的最小或者最大

            // 对于 max， 做左到右（左为尾部， 右为头） 元素应该递增， 进而实现 pop出了当前最大元素之后能够得到新的最大元素。
            // 每次添加从 尾部添加， pop从 头部pop， 进而保证 “能够得到新的最大元素”
            // 对于相等的两个元素， queue中pop两次。进而保证在窗口不满足条件时候， 如果窗口中有相等的两个 最大/小 值， 能够正确的吧他们全部pop出窗口
            // 注意的是， 对于min 或 max, 当前窗口的 最大或者最小元素都是在 头部的。
            while (!max.isEmpty() && max.peekLast() < nums[right]) {
                max.pollLast();
            }

            while (!min.isEmpty() && min.peekLast() > nums[right]) {
                min.pollLast();
            }

            // 两个queue中都要有当前元素
            // 因为当前元素在窗口不同时期都有可能是 最大 或者 最小
            max.offerLast(nums[right]);
            min.offerLast(nums[right]);

            // 这个while 中 套if， 就保证了 最大/小的元素 肯定能被pop出去， 就算当前窗口中 有两个 相等的最大元素。
            while (!max.isEmpty() && !min.isEmpty() && max.peekFirst() - min.peekFirst() > limit) {
                int last = nums[left];

                // 因为 min 或 max 中的头部是当前窗口的 最大或者最小元素。
                // 所以pop时候从 头部pop
                if (last == max.peekFirst()) {
                    max.pollFirst();
                }

                if (last == min.peekFirst()) {
                    min.pollFirst();
                }
                left++;
            }

            ans = Math.max(ans, right-left+1);
            right++;
        }
        return ans;
    }
}
