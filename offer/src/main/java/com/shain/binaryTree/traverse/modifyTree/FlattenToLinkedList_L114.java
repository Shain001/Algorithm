package com.shain.binaryTree.traverse.modifyTree;

import com.shain.common.tree.TreeNode;

public class FlattenToLinkedList_L114 {
    public void flatten(TreeNode root) {
        doFlatten(root);
    }

    // You can't simply do pre traverse
    // This version lost all the right children's values
    private void wrongVersion(TreeNode root) {
        if (root == null)
            return;

        TreeNode tempRight = root.right;
        TreeNode tempLeft = root.left;
        root.right = root.left;
        root.left = null;
        wrongVersion(tempLeft);
        wrongVersion(tempRight);
    }

    // This is the right version that corrects the mistake made above.
    // Firstly, since the above code lost all the right children's value, so what we need to do
    // is to add the right children's value.
    // Then, to do that, we have to ensure that the connected originalRight tree is already flatten.
    // This is why the recursive step has to be moved above, which means this becomes
    // you modify the tree from the bottom to top, instead of while traversing to the bottom.
    private void doFlatten(TreeNode root) {
        if (root == null)
            return;

        doFlatten(root.right);
        doFlatten(root.left);

        TreeNode originalRight = root.right;
        root.right = root.left;
        root.left = null;

        while (root.right != null) {
            root = root.right;
        }

        root.right = originalRight;
    }
}
