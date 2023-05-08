package com.shain.binaryTree.traverse.traverseAndCompare.commonAncestor;

import com.shain.common.tree.TreeNode;

public class CABst_L235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val)
            return doFind(root, q, p);
        return doFind(root, p, q);
    }

    public TreeNode doFind(TreeNode root, TreeNode small, TreeNode big) {
        if (root == null)
            return null;

        if (root.val < small.val)
            return doFind(root.right, small, big);

        if (root.val > big.val)
            return doFind(root.left, small, big);

        // if reach here, means big > root.val >= small, which means this is the common ancestor
        // Since in BST, only one node can be bigger than small and smaller than big
        // and if root = small, means small is the result and will be returned
        return root;
    }
}
