package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注意 int 溢出
 *
 * 注意判断 unique的方法， 直接利用map.size() 判断唯一的元素。 当 value 为0 时， 直接 remove 掉这个key以保证 map.size() 的值就是unique的个数
 * 这里map的使用方法， 即 key为元素， value为元素的个数， 适用于窗口内可以有重复元素的情况。
 * 同时， 这题要求的， 可以有重复， 但是同时又对 不重复元素的个数 有要求， 可以直接使用 map.size() 追踪窗口内不同元素的个数。
 *
 *
 * 注意这里的map的使用方法， 和 L2461 使用方法的区别。
 * 这道题复制粘贴， 把 if (map.size()) 改成 == k 即可通过 L2461， 但是效率较低。
 */
public class AlmostUniqueSum_L2841 {
    public long maxSum(List<Integer> nums, int m, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        long sum = 0;
        long result = 0;

        while (right < nums.size()) {
            map.put(nums.get(right), map.getOrDefault(nums.get(right), 0) + 1);
            sum += nums.get(right);

            if (right - left + 1 == k) {
                if (map.size() >= m) {
                    result = Math.max(sum, result);
                }


                map.put(nums.get(left), map.get(nums.get(left))-1);
                if (map.get(nums.get(left)) == 0) {
                    map.remove(nums.get(left));
                }

                sum -= nums.get(left);
                left++;
            }

            right++;
        }

        return result;
    }
}
