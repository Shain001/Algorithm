package com.shain.dp.twoPointer.oneString;

public class MinimumStepMakePalindrome_L1312 {
    /**
     * dp 表示 使 i-j 变为回文串所需的最小插入个数。实际也相当于最小删除个数。
     * <p>
     * 该题关键在于， 想通 "插入n个字符使一个字符串变成回文串" 中的n的最小值， 实际上就等价于 "删除 n 个字符使一个字符串变成回文串" 中n的最小值。
     * <p>
     * why?
     * e.g.  ab -> aba -> 需插入一个a， "使得a的字符串中a的数量相同"
     * abb -> aabb -> 同样+a, 也是为了使a的数量相同
     * acd -> aacdd -or-> 也是为了使 a, d 的数量相同
     * <p>
     * 不难发现， 如果我们不进行插入操作， 而是进行删除操作， 所需要删除的字符数量其实跟插入数量是相同的。
     * 因为进行插入的目的， "只是为了让一个字符串中不成对的字符变的成对"， 换句话说， "需要插入的字符数量 等于 不成对的字符数量"。
     * 那么， "将这些不成对的字符删除， 剩下的就一定是成对的字符" （或者只有一个 长度为1的字符串， 也即base case）。
     * 所以， 求需要插入的数量就等于求需要删除的数量。
     * 以上面的几个字符做例子
     * <p>
     * ab -> a or b -> 删除a 或 b
     * abb -> bb -> 删除bb  （如果删b， 则变成ab， 则删除b以后， 字符串变成回文的步数， 等于 1+ dp[ab]， 这一结果由于所需步数比 dp[bb] 大， 会被舍弃）
     * acd -> c -> 删除ad (同理， 在代码中， 实际是算出了所有三种删除方法步数， 然后取了最小， 所以最优结果是删除 ad)
     * <p>
     * Specifically, 由于 dp[i][j] = Math.min(Math.min(dp[i+1][j], dp[i][j-1]) + 1, dp[i+1][j-1] + 2);
     * 所以， abb 变成回文串的三种可能性：
     * 1. delete i=a -> bb -> n = 1 + dp[bb] i.e. dp[i-1][j]
     * 2. delete j=b - ab -> n = 1 + 使ab变成回文串所需的步数 dp[ab] i.e. dp[i][j-1]
     * 3. delete both -> b -> n = 2 + 使b变成回文串所需的步数 dp[b] i.e. dp[i+1][j-1]
     * <p>
     * 想通这一点以后， 进行最少删除数量的求解就简单了。
     * 无非是 删除i指针指向的字符， 还是删除j指向的字符， 或者是 i， j都删除三种可能性。
     * <p>
     * 一个结论： 对于所有动态规划的字符串类型题的 插入操作， 都可以等价于删除操作。（may need to double confirm in the future, but
     * so far this is true for all this kind of questions）
     * 如果对于该结论不放心， 至少可以在 题目中 涉及到插入时， 先按照 删除思考验证一下两者能否等价。
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int sLen = s.length();
        int[][] dp = new int[sLen][sLen];

        for (int i = 0; i < sLen; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < sLen; i++) {
            if (i + 1 > sLen - 1) {
                continue;
            }

            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;
        }

        for (int curLen = 3; curLen <= sLen; curLen++) {
            for (int i = 0; i < sLen; i++) {
                int j = i + curLen - 1;

                if (j > sLen - 1) {
                    continue;
                }

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j], dp[i][j - 1]) + 1, dp[i + 1][j - 1] + 2);
                }
            }
        }

        return dp[0][sLen - 1];
    }
}
