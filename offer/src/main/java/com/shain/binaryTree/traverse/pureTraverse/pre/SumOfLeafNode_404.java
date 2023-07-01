package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

public class SumOfLeafNode_404 {
    private int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        doTraverse(root);
        return result;
    }

    private void doTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result += root.left.val;
        }

        doTraverse(root.left);
        doTraverse(root.right);
    }
}
