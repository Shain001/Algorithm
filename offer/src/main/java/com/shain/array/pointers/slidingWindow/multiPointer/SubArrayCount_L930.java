package com.shain.array.pointers.slidingWindow.multiPointer;

import java.util.HashMap;
import java.util.Map;

public class SubArrayCount_L930 {
    /**
     使用三指针。 重点在于理解， 当找到一个 窗口， 满足和为 goal时。
     窗口内可能包含了不只一个满足条件的集合。 所以在找到一个窗口的时候
     要在窗口内， “从left指针开始， 找到所有 ‘当right等于当前值时满足条件的子集’”
     即， 当前窗口满足条件时， 在窗口内， 移动left并记录满足条件的子集， 直到找到当前窗口内所有子集。
     然后移动right。 继续这一操作。
     只要right移动了， 那么所记录到的子集就绝对不会有重复， 因为 子集的右端点变了。
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int right =0;
        int left = 0;
        int count = 0;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            int tempSum = sum;
            int tempLeft = left;
            while (tempLeft <= right && tempSum == goal) {
                count++;
                tempSum -= nums[tempLeft];
                tempLeft++;
            }

            right++;
        }

        return count;
    }

    /**
     * 前缀和方法。 更具有普遍性。 值的学习， 以下代码复制的。
     *
     * 假设原数组的前缀和数组为 sum，且子数组 (i,j] 的区间和为 goal，那么 sum[j]−sum[i]=goal， 因此我们可以枚举 j ，每次查询满足该等式的 i 的数量。
     *
     * 具体地，我们用哈希表记录每一种·前缀和· 出现的次数，假设我们当前枚举到元素 nums[j] 我们只需要查询哈希表中元素 sum[j]−goal 的数量即可
     * @param nums
     * @param goal
     * @return
     */
    public int prefixSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }
}
