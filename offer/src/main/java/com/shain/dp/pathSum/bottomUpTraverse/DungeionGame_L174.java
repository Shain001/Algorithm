package com.shain.dp.pathSum.bottomUpTraverse;

public class DungeionGame_L174 {
    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }

    /**
     * dp含义为 从 i，j 到达终点所需要的最少初始血量， 而非 从 原点到ij所需要的最少血量。
     * <p>
     * 1. 关于为什么要反向遍历：
     * 1） 首先， 在明确了dp数组的含义以后， 自然会想到要反向遍历。
     * 2） 其次， 当正向遍历思考时候， you would notice that you need two 2D arrays to calculate the value (which did think
     * about this), Then you should realize that 当正向遍历时， 对于每个格子的value， 你需要两个信息来计算。 此时你就该意识到应该反向
     * 遍历。
     * 3） 当正向遍历时， 你也会想到dp数组的含义是 当前格子到达下一个格子还不死所需要的最小血量，。 你会发现， 如果你正向遍历， 你需要依赖于 右边
     * 和下边格子的值来计算得到当前格子的值， 不符合无后效性，
     * <p>
     * 2. 关于 dp[-1][-1] 的初始化， 以及 dp方程中， dungeon[i][j] - dp[i][j + 1] 为负数时 需不需要+1 的问题：
     * 1） 首先， 当  dungeon[i][j] - dp[i][j + 1] >= 0 时， 自然需要将其赋值为 1， 这个毫无疑问。
     * 2） 当他小于 0 时， +1 与否取决于 你的 dp[-1][-1] 加没加 1， 如果加了 则dp中不需要+1， 否则需要。
     * 为什么？
     * 其实 dp[-1][-1] 初始化的值的含义是 ： "所期望的到达终点时所剩的血量"， 所以， 假设 当前格子要扣除我 5 点血 （dug = -5）， 我想要到达下一个格子时候
     * 至少还有 1 点血， 那么 -5 - 1 = -6 是不是就正好是你在当前格子出生时所需要的血量？
     * 反之， 如果 dp[-1][-1] 初始成 0， 那么你是不是就需要+1？
     *
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        int init = dungeon[dungeon.length - 1][dungeon[0].length - 1];
        dp[dp.length - 1][dp[0].length - 1] = init >= 0 ? 1 : -init + 1;

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length - 1) {
                    continue;
                }

                if (i == dp.length - 1) {
                    dp[i][j] = dungeon[i][j] - dp[i][j + 1] >= 0 ? 1 : -(dungeon[i][j] - dp[i][j + 1]);
                    continue;
                }

                if (j == dp[0].length - 1) {
                    dp[i][j] = dungeon[i][j] - dp[i + 1][j] >= 0 ? 1 : -(dungeon[i][j] - dp[i + 1][j]);
                    continue;
                }

                dp[i][j] = dungeon[i][j] - Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = dp[i][j] >= 0 ? 1 : -dp[i][j];
            }

        }

        return dp[0][0];

    }
}
