package com.shain.binaryTree.traverse.pureTraverse.bfs;

import com.shain.common.tree.TreeNode;

public class RestoreBST_L99 {
    TreeNode pre;
    TreeNode small;
    TreeNode big;
    public void recoverTree(TreeNode root) {
        inOrder(root);

        int temp = small.val;
        small.val = big.val;
        big.val = temp;

    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        if (pre != null && root.val < pre.val) {
            // 这里 需要理解一下
            // 因为有且只有两个元素错位， 这只可能有两种情况
            // 1. 错位的两个元素相邻 -> 中序遍历中只会产生一次逆序对 -> 交换两个元素即可, 也即对应着if中的条件。
            // 2. 错位的两个元素不相邻 -> 中序遍历中会产生两次逆序对
            // 在 2 的情况下， 由于我们知道 "只交换了两个元素"， 所以 "第二个逆序对中， 一定包含着的是 较小的那个元素"
            // 因为 只交换了两个元素。
            // 所以 如果 inorder过程中 发现了第二个逆序对， 则第二个逆序对中一定是 value更小的那个元素， 则我们直接更新 small 为root。
            // 为什么是 root？ 因为 外层if条件中 我们的判断条件是  root.val < pre.val， 总之就是找 更小的那一个就对了。
            if (small == null) {
                small = root;
                big = pre;
            } else {
                small = root;
            }
        }
        pre = root;
        inOrder(root.right);
    }
}
