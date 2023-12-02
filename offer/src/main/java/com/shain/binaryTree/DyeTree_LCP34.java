package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;
import com.sun.source.tree.Tree;

public class DyeTree_LCP34 {
    /**
     * 定义dp数组为： 对于当前节点， 连续染色的节点有i个时的最大值sum。 （dp数组定义有点问题。 等chatgpt）
     * 注意， 题意要求为 连续染色 不超过 k 次， 而非一定是k次。
     * <p>
     * 对于每一个节点， 有两种选择： 选与不选。
     * <p>
     * 不选： 则 左右 两个 子树 的节点互相一定不连续。 那么只要保证 两个子树中 连续染色节点都不超过k个即可
     * 即 -> dp[0] = max(dpLeft) + max(dpRight)  ---> 这里的dp0 处的定义 感觉与dp数组定义不符。 但是懒得想了。
     * <p>
     * 选： 则， 假设 left中连续染色节点有 i 个， right中有j个。 则， i+j+ 1 应该等于 m， m为dp下标。
     * 即： dp[i] = max（dp[i] + dp[m-i-1] + root.val）
     */
    private int k;

    public int maxValue(TreeNode root, int k) {
        this.k = k;
        // 注意， 这里返回的是数组中的最大值， 而非一定是 dp-1， 因为 不是一定要有k个连续节点， 小于k也可以
        return getMaxValue(getMaxValue(root));
    }

    private int[] getMaxValue(TreeNode root) {
        if (root == null) {
            return new int[k];
        }

        int[] left = getMaxValue(root.left);
        int[] right = getMaxValue(root.right);

        int[] dp = new int[k+1];

        dp[0] = getMaxValue(left) + getMaxValue(right);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                // 这里的 max dp[i] 是在更新 dpi 为 最大的 left[j] + right[i-j-1] + root.val
                dp[i] = Math.max(dp[i], left[j] + right[i-j-1] + root.val);
            }
        }

        return dp;
    }

    private int getMaxValue(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (Integer n: nums) {
            max = Math.max(max, n);
        }

        return max;
    }
}
