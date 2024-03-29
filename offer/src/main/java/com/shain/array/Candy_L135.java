package com.shain.array;

public class Candy_L135 {
    public int candy(int[] ratings) {
        int pre = 1;
        int ans = 0;
        int dec = 0;
        int inc = 1;

        for (int i = 0; i < ratings.length; i++) {
            if (i == 0) {
                ans += pre;
                continue;
            }

            if (ratings[i] >= ratings[i-1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ans += pre;
                inc = pre;
            } else {
                pre = 1;
                dec += 1;
                // 之所以 要记录 上升区间长度， 是因为这个思路其实是：
                // 在 当前 孩子是 下降区间的第一个元素时， 我们直接给他发了1 个
                // 但是如果 再下一个孩子， 比当前元素 更小， 那么我们得给 当前孩子补发一颗糖。
                // 这就是 dec 的作用。
                // 但是， 如果某一个时刻， 递增区间的 最后一个孩子， 也即 当前递减区间的前一个孩子 的糖果数量， 由于 递减区间太长， 导致 递减区间第一个孩子的糖果数量太多，
                // 这时候 原本递增区间的最后一个孩子的数量 就 被 递减区间的 第一个孩子糖果数量 超过了。进而导致了错误
                // 所以 从 inc = dec 的时刻开始， 也就代表着上述所说的那个时刻， 要开始给递增区间的最后一个孩子也补发糖果， 保证他的糖果数量比 递减区间第一个孩子的糖果数量多。
                if (inc == dec) {
                    dec += 1;
                }
                // 这里为什么时 ans 直接. + dec ? 其实就是编程计算导致的。 因为 dec初始是0， 到递减区间第一个元素的时候， 因为上面dec+=1, 这里刚好能得到正确答案。
                ans += dec;
            }
        }
        return ans;
    }
}
