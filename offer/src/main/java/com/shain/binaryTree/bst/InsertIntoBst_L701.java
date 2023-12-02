package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class InsertIntoBst_L701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
            return root;
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
            return root;
        }

        return root;
    }

    public TreeNode insertIntoBST_iterate(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode dumpHead = root;
        TreeNode pre = root;
        while (root != null) {
            while (root != null && root.val > val) {
                pre = root;
                root = root.left;
            }
            while (root != null && root.val < val) {
                pre = root;
                root = root.right;
            }

            if (root != null && root.val == val) {
                return dumpHead;
            }
        }

        if (pre.val > val) {
            pre.left = new TreeNode(val);
        }

        if (pre.val < val) {
            pre.right = new TreeNode(val);
        }

        return dumpHead;

    }
}
