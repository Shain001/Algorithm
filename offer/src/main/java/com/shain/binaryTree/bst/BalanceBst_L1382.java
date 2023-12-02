package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 得到中序遍历数组， 然后按照L108方法构建
public class BalanceBst_L1382 {
    private List<Integer> inorder;

    public TreeNode balanceBST(TreeNode root) {
        inorder = new ArrayList<>();
        getInorder(root);
        return doConstruct(0, inorder.size()-1);
    }

    private void getInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        getInorder(root.left);
        inorder.add(root.val);
        getInorder(root.right);
    }

    private TreeNode doConstruct(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right-left)/2;
        TreeNode root = new TreeNode(inorder.get(mid));
        root.left = doConstruct(left, mid-1);
        root.right = doConstruct(mid+1, right);
        return root;
    }
}
