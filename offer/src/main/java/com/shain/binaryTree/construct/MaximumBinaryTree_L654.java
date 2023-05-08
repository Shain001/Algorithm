package com.shain.binaryTree.construct;

import com.shain.common.tree.TreeNode;

public class MaximumBinaryTree_L654 {
    private int[] nums;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int right = nums.length - 1;
        this.nums = nums;
        return doConstruct(0, right);
    }

    private TreeNode doConstruct(int left, int right) {
        if (left > right) {
            return null;
        }

        int maxIndex = getMaximumIndex(left, right);
        int leftChildRight = maxIndex - 1;
        int rightChildLeft = maxIndex + 1;
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = doConstruct(left, leftChildRight);
        root.right = doConstruct(rightChildLeft, right);
        return root;
    }

    public int getMaximumIndex(int left, int right) {
        int curMax = Integer.MIN_VALUE;
        int curMaxIndex = -1;

        for (int i = left; i <= right; i++) {
            if (nums[i] > curMax) {
                curMax = nums[i];
                curMaxIndex = i;
            }
        }

        return curMaxIndex;
    }
}
