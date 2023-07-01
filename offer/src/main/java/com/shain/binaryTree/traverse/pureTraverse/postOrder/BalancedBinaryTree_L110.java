package com.shain.binaryTree.traverse.pureTraverse.postOrder;

import com.shain.common.tree.TreeNode;

public class BalancedBinaryTree_L110 {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) == -1? false: true;
    }

    // 返回以当前节点为根节点的 树的最大高度， 并且检查左右子树的高度差是否<1
    // 如果 左右子树高度差 < 1， 返回当前树的最大高度， 否则返回-1
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (left == -1 || right == -1)
            return -1;

        return Math.abs(left - right) > 1? -1: 1 + Math.max(left, right);
    }
}
