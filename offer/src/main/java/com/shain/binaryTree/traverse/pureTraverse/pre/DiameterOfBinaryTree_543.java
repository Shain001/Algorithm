package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

/**
 * Diameter means the length of the longest path between any two nodes.
 *
 *
 */
public class DiameterOfBinaryTree_543 {
    private int longest = 0;
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
        longest = Math.max(longest, leftLongest+rightLongest);

        return Math.max(leftLongest, rightLongest) + 1;
    }

}
