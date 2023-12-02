package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class UniqueBst2_L95 {
    private List<TreeNode> result;
    public List<TreeNode> generateTrees(int n) {
        return construct(1, n);
    }

    private List<TreeNode> construct(int left, int right) {
        List<TreeNode> curLevelHeads = new LinkedList<>();

        if (left > right) {
            // 这里必须加一个null， 不然后面的双层for 有可能进不去
            curLevelHeads.add(null);
            return curLevelHeads;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftHeads = construct(left, i-1);
            List<TreeNode> rightHeads = construct(i+1, right);

            for (TreeNode leftHead: leftHeads) {
                for (TreeNode rightHead: rightHeads) {
                    TreeNode head = new TreeNode(i);
                    head.left = leftHead;
                    head.right = rightHead;
                    curLevelHeads.add(head);
                }
            }
        }

        return curLevelHeads;
    }
}
