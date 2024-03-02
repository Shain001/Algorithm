package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class NextInOrderNode_L285 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // p==null 是输入检测
        // 如果遍历到root为null 还没找到target
        // 意味着 要么 p 不在 tree中 （该题条件应该不包含这一情况）
        // 要么 p 即为 整个树中最大的节点， 也即最 右下角的节点， 故返回null
        if (root == null || p == null) {
            return null;
        }
        // 如果 当前 root > p, 那么 p 必定在左子树之中， 而对于目标 节点 “距离p最近的， 也即与p直接相连的根结点 target” 有两种情况：
        // 1. 当前节点就是 target
        // 2. target 在 当前节点的 左子树之中
        if (root.val > p.val) {
            TreeNode target = inorderSuccessor(root.left, p);
            if (target == null) {
                return root;
            } else {
                return target;
            }
        }

        // 如果 当前root 小于 p， 则p一定在 右子树当中， 而 大于p的第一个跟节点不可能是 当前节点， 所以直接返回 在右子树中的搜寻结果
        return inorderSuccessor(root.right, p);
    }
}
