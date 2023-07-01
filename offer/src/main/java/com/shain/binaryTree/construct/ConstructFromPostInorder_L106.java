package com.shain.binaryTree.construct;

import com.shain.common.tree.TreeNode;

public class ConstructFromPostInorder_L106 {
    int[] postorder;
    int[] inorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        return getTreeNode(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode getTreeNode(int iStart, int iEnd, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) {
            return null;
        }

        int curVal = postorder[pEnd];

        int rootIndex = iStart;
        for (int i = iStart; i <= iEnd; i++) {
            if (inorder[i] == curVal) {
                rootIndex = i;
                break;
            }
        }

        int leftTreeSize = rootIndex - iStart;

        TreeNode cur = new TreeNode(curVal);
        cur.left = getTreeNode(iStart, rootIndex - 1, pStart, pStart + leftTreeSize - 1);
        cur.right = getTreeNode(rootIndex + 1, iEnd, pStart + leftTreeSize, pEnd - 1);

        return cur;
    }

}
