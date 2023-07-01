package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class MinimumAbsDiff_L530 {
    private Integer pre = null;
    private int result = Integer.MAX_VALUE;

    // bst 中序遍历后变成有序数组， 那么最小 差值的绝对值， 即一次比较相邻元素的差值， 然后记录最小差值即可。

    public int getMinimumDifference(TreeNode root) {
        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        if (pre != null) {
            int cur = root.val - pre;
            result = Math.min(result, cur);
        }
        pre = root.val;
        traverse(root.right);
    }
}
