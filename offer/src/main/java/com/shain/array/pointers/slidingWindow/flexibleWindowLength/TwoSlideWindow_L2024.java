package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

/**
 * 使用两次滑动窗口。
 *
 * 不能是简单的将问题转化为："    // 记录窗口内 t f 的m， n。 找到 m-n 的绝对值 < k 的最长窗口"
 *
 * 因为 （chatGPT）：
 * 使用您原始代码的方法（直接计算'T'和'F'的数量差并比较与k的关系）可能不总是能正确地解决问题，主要是因为它没有准确地处理将一种字符全部转换为另一种所需的最少操作次数。您的方法在某些情况下可能无法正确判断何时需要滑动窗口。
 *
 * 考虑以下例子：
 *
 * answerKey = "TFTFTFTF"，k = 1
 * 按照您的方法，我们计算'T'和'F'的数量差，当这个差值绝对值大于k时，我们尝试调整窗口。但是，对于这个特定的字符串，不管是选择将'T'转为'F'还是将'F'转为'T'，最多只能做一次操作，所以我们在实际上是在寻找连续的'T'或者'F'的最长序列，这个序列内最多只包含一个不同的字符。
 *
 * 以k = 1为例，如果我们从左到右扫描：
 *
 * 初始left = 0, right = 0, 此时窗口内是"T"，满足条件。
 * right移动到1，窗口内是"TF"，满足条件（我们可以将一个'F'变为'T'）。
 * right移动到2，窗口内是"TFT"，此时不满足条件，因为我们需要将两个'F'都变为'T'才能使窗口内字符一致，超过了k的限制。
 * 根据您的逻辑，一旦Math.abs(t-f) > k，您会移动left指针尝试减少'T'和'F'的数量差。但是，这种处理方式没有考虑到只有当新的字符加入窗口时才可能需要调整窗口来保持最少的转换次数，且窗口的移动应该基于保持k次转换能覆盖的最长子串，而不仅仅是简单地基于数量差。
 *
 * 正确的逻辑是，不管当前字符是什么，都将其加入窗口，并检查如果将窗口内非主导字符（即较少的字符）全部转换为主导字符所需的操作次数是否超过k。如果是，则移动左边界直到转换次数不超过k为止。这样，每一步都保证了窗口内的字符通过不超过k次操作可以变得一致，并且尽可能扩大窗口。
 */
public class TwoSlideWindow_L2024 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(countFlip('T', answerKey, k), countFlip('F', answerKey, k));
    }

    private int countFlip(char target, String answerKey, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int ans = 0;

        while (right < answerKey.length()) {
            char cur = answerKey.charAt(right);
            count = cur == target? count+1: count;

            while (count > k) {
                char l = answerKey.charAt(left);
                count = l == target? count-1: count;
                left++;
            }

            ans = Math.max(ans, right-left+1);
            right++;
        }

        return ans;
    }
}
