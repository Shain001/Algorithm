package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class DeleteNodeFromBst_L450 {
    // func作用为： 在以当前root为根的 树 中， 删除key节点，并返回重新排序过的 root。　
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        // 找到key
        // 1. 叶子节点，直接删除。
        if (root.right == null && root.left == null) {
            return null;
        }
        // 2. 没有左子树, 直接返回right
        if (root.left == null)
            return root.right;
        // 3. 没有right
        if (root.right == null)
            return root.left;

        // 找到 right中的最小值， 其应该为新的root
        int val = findLeftMost(root.right);
        // 在right 中删除 最小值
        TreeNode newRight = deleteNode(root.right, val);
        // 创建新的头节点， 并且链接left 和 right；
        TreeNode newRoot = new TreeNode(val);
        newRoot.left = root.left;
        newRoot.right = newRight;

        return newRoot;
    }

    private int findLeftMost(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
