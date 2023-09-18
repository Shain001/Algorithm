package com.shain.binaryTree.traverse.pureTraverse.postOrder;

import com.shain.common.tree.TreeNode;

/**
 * Diameter means the length of the longest path between any two nodes.
 */
public class DiameterOfBinaryTree_543 {
    private int longest = 0;

    /**
     * v1 v2是一样的 。。
     * 但是v1 直接算得是边的数量
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        getLongest(root);
        return longest;
    }

    private int getLongest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLongest = getLongest(root.left);
        int rightLongest = getLongest(root.right);
        longest = Math.max(longest, leftLongest + rightLongest);

        return Math.max(leftLongest, rightLongest) + 1;
    }

    private int result;
    public int diameterOfBinaryTree_v2(TreeNode root) {
        traverse(root);
        return result-1;
    }

    /**
     * 函数作用为： 计算以root为根的树的最大高度， 并在遍历过程中， 更新result
     *
     * 逻辑为， 最长半径， 一定是 以 root 为根结点的子树中， 最长左子树高度 + 最长右子树高度 + 节点本身， 但由于 题目要的是 边 的数量， 所以result应该-1
     * @param root
     * @return
     */
    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        result = Math.max(left + right + 1, result);

        // 以当前树为跟的树的最大高度为 左/右子树最大高度+1
        return Math.max(left, right) + 1;
    }

}
