package com.shain.dp.pathSum_actuallyTree.upBottomTraverse;

import java.util.List;

public class MinPathTriangle_L120 {
    public static void main(String[] args) {
        var test = new MinPathTriangle_L120();

    }

    public int minimumTotal(List<List<Integer>> triangle) {
//        int[] dp = new int[triangle.get(triangle.size()-1).size()];
//
//        for (List<Integer> cur : triangle) {
//            for (int i = cur.size() - 1; i >= 0; i--) {
//                if (i >= 1)
//                    dp[i] = Math.min(dp[i], dp[i - 1]) + cur.get(i);
//                else
//                    dp[i] = cur.get(i) + dp[i];
//            }
//        }
//
//        int result = Integer.MAX_VALUE;
//        for (Integer n: dp) {
//            result = Math.min(result, n);
//        }
//
//        return result;

        // chatgpt改的， 上面代码 更新dp值时候有问题， 方程应该是对的， 但是好像用到了dp的错误值。 懒得debug了。
        int[] dp = new int[triangle.get(triangle.size() - 1).size()];

        for (List<Integer> cur : triangle) {
            int prev = dp[0];  // 保存 dp[0] 的旧值
            for (int i = 0; i < cur.size(); i++) {
                int temp = dp[i];  // 保存 dp[i] 的旧值
                if (i > 0 && i < cur.size() - 1) {
                    dp[i] = Math.min(prev, temp) + cur.get(i);
                } else if (i == 0) {
                    dp[i] = temp + cur.get(i);
                } else {  // i == cur.size() - 1
                    dp[i] = prev + cur.get(i);
                }
                prev = temp;  // 更新 prev 的值
            }
        }

        int result = Integer.MAX_VALUE;
        for (Integer n : dp) {
            result = Math.min(result, n);
        }

        return result;
    }
}
