package com.shain.binaryTree.traverse.completeBinaryTree;

import com.shain.common.tree.TreeNode;

public class CountNodesComplete_L222 {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = 0;
        TreeNode temp = root;
        while (temp != null) {
            leftHeight++;
            temp = temp.left;
        }

        int rightHeight = 0;
        temp = root;
        while (temp != null) {
            rightHeight++;
            temp = temp.right;
        }

        // the subtree is a perfect binary tree
        // And this if condition will definitely be gone into, because the input tree is a complete binary tree.
        // which means there must be a subtree that is a perfect binary tree.
        // Therefore, the time complexity is imporved by doing line 10 to line 28.
        if (rightHeight == leftHeight) {
            return (int) Math.pow(2, rightHeight) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
