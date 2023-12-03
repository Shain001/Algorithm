package com.shain.binaryTree.traverse.pureTraverse.postOrder;

import com.shain.common.tree.TreeNode;

public class MinDepthOfTree_111 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // 保证是叶子节点。 若只有 一个子树为空， 不是答案
        if (left != 0 && right != 0)
            return Math.min(left,right)+1;

        return left == 0? right+1: left+1;
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
