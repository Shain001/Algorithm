package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class InsertToBST_L701 {
    // 只需记得递归方法的作用： 插入， 并返回 新的头节点。
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
