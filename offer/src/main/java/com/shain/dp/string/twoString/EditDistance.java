package com.shain.dp.string.twoString;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("ros", "horse"));
    }

    /**
     * 解释方式/思路：
     * i 为 word 指针， j为target指针。 dp 数组中每个格子表示 使 0-i 与 0-j 相等所需的最小步数
     * 从i， j 长度入手思考：
     * 在计算dp数组过程中， 或者说在移动i，j指针过程中， i，j长度有三种可能性：
     * i = j -> 此时 应进行替换操作为最佳选择 -> 所需步数为： 使 前i-1 与前j-1 位字符相等所需的步数 + 1； e.g. hor -> hos
     * i > j -> 此时， 由于word已经比target长， 所以删除为最佳 -> 所需步数： 使 前 i-1 与 前 j 位字符相等 + 1； -> horaaa -> hor
     * i < j -> 此时， 由于word已经比target短， 所以需添加元素 -> 使 前 i 位字符 与 前j-1 位字符相等+1；-> hor -> horaaa
     * 最后， 三种情况取最小即可。
     * <p>
     * 这种解释能够使你在不看答案的情况下正向想出解法， 但不能完美解释 dp table 。
     * <p>
     * 以下解释能够一贯的解释dp table计算中的状态转移过程。 即 "对于word的添加操作， 等价于对于target的删除操作"
     * e.g. word = hor, target = horse. word添加se 与 target 删除se实际是一样的。
     * 但是在状态转移过程中， 这种解释 代表： 使word变成hos， 与 使 target 变成 hor 的混用 不会影响最终的结果。
     * Specifically，画个dp table 你会发现, 当 i=j 时， dp[i-1][j] 和 dp[i][j-1] 分别代表着使 target变成word的最小步数，
     * 和使word变成target的最小步数。
     * <p>
     * More。
     * 该题的dp table所对应的 状态转移 是： 使 0-i 为字符 从hor 的状态， 变成 0-j 的ros状态。
     * 转移的 方式 有三种： 替换， 添加， 删除。
     * 1. 替换： hor -> ros  ---> hor --1 step--> ros + make ho = ro where steps = dp[i-1][j-1]
     * 2. delete/ delete from word: hor -> ros ----> hor --1 step--> ho -?-> ros; ? = dp[i][j-1]
     * 3. insert/ delete from target:  ros -1-> ro -?-> hor;   ?=dp[i-1][j]
     * <p>
     * 解释dp table的问题就是， 如果按照 对 word 进行插入的理解 解释状态转移方程， 没法解释， 而且也找不到 dp table中对应着
     * 插入 word的格子。 dp[i-1][j] 在列表中对应的意义就是 对于target删除一位， 然后变成跟 word相等所需的步数。
     * <p>
     * 但是这样解释， 就变成了操作target使target=word， 而不是word=target了， 从结果看， 这一计算的interchange不会影响最终结果。
     * 但是需要证明，which too complex to be done.
     * <p>
     * ！突然意识到， 这种解释是没问题的， 因为单看 dp table的作用， 其意义就是 使target 与 word 相等所需要的最小步骤， 没有能够操作谁不能
     * 操作谁的概念。 所以其实就是一样的。
     *
     * @param word   word that needs to be modified
     * @param target target
     * @return
     */
    public static int minDistance(String word, String target) {

        // +1 因为第一行&第一列表示空字符
        int[][] dp = new int[word.length() + 1][target.length() + 1];

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i - 1 < word.length() && j - 1 < target.length() && word.charAt(i - 1) == target.charAt(j - 1)) {
                    // 字符相等， 不需要修改， 修改次数等于两个指针前一位的步数；
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
