package com.shain.dp.knapsack.zeroOne;

import java.util.Arrays;

public class TargetSum_L494 {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1}, 1));
    }

    /**
     * key point:
     * 1. 首先正常思考dp方程， you will notice that to calculate dp[i][j], you would need value of dp[i][j-n] and dp[i][j+n],
     * which is not possible to get all of them, because you can only update the dp array from one direction.
     * <p>
     * 2. Actually, 这个问题可以被转换成在nums 中寻找 target=(sum-target) / 2 的所有元素组合的数量。
     * （或者寻找target = (target + sum[nums])/2， 两个target的值都可以， 但是按前者理解更好， 因为方便输入检查， 详见下文）
     * <p>
     * <p>
     * 数组中只有对于每个数有正负两种状态， 也即， 对于每一种sum=target的组合， 组合中的数 要么正， 要么负。
     * 那么不妨把 正负元素分成两组， then you will get an equation like this:
     * <p>
     * eq1. sum[positive] + sum[negative] = target, where sum[p]>0, sum[n]< 0.
     * <p>
     * This equals to:
     * eq1.1 sum[p]-sum[n] = target. 注意， 此时， sum[n] 已经变成了正数。 这一转换相当于 1 + (-1+-1) = 1 - (1+1)
     * <p>
     * Now you can get:
     * eq1.2 sum[p] = target + sum[n] (NOTE: 此时 sP, sN 均 > 0)
     * <p>
     * 此时， 你的目标是消除 sum[n], so what can sum[n] be converted to?
     * 其实， 当 sum[p] 被 当成正数以后， you can get one more equation:
     * eq2. sP + sN = sum[nums]， 即， sp + sn = 所有元素总和。
     * <p>
     * Combine eq1, eq2:
     * <p>
     * sp = target + sn  ==> sp = target + sum[nums] - sp ==> 2*sp = target + sum[nums]  ==> sp = (target + sum[nums])/2
     * <p>
     * update:
     * 上面的推导过程， 如果把等号左边换成sN 进行推导， 可得出 sN = (sum-target) / 2
     * 而利用sN的值进行求解更好， 因为更好进行输入的检查。 如果按照sP进行求解， 容易想到 sum+target需要为偶数， 但是， what if 输入 nums=[100], target = -200?
     * 此时dp数组的列数会被初始化成负数， 所以为了更好的理解，并且避免这种情况， 利用sN 判断， 可以很容易想到 sum-target 需要是偶数，
     * <p>
     * 并且 sum-target 需要大于0， 为什么？ 因为上式的推导过程中， 已经把 sumNegative 转换成了正数进行推导。 see line 28
     * <p>
     * <p>
     * 理解成， 求子集和等于 sp 的问题更好， 因为这样利于进行输入判断。
     * <p>
     * 此时， 该问题已经被转换成: "在一个数组中， 是否存在n个 sum=(target + sum[nums])/2 的子集, 并且每个元素只可被选择一次"， which is exactly
     * a standard knapsack problem.
     * <p>
     * <p>
     * More of reflection:
     * 该题不能初始化dp长度为 n * target+1。 必须是 n+1 * target+1.
     * 因为这题的元素取值范围包括0， 即元素中可能有0。
     * 因此， 如果 将dp的第0行对应 第0个元素， 那么在初始化 dp0的时候 dp[0][nums[0]] = 1; 就可能漏判可能性。
     * e.g. 第一个元素为0， target为0， 0 能够有几种方式 +- 到0？ 2种， 0+0 或 0-0， 所以dp的初始化发生了错误。
     * 因此需要初始化dp为n+1行， 第0行代表不选则元素， 那么不选择元素， 得到和为0， 则只有一种可能性。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // 这种check has problem, it will wrongly return when input = [1], 1
//        if ((Arrays.stream(nums).sum() + target) % 2 != 0){
//            return 0;
//        }
//
//        int newTarget = (Arrays.stream(nums).sum() + target) / 2;

        int sum = Arrays.stream(nums).sum();

        if ((sum - target) % 2 != 0 || sum - target < 0)
            return 0;

        int newTarget = (sum - target) / 2;

        int[][] dp = new int[nums.length + 1][newTarget + 1];
        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (j - nums[i - 1] < 0) {
                    // wrong2： missed line62， 这里属于理解的不到位， 当j-nums[i-1]< 0, 其实代表着当前的num无法被选中，
                    // 也即意味着 他只能有 不被选择一个状态， 那么dp值就应该等于dp[i-1][j]
                    // 之所以你会漏掉line63， 是因为你只想着判断数组超界了， 而忘了考虑超界的意义。
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                // wrong1: should be dp[i][j-nums[i-1]] 代表每个元素可以被重复选择， 错了。
//                dp[i][j] = dp[i-1][j] + dp[i][j-nums[i-1]];
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
            }
        }


        return dp[dp.length - 1][dp[0].length - 1];
    }
}
