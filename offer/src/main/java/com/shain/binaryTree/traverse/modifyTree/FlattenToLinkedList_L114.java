package com.shain.binaryTree.traverse.modifyTree;

import com.shain.common.tree.TreeNode;

public class FlattenToLinkedList_L114 {
    public void flatten(TreeNode root) {
        doFlatten(root);
    }

    TreeNode parent;

    public void flatten_preOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        root.left = null;
        if (parent != null) parent.right = root;
        parent = root;
        flatten(originalLeft);
        flatten(originalRight);
    }

    private void doFlatten(TreeNode root) {
        if (root == null)
            return;

        doFlatten(root.right);
        doFlatten(root.left);

        TreeNode originalRight = root.right;
        root.right = root.left;
        root.left = null;

        while (root.right != null) {
            root = root.right;
        }

        root.right = originalRight;
    }

    private TreeNode doFlatten_v2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = doFlatten_v2(root.left);
        TreeNode right = doFlatten_v2(root.right);

        root.left = null;
        root.right = left;

        TreeNode dumpRoot = root;
        while (root.right != null) {
            root = root.right;
        }

        root.right = right;

        return dumpRoot;
    }
}
