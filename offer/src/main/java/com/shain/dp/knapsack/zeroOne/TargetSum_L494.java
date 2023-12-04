package com.shain.dp.knapsack.zeroOne;

import java.util.Arrays;

public class TargetSum_L494 {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{0, 0}, 0));
    }

    /**
     * update at 25/06/2023:
     * <p>
     * 1. 首先， 这道题在正常拿过来的情况下， 你至少应该意识到它是一道 0-1 背包问题；
     * why？ 因为问题形式为： 在 数组 0-i 区间内， 你能否得到和为target的组合， 并且每个元素只能选择一次。
     * <p>
     * 2. 那么， 在意识到0-1 背包以后， 自然你应该想到， dp 数组 的每个格子意义为： 0-i 区间内能不能得到 sum=j 的组合 （这道题记录的是个数，而非true false， 但是我们暂时忽略这个问题）
     * <p>
     * 3. 好， 那么对于这道题来说， 我们当然可以初始化数组 行数为 nums.length； 但是你会意识到， 对于第一行元素， 即区间 0-0， 数组中的第一个 1， 他有两种可能和， -> 1 & -1, 但是
     * 数组的下标只能表示正数， 你无法记录 dp[0][-1] = true, 那怎么办？ 你想到将 "整个坐标轴平移"， 即 将dp的列数 从 "sum 变成 2* sum"， 相当与将 j 与 sum 值进行了一个映射。
     * <p>
     * 这个思路没错， 而且能做 （have some details need to be noticed though）
     * <p>
     * 法2：
     * <p>
     * You had the feeling that this question might be able to do some tricky conversions to make it simpler, which is good.
     * 这个转换方法，就是原本以下这一长串题解， 写的略麻烦， 其实很简单。
     * 你有一串1  [1,1,1,1,1,1]   每个 1 前面可以是 +， -    你的目标就是添加正负号， 计算等于 target 的组合数量。 那么你实际就是将 所有的 1 分成了两组。
     * 一组都是 +号， 一组都是 -号。 即：
     * <p>
     * (1 + 1 + 1+...) + (-1 -1 -1 -1 ... ) = target
     * <p>
     * 提个负号：
     * <p>
     * (1 + 1 + 1 + ... ) - (1 + 1 + 1...) = target
     * <p>
     * 发现没有， 现在两组 1 都是 正号了。 相当于： Sum(positive) - Sum(negative) = target. 且 Sp, Sn 都是正数。
     * 并且： "两组1 的总个数就是数组中 所有1 的总个数" which means Sp + Sn = Sum(nums)
     * <p>
     * 进而 Sp = Sum - Sn
     * 又因为 Sp - Sn = target
     * <p>
     * => Sum - Sn - Sn = target => Sum - 2Sn = target => sn = (Sum-target) / 2
     * <p>
     * 至此， 问题转化成了一个 正常的 0-1， 你无需再像方法1 一样增大dp数组的长度。
     * <p>
     * <p>
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
     * eq1.1 sum[p]-sum[negative] = target. 注意， 此时， sum[negative] 已经变成了正数。 这一转换相当于 1 + (-1+-1) = 1 - (1+1)
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
     *
     *
     * update 4/12/2023
     * 不要把dp方程写成 1 + dp[i-1][j-n] + dp[i-1][j]
     *
     * 当选取n时， 你的目的变成了 在i-1 至 0 这个区间中 找到目标和为 j-n 的组合。 这个组合数是不应该+1的，
     * 想象一下： 1+2+3 找sum = 6。 到了3 以后， target变成3。 1+2 有一种组合变成3。 那么到了 6 这。 按照错误的dp方程， 组合数变成了2。 是不对的。
     *
     * @param nums
     * @param
     * @return
     */

    public static int findTargetSumWays_m2(int[] nums, int s) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;

        int len = nums.length;
        // - 0 +
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = Math.max((j - nums[i]), 0);
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + s];
    }


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
