package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class TrimBST_L669 {
    // 别想那么多， 只需要记得递归函数的意义， 即：
    // 返回修剪以后， 已经符合条件的头节点。
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return root;
        }

        // 如果合法， 则修剪左右子树
        if (root.val >= low && root.val <= high) {
            TreeNode left = trimBST(root.left, low, high);
            TreeNode right = trimBST(root.right, low, high);
            root.left = left;
            root.right = right;
            return root;
        } else if (root.val < low) { // 如果当前节点值<low, 代表所有左子树都不再可能合法， 直接返回修剪以后的 右子树的头节点/
            return trimBST(root.right, low, high);
        } else {
            return trimBST(root.left, low, high); // 同理
        }
    }
}
