package com.shain.binaryTree.traverse.traverseAndCompare.commonAncestor;

import com.shain.common.tree.TreeNode;

public class LCA1_L236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (p == root) {
            return p;
        }

        if (q == root) {
            return q;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) {
            return null;
        }

        if (left != null && right != null ){
            return root;
        }

        return left == null? right: left;

    }
}
