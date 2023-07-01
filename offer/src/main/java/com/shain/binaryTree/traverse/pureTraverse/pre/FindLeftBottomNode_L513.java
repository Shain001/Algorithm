package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

public class FindLeftBottomNode_L513 {
    private int result;
    private int depth;

    public int findBottomLeftValue(TreeNode root) {
        this.result = -1;
        this.depth = -1;
        getLeftMost(root, 1);
        return result;
    }

    private void getLeftMost(TreeNode root, int curDepth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && curDepth > this.depth) {
            result = root.val;
            this.depth = curDepth;
            return;
        }

        // 由于此处是前序遍历， 即 永远是先走左节点， 所以就算走到 最底层叶子节点那一层的时候， 左右节点都有值， 在if判断中也是记录的左侧节点的值， 所以保证了是最左侧节点。
        getLeftMost(root.left, curDepth + 1);
        getLeftMost(root.right, curDepth + 1);

    }
}
