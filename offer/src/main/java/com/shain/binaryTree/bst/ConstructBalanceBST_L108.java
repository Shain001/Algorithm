package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class ConstructBalanceBST_L108 {
    private int[] nums;

    // 只有中序数列， 无论是否限制construct出来的树平衡， 都无法唯一确定一颗bst
    // 题目要求的平衡， 只需每次选择数组中间的节点作为根即可。 这有一前提是数组是有序的。
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return doConstruct(0, nums.length-1);
    }

    private TreeNode doConstruct(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right-left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = doConstruct(left, mid-1);
        root.right = doConstruct(mid+1, right);
        return root;
    }
}
