package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

public class MoveCoins_L979 {
    private int result;
    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return result;
    }

    // 0 -> sum of val
    // 1 -> num of node
    // 2 -> result
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int sumOfVal = left[0] + right[0]  + root.val;
        int numOfNode = left[1] + right[1] +1;

        result += Math.abs(sumOfVal - numOfNode);
        return new int[]{sumOfVal, numOfNode};
    }
}
