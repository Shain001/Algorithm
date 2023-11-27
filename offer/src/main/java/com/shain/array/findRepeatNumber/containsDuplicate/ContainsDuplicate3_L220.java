package com.shain.array.findRepeatNumber.containsDuplicate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// todo: 看 treeSet & bucket -> 手写treeSet; 桶排序
public class ContainsDuplicate3_L220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> window = new TreeSet<>();

        int left = 0;
        int right = 0;

        while (right < nums.length) {
            while (right - left <= indexDiff && right < nums.length) {
                int cur = nums[right];

                if (window.ceiling(cur) != null && Math.abs(window.ceiling(cur) - cur) <= valueDiff) {
                    return true;
                }

                if (window.floor(cur) != null && Math.abs(window.floor(cur) - cur) <= valueDiff) {
                    return true;
                }

                window.add(cur);
                right++;
            }

            window.remove(nums[left]);
            left++;
        }

        return false;
    }

    // 利用桶排序思想
    // https://leetcode.cn/problems/contains-duplicate-iii/solutions/726905/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-dlnv/
    // 官方题解和上面的题解讲的很清楚。
    // 几个补充的：
    // 1. 为什么 要bucket.remove(getBucketId(nums[i - indexDiff]));
    // 为了满足 indexDiff 的条件。 因为桶中 是没有 index这一个信息的， 很可能一个满足条件的桶虽然存在， 但是由 index不满足条件的元素来的。
    // 2. getId 为什么是 n/size? -> 其实这个桶排序， 就是在一个坐标轴上， 找到 当前元素应该落到几号区间。 那么自然一除就行了。
    // 3. 为什么 n < 0 时， return n / bucketSize - 1 ? 因为负数除法的时候会 向0 取整， 所以得到的区间号是比实际大1 的。
    private int bucketSize;
    public boolean bucket(int[] nums, int indexDiff, int valueDiff) {
        this.bucketSize = valueDiff+1;
        Map<Long, Long> bucket = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            long bucketId = getBucketId(nums[i]);

            if (bucket.containsKey(bucketId)) {
                return true;
            }

            if (bucket.containsKey(bucketId-1) && Math.abs(bucket.get(bucketId-1)-nums[i]) <= valueDiff) {
                return true;
            }

            if (bucket.containsKey(bucketId+1) && Math.abs(bucket.get(bucketId+1)-nums[i]) <= valueDiff) {
                return true;
            }

            bucket.put(bucketId, (long) nums[i]);

            if (i >= indexDiff) {
                bucket.remove(getBucketId(nums[i - indexDiff]));
            }
        }
        return false;
    }

    private long getBucketId(long n) {
        if (n > 0) {
            return n / bucketSize;
        }

        return n / bucketSize - 1;
    }
}
