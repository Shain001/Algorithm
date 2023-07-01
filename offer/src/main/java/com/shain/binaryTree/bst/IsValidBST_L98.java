package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class IsValidBST_L98 {
    public boolean isValidBST(TreeNode root) {
        return doCheck(root, null, null);
    }

    public boolean doCheck(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        // 注意， 不能简单的这样判断， 检查了当前节点的左子节点和右子节点的值，以确定其是否为有效的二叉搜索树。然而，这个方法没有考虑到节点的值应该符合整个子树的要求，而不仅仅是与直接的左右子节点比较

        // if (root.left != null && root.val <= root.left.val) {
        //     return false;
        // }

        // if (root.right != null && root.val >= root.right.val) {
        //     return false;
        // }

        if (min != null && root.val <= min) {
            return false;
        }

        if (max != null && root.val >= max) {
            return false;
        }

        // 注意这里， 对于左节点， 他的最大值就是 当前节点的值， 对于右节点， 最小值就是 当前节点的值。
        // 剩下的 关于 左子树的min， 右子树的max 交给递归处理。
        // 这是因为， 会出错的情况， 都是出现于： e.g. 一个节点在根结点的右侧， 但是在 父节点的左侧， 那么假如， 根结点是 10， 父节点是 15， 那么当前节点如果等于 8， 8 < 15, 但是不满足 8 > 10
        return doCheck(root.left, min, root.val) && doCheck(root.right, root.val, max);
    }
}
