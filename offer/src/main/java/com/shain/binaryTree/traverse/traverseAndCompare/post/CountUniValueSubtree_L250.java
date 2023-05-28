package com.shain.binaryTree.traverse.traverseAndCompare.post;

import com.shain.common.tree.TreeNode;

public class CountUniValueSubtree_L250 {
    private int count=0;

    public int countUnivalSubtrees(TreeNode root) {
        isUniValueSubutree(root);
        return count;
    }

    private boolean isUniValueSubutree(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftIsUnique = isUniValueSubutree(root.left);
        boolean rightIsQunique = isUniValueSubutree(root.right);

        boolean isEqual = false;

        if (root.right != null && root.left != null) {
            isEqual = root.val == root.right.val && root.val == root.left.val;
        } else if (root.right == null && root.left != null) {
            isEqual = root.val == root.left.val;
        } else if (root.left == null && root.right != null) {
            isEqual = root.val == root.right.val;
        } else {
            // leaf node
            isEqual = true;
        }

        if (isEqual && rightIsQunique && leftIsUnique) {
            count ++;
            return true;
        }

        return false;
    }
}
