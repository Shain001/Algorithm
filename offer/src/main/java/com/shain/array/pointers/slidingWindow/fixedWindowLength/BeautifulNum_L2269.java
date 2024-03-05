package com.shain.array.pointers.slidingWindow.fixedWindowLength;

/**
 * 注意 截取 int类型的数字 以及 计算 int类型数字的长度方法即可。
 */
public class BeautifulNum_L2269 {
    private int len;

    public int divisorSubstrings(int num, int k) {
        int count = 0;
        this.len = getLen(num);
        int left = 0;
        int right = k;

        while (right <= len) {
            int cur = cut(num, left, right);
            count = cur != 0 && num % cur == 0 ? count + 1 : count;
            right += 1;
            left += 1;
        }

        return count;
    }

    /**
     * 除法操作：首先，num被除以10^left。这个操作的目的是去除num中从最低位开始到第left位的所有数字。例如，如果num = 123456且left = 2，那么num / 10^2 = 1234。这一步将num截断为前left位之后的数字。
     * <p>
     * 模运算：接下来，上一步得到的结果再对10^(right-left)取模。这个操作的目的是从当前结果中保留最低的right-left位数字，同时去除更高位的数字。继续上面的例子，如果right = 5，那么right-left = 3，我们有1234 % 10^3 = 234。这样，我们就得到了从左数第left+1位到左数第right位的子数字。
     *
     * @param num
     * @param left
     * @param right
     * @return
     */
    private int cut(int num, int left, int right) {
        int result = num / (int) Math.pow(10, left);
        result %= (int) Math.pow(10, right - left);

        return result;
    }

    private int getLen(int num) {
        int len = 0;

        while (num != 0) {
            num /= 10;
            len++;
        }

        return len;
    }
}
