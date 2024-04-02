package com.shain.binaryTree.construct;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 这题根 L95 构建所有可能的bst 一样。 没有区别
 *
 * 唯一要想到的就是：
 * 1。 真二叉树一定是奇数个节点
 * 2。 构建坐右子树的时候， 按照 左右子树 可能的节点数量递归。
 */
public class AllTruBinaryTree_L894 {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> roots = new ArrayList<>();
        if (n == 1) {
            roots.add(new TreeNode(0));
            return roots;
        }

        if (n %2 == 0) {
            return roots;
        }

        for (int leftCount = 1; leftCount < n; leftCount += 2 ) {
            List<TreeNode> lefts = allPossibleFBT(leftCount);
            List<TreeNode> rights = allPossibleFBT(n-leftCount-1);

            for (TreeNode left: lefts) {
                for (TreeNode right: rights) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    roots.add(root);
                }
            }
        }

        return roots;
    }
}
