package com.shain.array.groupLoop;

public class StockDown_L2110 {
    public long getDescentPeriods(int[] prices) {
        int i = 0;
        long ans = 0;

        while (i < prices.length) {
            int j = i;
            i++;
            while (i >= 1 && i < prices.length && prices[i]-prices[i-1] == -1) {
                i++;
            }

            // 这里是数学规律运算， 等差求和公式 （首项（0） + 末项） * 长度 / 2
            ans += (1+(long)(i-j)) * (long)(i-j) /2;
        }

        return ans;
    }
}
