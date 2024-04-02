package com.shain.binaryTree.construct;

import com.shain.common.tree.TreeNode;

public class UpDownReverseTree_L156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        return traverse(root);

    }

    private TreeNode traverse(TreeNode root) {
        if (root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);

        root.left.left = root.right; // 新的左子节点是原来的右子节点
        root.left.right = root; // 新的右子节点是原来的根节点
        root.left = null; // 原来的左子节点设置为null
        root.right = null; // 原来的右子节点设置为null

        return newRoot;
    }
}
