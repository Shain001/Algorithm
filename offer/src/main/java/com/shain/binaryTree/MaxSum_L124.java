package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

// 这题的难点在于， 以下这种路径是不符合题意的
//     1
//    /
//   2
//  / \
// 3   4
// 解决方法如 method注释中所写
public class MaxSum_L124 {
    private int result;
    public int maxPathSum(TreeNode root) {
        this.result = Integer.MIN_VALUE;
        getMaxSum(root);
        return result;
    }

    // 函数作用为： 返回以 当前节点为根节点的 一条 只包含左侧path 或 右侧 path 的最大和。
    // 同时 更新result
    // 注意， result是可以即包含左侧路径又包含右侧路径的。
    private int getMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getMaxSum(root.left);
        int right = getMaxSum(root.right);

        // 左，右路径 最大和 如果为负数， 那么不如不选
        int leftMax = Math.max(left, 0);
        int rightMax = Math.max(right, 0);

        // 更新答案， 答案的路径是可以包含 人字形的， 所以 左右路径都可以加上。
        int curMax = root.val + leftMax + rightMax;
        result = Math.max(curMax, result);

        // 以当前节点为根结点的 最大路径和， 注意， 这里的路径仅限于 单边， 不包含人字形。
        return Math.max(root.val, Math.max(left, right) + root.val);
    }
}
