package com.shain.binaryTree.construct;

import com.shain.common.tree.TreeNode;

/**
 * Point:
 * Pre-Order: root -> left -> right
 * In-Order: left -> root -> right
 *
 * Which means:
 * the pre-order array would be like: [root, (left children), (right children)]
 * the in-order array would be like: [(left children), root, (right children)]
 *
 * Now, to construct a tree, what we need?
 * 1. root value
 * 2. left children values and left root
 * 3. right children values and right root
 *
 * So how can we get them?
 * 1. from the pre-order array, we can get root value, which is always the first element in array
 * 2. Then, what we have to determine, is the boundaries in line 11's array, i.e. the index of ().
 * 3. to get that index, we can use in-order array:
 *      3.1 identify the index of root value from in-order array
 *      3.2 count the number of elements from the left of root, and the right of root, which is the number of
 *          left children and right children
 *      3.3 identify the index of () in pre-order array
 */
public class ConstructFromPreAndInorder_L105 {
    private int[] pre;
    private int[] in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder;
        this.in = inorder;
        return doConstruct(0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode doConstruct(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight)
            return null;

        int rootVal = pre[preLeft];

        // get number of left
        int indexOfRoot = -1;

        // This for loop can be replaced by a hashmap storing the mapping of index and value.
        // Depends on you, it's only about you want to save some time or save some space.
        for (int i = inLeft; i <= inRight; i++){
            if (rootVal == in[i]){
                indexOfRoot = i;
                break;
            }
        }

        int numberOfLeft = indexOfRoot-inLeft;

        TreeNode root = new TreeNode(rootVal);
        root.left = doConstruct(preLeft+1, preLeft+numberOfLeft, inLeft, indexOfRoot-1);
        root.right = doConstruct(preLeft+numberOfLeft+1, preRight, indexOfRoot+1, inRight);

        return root;
    }
}
