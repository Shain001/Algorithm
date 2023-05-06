package com.shain.binaryTree.traverse.modifyTree;

import com.shain.common.tree.TreeNode;

public class InvertBinaryTree_L226 {
    public TreeNode invertTree(TreeNode root) {
        //doInvert(root);
        return doInvert_v2(root);
    }

    private void doInvert(TreeNode root) {
        if (root == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        doInvert(root.left);
        doInvert(root.right);
    }

    private TreeNode doInvert_v2(TreeNode root) {
        if (root == null)
            return null;
//        this version is wrong because the root.right is lost.
//        after line 29, root.right has been override by inverted root.left, which means the original value of root.right
//        is lost, so in line 30, the root.right being passed to the method is not the original root.right.
//         the way to fix is add this to the beginning: TreeNode temp = root.right, then line 30 should be root.left = do(temp)
//        root.right = doInvert_v2(root.left);
//        root.left = doInvert_v2(root.right);
        TreeNode left = doInvert_v2(root.left);
        TreeNode right = doInvert_v2(root.right);
        root.left = right;
        root.right = left;

        return root;
    }
}
