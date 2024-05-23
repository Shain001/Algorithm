package com.shain.array.binarySearch;

public class Divide_L29 {
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }

        if (dividend == Integer.MAX_VALUE) {
            if (divisor == 1) {
                return Integer.MAX_VALUE;
            }
            if (divisor == -1) {
                return Integer.MIN_VALUE + 1;
            }
        }

        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 开始
        // 这里也必须是long， 否则在转换正负号的时候容易溢出。 因为 Integer.Min 是 - 2^31, 但是 Integer.Max 是 2^31 -1
        long m = dividend;
        long n = divisor;
        if (m < 0) {
            m = -m;
        }
        if (n < 0) {
            n = -n;
        }
        int flag = 1;

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            flag = -1;
        }


        // 这里必须是long， 否则下面计算mid的时候会溢出， 导致死循环
        // dividen = 2147483647; advisor = 2
        long left = 0;
        long right = m;

        // 二分结果。 因为 商必在 0 - 被除数 之间。
        // 每次取中间值 * divisor， 看结果是否超过 被除数
        while (left < right) {
            long mid = left + (right - left + 1) / 2;

            if (multiply(mid, n) > m) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return (int) (flag * left);
    }

    /**
     * 用倍增的方式实现a*b, 本质是利用了 二进制的乘法运算方式
     * <p>
     * 通过按位与运算 (k & 1)，我们可以有效地检查 k 的最低位是否为 1，并根据其值决定是否将当前的 a 累加到 ans。这种方法有效地模拟了乘法运算中的逐位累加过程，同时利用了二进制操作的高效性。
     * <p>
     * 为什么 k & 1 == 1 时累加到 ans 就是 a * k 的结果
     * <p>
     * 二进制乘法的原理
     * <p>
     * 在二进制系统中，乘法运算可以通过位移和加法来实现。这与我们在十进制中使用的乘法方法类似，只不过是用 2 的幂来代替 10 的幂。
     * <p>
     * 假设我们要计算 a * k，其中 k 用二进制表示。我们可以将 k 的每一位与 a 相乘，然后将这些结果相加。这是因为任何数都可以表示为二进制数的和。例如：
     * <p>
     * k = 1011₂ = 1 × 2³ + 0 × 2² + 1 × 2¹ + 1 × 2⁰
     * <p>
     * 因此，计算 a * k 就等同于计算：
     * <p>
     * a × (1 × 2³ + 0 × 2² + 1 × 2¹ + 1 × 2⁰)
     * <p>
     * 逐位累加的过程
     * <p>
     * 在代码中，我们通过按位与运算 (k & 1) 来检查 k 的每一位：
     * <p>
     * 最低位检查：
     * <p>
     * k & 1 检查 k 的最低位。如果最低位是 1，则表示当前位对最终结果有贡献。
     * 如果最低位是 1，则将当前的 a 累加到 ans 中。
     * 位移操作：
     * <p>
     * 将 k 右移一位 (k >>= 1)，以检查下一位。
     * 将 a 左移一位 (a += a)，以反映二进制位移的结果（相当于 a 乘以 2）。
     * 通过这种方式，我们逐位处理 k 的每一位，并根据其值决定是否将当前的 a 加到 ans。这样，我们逐位累加了 a 的倍数，最终结果就是 a * k。
     * <p>
     * 例子
     * <p>
     * 假设 a = 3，k = 5（二进制：101）：
     * <p>
     * 初始状态：ans = 0，a = 3，k = 5
     * <p>
     * k & 1 = 1（最低位是 1），所以 ans += a，ans = 3
     * <p>
     * 将 k 右移一位：k = 2（10）
     * 将 a 左移一位：a = 6
     * k & 1 = 0（最低位是 0），所以 ans 不变
     * <p>
     * 将 k 右移一位：k = 1（1）
     * 将 a 左移一位：a = 12
     * k & 1 = 1（最低位是 1），所以 ans += a，ans = 15
     * <p>
     * 将 k 右移一位：k = 0
     * 将 a 左移一位：a = 24（这个值不再用到，因为 k 已经是 0）
     * 最终，ans 的值是 15，即 3 * 5 的结果。
     */
    private long multiply(long a, long b) {
        long ans = 0;

        while (b > 0) {
            if ((b & 1) == 1) {
                ans += a;
            }
            b = b >> 1;
            // 这里的 a += a 实际上应该 更准确的表达为 a *= 2;
            // 原因看上面的解释。
            a += a;
        }

        return ans;
    }
}
