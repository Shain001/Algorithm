package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

/**
 * 这题第一点要注意 从下向上遍历更方便。 开始想的从上倒下， 应该能做， 但是麻烦。
 *
 * 其次， 难点跟 L124 一样。 即 人字形 的路径是不符合题意的。
 * 处理方法也跟 L124 一样。 即 返回值是 单一路径。 result 是 左右路径都有
 */
public class LongestUniValuePath_L687 {
    private int result;

    private int bottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = bottomUp(root.left);
        int right = bottomUp(root.right);

        int leftTemp = 0;
        int rightTemp = 0;
        if (root.left != null && root.left.val == root.val) {
            leftTemp = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightTemp = right + 1;
        }


        result = Math.max(leftTemp + rightTemp, result);

        return Math.max(leftTemp, rightTemp);
    }
}
