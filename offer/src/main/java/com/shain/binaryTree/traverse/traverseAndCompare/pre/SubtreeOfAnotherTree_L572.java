package com.shain.binaryTree.traverse.traverseAndCompare.pre;

import com.shain.common.tree.TreeNode;

public class SubtreeOfAnotherTree_L572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // root null; sub null -> ture
        // root !null; sub null -> false
        // root null; sub !null -> false
        // root !null; sub !null -> compare
        if (root == null && subRoot == null)
            return true;
        if (root == null || subRoot == null)
            return false;
        if (doCheck(root, subRoot))
            return true;

        return isSubtree(root.right, subRoot) ||
                isSubtree(root.left, subRoot);
    }

    public boolean doCheck(TreeNode root, TreeNode subRoot) {
        // 只要subnode为null， 就返回true
        if (subRoot == null && root == null)
            return true;
        if (root == null || subRoot == null)
            return false;
        if (subRoot.val == root.val)
            return doCheck(root.left, subRoot.left) && doCheck(root.right, subRoot.right);
        else
            return false;

    }
}
