package com.shain.dp.array.subArray.LIS;

/**
 * 注意， 这题求的是连续有效括号的最大长度。
 *
 *
 * 关于 i - dp[i-1] - 1：
 * 假设我们在位置 i 处有一个闭括号 ')'，并且我们想知道它是否能与之前的某个开括号 '(' 形成一对。
 *
 * 查找可能的开括号位置：如果闭括号 ')' 在位置 i 能够与一个开括号配对，那么这个开括号必须出现在位置 i - 1 处闭括号之前的某个位置。由于 dp[i - 1] 表示在位置 i - 1 结尾的最长有效括号子串的长度，所以我们需要跳过这段长度，以找到可能与位置 i 处的闭括号配对的开括号。
 *
 * 计算配对的开括号的位置：我们从位置 i 向左移动 dp[i - 1] 步，到达位置 i - dp[i - 1]。但这实际上是位置 i - 1 处闭括号子串的第一个字符。我们还需要再向左移动一步，到达位置 i - dp[i - 1] - 1，这个位置就是可能与位置 i 处闭括号配对的开括号的位置。
 *
 * 示例
 * 考虑字符串 "(()())" 和位置 i = 5（最后一个字符）：
 *
 * 在位置 i = 4 处，dp[4] = 4，表示 "(()())" 中的 "(()(" 是有效括号子串。
 * 我们想知道位置 i = 5 处的 ')' 是否可以与之前的某个 '(' 配对。
 * 我们跳过 dp[4] = 4 长度的子串，到达位置 5 - 4 = 1。
 * 我们再向左移动一步，到达位置 1 - 1 = 0，这是可能的配对位置。
 * 在这个例子中，位置 0 处的确是一个 '('，与位置 5 处的 ')' 配对。
 * 这样，我们就能判断出闭括号在位置 i 是否能与之前的开括号形成有效的括号对，从而准确地更新动态规划数组。
 *
 *
 * 关于 + dp[i-2-dp[i-1]]
 *
 * 因为dp数组代表的是 "以 i 结尾的字符串的 最长有效括号长度"
 *
 * 这里要加上 当前有效括号串 前面的子串， 因为有可能前面的子串也是连续有效的
 *
 */
public class LongestValidParentheses_L32 {
    public static void main(String[] args) {
        LongestValidParentheses_L32 test = new LongestValidParentheses_L32();
        System.out.println(test.longestValidParentheses("(()"));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
                continue;
            }

            if (s.charAt(i) == ')') {
                if (i - 1 < 0) {
                    continue;
                }

                if (s.charAt(i-1) == '(') {
                    dp[i] = i-2 >= 0? (2 + dp[i-2]) : 2;
                } else {
                    if (i - dp[i-1] - 1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') {
                        dp[i] = 2 + dp[i-1];
                        dp[i] = i-2-dp[i-1] >= 0? (dp[i] + dp[i-2-dp[i-1]]): dp[i];
                    }
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
