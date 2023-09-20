package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

/**
 * update 20/09/2023
 * 注意对于 "找到 左侧叶子节点， 或 右侧叶子节点的 判断条件"
 * 今晚错了两次了。 注意不可以写成 wrong version 那样的判断， 简单的 v1 中那样判断就可以了。
 */
public class SumOfLeafNode_404 {
    private int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        doTraverse(root);
        return result;
    }

    private void doTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result += root.left.val;
        }

        doTraverse(root.left);
        doTraverse(root.right);
    }

    /**
     * Wrong version:
     * 注意不可以这么写， 对于 用例 [1,2,3,4,null,null,5] 会出错， 因为在 第一个if 中错误的return了
     *
     *
     *
     * public int sumOfLeftLeaves(TreeNode root) {
     *         traverse(root);
     *         return sum;
     *     }
     *
     *     private void traverse(TreeNode root) {
     *         if (root == null || root.left == null) {
     *             return;
     *         }
     *
     *         if (root.left.right == null && root.left.right == null) {
     *             sum += root.left.val;
     *         }
     *         traverse(root.left);
     *         traverse(root.right);
     *     }
     */
}
