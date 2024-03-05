package com.shain.array.pointers.slidingWindow.fixedWindowLength;

/**
 * 注意 逆向思维
 */
public class MaxAvailablePoint_L1423 {
    public int maxScore(int[] cardPoints, int k) {

        long sumAll = 0;
        for (Integer n : cardPoints) {
            sumAll += n;
        }

        // 如果不想要这个if， 可以将 curSum 初始化为 第一个窗口内所有元素的和。 即 nums.len-k，但是这样的话需要更新while内的逻辑。
        // 看func2 chatgpt的代码。
        if (k == cardPoints.length) {
            return (int) sumAll;
        }

        int left = 0;
        int right = 0;
        long minLeft = Long.MAX_VALUE;
        long curSum = 0;

        while (right < cardPoints.length) {
            curSum += cardPoints[right];

            if (right - left + 1 == cardPoints.length-k) {
                minLeft = Math.min(minLeft, curSum);
                curSum -= cardPoints[left];
                left++;
            }
            right++;
        }

        return (int) (sumAll - minLeft);
    }

    public int maxScore_noIf(int[] cardPoints, int k) {
        long sumAll = 0;
        for (int n : cardPoints) {
            sumAll += n;
        }

        int n = cardPoints.length;
        int windowSize = n - k; // 计算不被选中卡牌的窗口大小
        long curSum = 0;
        long minSum = 0;

        // 初始化为前windowSize个卡牌的和
        for (int i = 0; i < windowSize; i++) {
            curSum += cardPoints[i];
        }
        minSum = curSum;

        // 滑动窗口，寻找最小和
        for (int i = windowSize; i < n; i++) {
            curSum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, curSum);
        }

        return (int) (sumAll - minSum);
    }

}
