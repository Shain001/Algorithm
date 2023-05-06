package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

public class MaximumDepth_L104 {
    private int depth = 0;

    public int maxDepth(TreeNode root) {
        traverse(root, 0);
        return depth;
    }

    private void traverse(TreeNode root, int curDepth) {
        if (root == null) {
            depth = Math.max(depth, curDepth);
            return;
        }

        traverse(root.left, curDepth+1);
        traverse(root.right, curDepth+1);
    }
}
