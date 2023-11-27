package com.shain.array.findRepeatNumber.containsDuplicate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// todo: 看 treeSet & bucket -> 手写treeSet; 桶排序

/**
 * 桶排序算法及其变体，如水塘抽样和计数排序，是非常有用的算法，适用于多种类型的问题。以下是一些适用桶排序思想的典型场景：
 *
 * 处理大数据流或大量数据：当数据量太大，无法一次性全部装入内存时，桶排序可以高效地处理数据。例如，在大数据应用中，可以将数据分散到多个桶中，每个桶负责处理一部分数据。
 *
 * 数据分布均匀时的快速排序：如果数据分布比较均匀，桶排序可以提供非常高的排序效率。通过将数据分布到多个桶中，每个桶内的数据量变小，可以使用其他排序算法（如快速排序或归并排序）对每个桶中的数据进行排序。
 *
 * 近似排序问题：对于需要找到近似排序位置的元素（如LeetCode 220题中的问题），桶排序能够有效地解决。通过桶的划分，可以快速定位到可能存在近似排序元素的区域。
 *
 * 分布式排序或并行处理：桶排序可以很容易地进行并行处理。每个桶可以独立地在不同的处理器或机器上进行排序，然后将结果合并。这使得桶排序非常适合分布式系统或多核处理器。
 *
 * 负载均衡：在某些情况下，如网络流量分析或数据库分区，桶算法可以用来进行负载均衡。通过将数据或任务均匀地分配到多个桶中，可以有效地分散处理压力。
 *
 * 统计和数据分析：桶排序思想也可以用于数据分析和统计学中，特别是在需要对数据进行分桶或分位数分析时。例如，在计算直方图或累积分布函数时，桶算法提供了一种有效的方法来组织数据。
 *
 * 桶排序算法的关键优势在于其高效性和易于并行化，但其性能强烈依赖于数据的分布情况。如果数据分布不均匀，桶排序的效率可能会大大降低。因此，在选择使用桶排序或其变体时，了解数据的特点和分布非常重要。
 */
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
