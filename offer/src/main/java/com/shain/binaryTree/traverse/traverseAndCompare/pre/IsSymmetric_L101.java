package com.shain.binaryTree.traverse.traverseAndCompare.pre;

import com.shain.common.tree.TreeNode;

public class IsSymmetric_L101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;

        return doCheck(root.left, root.right);
    }

    private boolean doCheck(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val){
            return false;
        }

        return doCheck(left.left, right.right) && doCheck(left.right, right.left);
    }
}
