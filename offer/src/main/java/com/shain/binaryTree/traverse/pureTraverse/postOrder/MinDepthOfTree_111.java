package com.shain.binaryTree.traverse.pureTraverse.postOrder;

import com.shain.common.tree.TreeNode;

public class MinDepthOfTree_111 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // 左子树或者又子树为空的情况下， 不能记录当前最小深度就是1.
        if (left == 0) {
            return 1 + right;
        }
        if (right == 0) {
            return 1 + left;
        }
        return 1 + Math.min(left, right);
    }

    // 从上至下
    private int result;
    public int minDepth_v2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        result = Integer.MAX_VALUE;
        traverse(root, 1);
        return result;
    }

    private void traverse(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result = Math.min(depth, result);
            return;
        }

        traverse(root.left, depth+1);
        traverse(root.right, depth+1);
    }
}
