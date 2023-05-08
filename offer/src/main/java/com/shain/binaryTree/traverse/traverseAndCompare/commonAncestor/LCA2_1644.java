package com.shain.binaryTree.traverse.traverseAndCompare.commonAncestor;

import com.shain.common.tree.TreeNode;

public class LCA2_1644 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        // wrong version.
        // The point of the "LOWEST" common ancestor, means "have to do post-order traverse", i.e. you have to check the
        // roots from bottom to up.
        // If you use post-order, then you can find the ancestor, but it's not gonna be the lowest one,
        // it will always be the root node.
//        if (containsNode(root, p) && containsNode(root, q))
//            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null)
            return left;

        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (right != null)
            return right;

        // This line put here, also achieves "post-order traverse";
        // You wrote this by yourself, but not too familiar, so have a double review.
        if (containsNode(root, p) && containsNode(root, q))
            return root;

        return null;
    }

    private boolean containsNode(TreeNode root, TreeNode target) {
        if (root == null)
            return false;

        if (root == target)
            return true;

        return containsNode(root.right, target) || containsNode(root.left, target);
    }
}
