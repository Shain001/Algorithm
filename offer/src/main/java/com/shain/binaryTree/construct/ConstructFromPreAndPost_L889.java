package com.shain.binaryTree.construct;


import com.shain.common.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * post: [ (left), (right), root]
 * pre: [root, (left), (right)]
 * <p>
 * Still, we can find the "root value of the 'left or right' child from pre array" (let's call it RC).
 * Then, in the post array, find the index of RC, all the elements on the left of RC in post array, would be the number
 * of left child, on the right of it is the right children
 * <p>
 * It's left or right, because there might be no left children, but we cannot identify that, and the leetcode algorithms
 * know this may happen, so it doesn't matter
 */
public class ConstructFromPreAndPost_L889 {
    private int[] preorder;
    private int[] postorder;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;

        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        return doConstruct(0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode doConstruct(int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight || postLeft > postRight)
            return null;

        int curVal = preorder[preLeft];

        TreeNode root = new TreeNode(curVal);

        // if preLeft+1 > preRight, that means current subtree only has one node which is itself,
        // This means current root doesn't have any children, either left or right,
        // so that we just return root is fine.
        if (preLeft + 1 <= preRight) {
            int leftChildRoot = preorder[preLeft + 1];
            int leftChildRootIndex = map.get(leftChildRoot);
            // don't forget +1 here, since the leftChildRootIndex is the index of the next level's root, it's part of
            // left children
            int numberOfLeft = leftChildRootIndex - postLeft + 1;
            root.left = doConstruct(preLeft + 1, preLeft + numberOfLeft, postLeft, leftChildRootIndex);
            // don't forget postRight-1 here, since we have to remove the current root value from post order arrary
            root.right = doConstruct(preLeft + numberOfLeft + 1, preRight, leftChildRootIndex + 1, postRight - 1);
        }

        return root;
    }

}
