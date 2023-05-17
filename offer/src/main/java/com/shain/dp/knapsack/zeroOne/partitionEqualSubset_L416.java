package com.shain.dp.knapsack.zeroOne;

import java.util.Arrays;

public class partitionEqualSubset_L416 {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    /**
     * 关键在于对问题的转化， 所谓能分成 和相等的两组数， 即代表数组中是否存在 和 等于 数组所有元素的和的一半的子集。
     * <p>
     * dp方程：
     * 对于每一个数组中的数，也即当前遍历到的数， 只有两种选择， 被选中与不被选。
     * 假设当前计算到 5 （index=1）， nums = [1, 5, 11, 5], 当前target = 11 （即j=11）
     * 如果被选， 则 11 - 5 = 6。 需要判断的条件变成 0- i-1 的下标中， 有没有元素相加能够得到6？ 也即 dp[i][j] = dp[i-1][j-nums[i]]
     * 因为选中5以后， 当前的这个 5 这个元素已经不能再被选中， 所以可选择范围变成了 0-i-1， target变成了6；
     * <p>
     * 如果不选当前的 5，则 可选择的下标范围变成了 0 - i-1 ， 即需判断的下标范围依旧变成了 0-i-1， 而由于不选当前的5， 需要找的target 依然为11。
     * <p>
     * 关于第0列应不应该初始化成 true：
     * 对于此题， 从代码以及代码的运行结果角度讲， 都可以。
     * 但是 从dp数组的定义， 以及题目条件来讲， 初始化为false 更严谨。 因为 在都是正整数的条件下， 不可能有元素相加=0。
     * 从这里来说， 其实第0行不加都可以， 只不过编码写起来不太方便。
     * <p>
     * More reflection from the wrong version:
     * 如果一个元素可以被重复使用 （actually this is exactly what does unbounded knapsack mean, i.e. 完全背包）， 则：
     * 使 dp方程变为： dp[i][j] = dp[i][j-nums[i] || dp[i-1][j];
     * <p>
     * 不难发现， 其与0-1背包有什么区别？
     * 1） 当 当前元素被选择时， dp方程从 dp[i-1][j-nums[i]] 变成了 dp[i][j-nums[i]; 因为就算选择了当前元素， 可选择范围还是 0-i。
     * <p>
     * <p>
     * update：
     * 初始化成 dp 为 n+1行是可以的， 代表 "一个元素都不选， '能够' 得到和为0"
     * 这种理解， 从事实层面上理解， 是不严谨的， 但是能够得到正确结果。 因为 题目给了输入的constraints 是 nums中不含0。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0 || nums.length == 1)
            return false;

        // dp 表示数组中得数能否想加得到 i
        // 长度为sum+1 因为0为baseCase
        // todo: 验证是否初始化成 nums.length+1, 以保持跟完全背包一致方便记忆。
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        if (nums[0] < dp[0].length) {
            dp[0][nums[0]] = true;
        }

        // wrong: 这样相当于每个数组中的元素可以被多次使用。
//        for (int i = 1; i < dp.length; i++) {
//            for (Integer n: nums) {
//                if (i-n >=0 && !dp[i])
//                    dp[i] = dp[i-n];
//            }
//        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static boolean canPartition_cleanAndUpdateVersion(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0 || nums.length == 1)
            return false;

        // dp 表示数组中得数能否想加得到 i
        // 长度为sum+1 因为0为baseCase
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];

        // 这样初始化， 则下面for循环中可以通过 dp[i][j] = dp[i-1][j-nums[i]] || dp[i-1][j]; 判断ij可不可以是true。
        // 代码写起来更方便， 但是不严谨。
        // 因为 dp 数组的意义为 0-i下标之间的元素， 任意数组合能不能得到和为j。
        // 显然数组中数字都是正整数的情况下， 无法得到0为和。
        // 所以去掉该行， 且在下面的for循环中特判 dp[nums[i]]=true
//        for (int i = 0; i < dp.length; i++) {
//            dp[i][0] = true;
//        }

        if (nums[0] < dp[0].length) {
            dp[0][nums[0]] = true;
        }

        // wrong: 这样相当于每个数组中的元素可以被多次使用。
//        for (int i = 1; i < dp.length; i++) {
//            for (Integer n: nums) {
//                if (i-n >=0 && !dp[i])
//                    dp[i] = dp[i-n];
//            }
//        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 由于初始化的修改， 加上此判断。
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }

                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
