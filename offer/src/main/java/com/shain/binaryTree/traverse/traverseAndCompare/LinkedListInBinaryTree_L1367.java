package com.shain.binaryTree.traverse.traverseAndCompare;

import com.shain.common.linkList.ListNode;
import com.shain.common.tree.TreeNode;

public class LinkedListInBinaryTree_L1367 {
    public boolean isSubPath(ListNode listHead, TreeNode treeRoot) {
        if (listHead == null)
            return true;
        if (treeRoot == null)
            return false;

        if (doCheck(treeRoot, listHead))
            return true;
        return isSubPath(listHead, treeRoot.left) || isSubPath(listHead, treeRoot.right);
    }

    public boolean doCheck(TreeNode treeRoot, ListNode listHead) {
        if (listHead == null)
            return true;
        if (treeRoot == null)
            return false;
        if (treeRoot.val != listHead.val)
            return false;
        return doCheck(treeRoot.left, listHead.next) ||
                doCheck(treeRoot.right, listHead.next);
    }
}
