package com.shain.binaryTree.traverse.traverseAndCompare.post;

import com.shain.common.tree.TreeNode;

public class CountUniValueSubtree_L250 {
    private int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        isUniValueSubutree(root);
        return count;
    }

    private boolean isUniValueSubutree(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftIsUnique = isUniValueSubutree(root.left);
        boolean rightIsQunique = isUniValueSubutree(root.right);

        boolean isEqual = false;

        if (root.right != null && root.left != null) {
            isEqual = root.val == root.right.val && root.val == root.left.val;
        } else if (root.right == null && root.left != null) {
            isEqual = root.val == root.left.val;
        } else if (root.left == null && root.right != null) {
            isEqual = root.val == root.right.val;
        } else {
            // leaf node
            isEqual = true;
        }

        if (isEqual && rightIsQunique && leftIsUnique) {
            count++;
            return true;
        }

        return false;
    }

    // update 21/09/2023 用了数组
    public int countUnivalSubtrees_v2(TreeNode root) {
        return count(root)[0];
    }

    private int[] count(TreeNode root) {
        if (root == null) {
            return new int[]{0, 1};
        }

        int[] left = count(root.left);
        int[] right = count(root.right);

        if (left[1] == 0 || right[1] == 0) {
            return new int[]{left[0]+right[0], 0};
        }

        if (root.left == null && root.right == null) {
            return new int[]{1, 1};
        }
        if (root.left == null) {
            return root.right.val == root.val ? new int[]{1+right[0], 1} : new int[]{right[0],0};
        }
        if (root.right == null) {
            return root.left.val == root.val? new int[]{1+left[0], 1}: new int[]{left[0], 0};
        }
        if (root.val == root.right.val && root.val == root.left.val) {
            return new int[]{1 + left[0] + right[0], 1};
        }

        return new int[]{left[0]+right[0],0};
    }
}
