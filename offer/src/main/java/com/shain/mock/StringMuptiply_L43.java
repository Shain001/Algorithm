package com.shain.mock;

public class StringMuptiply_L43 {
    public static void main(String[] args) {
        var test = new StringMuptiply_L43();
        System.out.println(test.multiply("3", "2"));
    }
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1 = num1.length();
        int n2 = num2.length();
        int[] ans = new int[n1 + n2];

        for (int i = n1-1; i >= 0; i--) {
            int cur1 = num1.charAt(i) - '0';

            for (int j = n2-1; j >= 0; j--) {
                int cur2 = num2.charAt(j) - '0';
                int result = cur1 * cur2 + ans[i+j+1];
                //重点在这个 下标的对应， 举个简单例子算一下就知道了
                ans[i + j + 1] = result % 10;
                ans[i + j] += result / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (sb.length() == 0 && ans[i] == 0) continue; // Skip leading zeros
            sb.append(ans[i]);
        }

        return sb.toString();
    }
}
