package com.shain.dp.twoPointer.twoString;

public class DeleteMakeSame_L583 {
    public static void main(String[] args) {
        System.out.println(minDistance_v2("sea", "eat"));
    }

    /**
     * 通过计算 最大公共子序列， 间接的得到最小删除步数。
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        // dp 为 0-i， 0-j 之间最大公共字符数
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        // i -> word1, j -> word2
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return word1.length() + word2.length() - 2 * dp[dp.length-1][dp[0].length-1];
    }

    /**
     *      直观的动态规划方法。
     *      思考过程：
     *      1. 首先明确： dp 为 使得 0-i, 0-j 相等所需要的最小步数。
     *      2. 其次思考， 对于每两个遍历到的字符， 对其能进行什么操作？ 答： 只有删除。
     *      3. 再次， 如果删除， 当前的步数应该等于什么？
     *         答： "由于目的是使得删除后两个字符串相同"， 并且应该取最小步数， 所以应计算： 当前删除所需的步数 + 使得删除以后的字符串相等的最小步数。
     *      4. 对于删除这个操作而言， 有哪几种删除方式？答： 要么只删除一个字符， 要么两个字符都应该删除。
     *      5. 那么， 对于 每两个单个字符， 如果相等， 则"不需要删除"， 步数不变， 即等于 使得 0-i-1 和 0-j-1 所需的最小步数
     *         如果不相等，则需要删除， 进而有以下三种 状态转换的方式， 也即 使0-i, 0-j 相等的方式：
     *          1. 只删除一个字符， 步数+1， 删除word1还是word2， 取决于 dp[i-1][j] 和 dp[i][j-1] 哪个小。
     *              解释： 只删除一个字符， 代表要么从word1删除，要么从word2删除
     *              以从word1 删除为例， 删除当前字符后， 剩余字符为 0-i-1, word2 还是j个， 那么此时要让0-i-1 与 0-j 相等所需的
     *              最小步数则为 dp[i-1][j]。
     *          2. 两个都删除， 步数+1， 此时所需步数为 2+ dp[i-1][j-1]
     *          3. 比较 以上三种可能性得到最小值则为dp[i][j] 的值。
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance_v2(String word1, String word2) {
        // 使得 0-i, 0-j 相等所需要的最小步数。
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        // i -> word1, j -> word2
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = getMin(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]);
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    private static int getMin(int removeL, int removeBoth, int removeR) {
        int temp = Math.min(removeL, removeR) + 1;
        return Math.min(temp, removeBoth+2);
    }
}
