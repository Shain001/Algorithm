package com.shain.array.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum_L18 {
    /**
     * 与三数之和基本没有区别。
     * 即用了4 个指针：
     * <p>
     * 第一个指针m -> 依次遍历每个数字， 得到一个剩余的 target
     * 然后 对 m+1 - end 数组进行 三数之和查找。
     * <p>
     * 需要注意的三点：
     * 1. 由于 fourSum 的target不是固定的0， 而是一个输入值， 所以 不能在forloop中加入 if (cur > target) break 的剪枝。
     * 因为有可能有负数的情况， 比如 [-5, -3, -2] target = -8. 这个结论在threeSum也适用。 换句话说， 只有规定了target=0 的情况
     * 可以加入剪枝
     * <p>
     * 2. 注意溢出， 即 计算 long curTarget 与 long anotherCurTarget
     * 3. 在这道题中， 在 m+1 - end 中进行threeSum时：
     * if (i > m+1 && nums[i - 1] == nums[i])
     * continue;
     * if 中需要判断的是 i> m+1, 而不是 i>0， 因为 m 指向的数， 与 i 指向的数是可以相等的。 我们要做的是 "保证 指针i所对应的'位置' 这个数字，在result中是不可以重复的"
     * e.g. [2,2,2,2] target=8， 如果写成 if (i >0 ...) 则出错。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int m = 0; m < nums.length; m++) {

            if (m > 0 && nums[m] == nums[m - 1])
                continue;

            long curTarget = target - nums[m];


            for (int i = m + 1; i < nums.length; i++) {
                if (i > m + 1 && nums[i - 1] == nums[i])
                    continue;

                long anotherCurTarget = curTarget - nums[i];
                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {

                    int sum = nums[j] + nums[k];

                    if (sum == anotherCurTarget) {
                        result.add(Arrays.asList(nums[m], nums[i], nums[j], nums[k]));
                        j++;
                        k--;

                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }

                        while (j < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    } else if (sum > anotherCurTarget) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }


        return result;
    }
}
