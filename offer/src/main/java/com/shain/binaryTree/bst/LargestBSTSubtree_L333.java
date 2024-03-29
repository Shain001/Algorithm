package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

public class LargestBSTSubtree_L333 {
    public int largestBSTSubtree(TreeNode root) {
        return doTraverse(root)[1];
    }

    /**
     * int[0] -> 以当前节点为根的子树是否是bst （1=true -1=false）; int[1] -> 以当前节点为根的 "子树中"， 符合bst的子树的节点数量
     * int[2] -> 以当前节点为根的子树的MaxValue; int[3] -> 以当前节点为根的子树的 MinValue
     * <p>
     * One thing to Note:
     * 判断以 某节点为根的树是不是BST， 要同时满足以下两个条件：
     * 1. 要满足当前value > 左侧MaxValue && < 右侧MinValue
     * 2. 并且 左右子树都是BST
     * <p>
     * 注意 第1 点， 比较的是 Max&Min， 而不是 root.left.val & root.right.val
     *
     * @param root
     * @return
     */
    public int[] doTraverse(TreeNode root) {
        if (root == null) {
            // 对于空节点， 其不影响父节点是否是BST， 所以赋值为int[0]=1, int2,3 = Min & Max ->  使得 line 33&36一定为true。
            return new int[]{1, 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        }

        int[] left = doTraverse(root.left);
        int[] right = doTraverse(root.right);

        // 1. 判断当前节点是否大于左最大以及小于右最小。 -> Point 1 at line 16
        boolean flag = root.val > left[2] && root.val < right[3];

        // 2. Point 2 at line 17
        if (flag && left[0] == 1 && right[0] == 1) {
            // !!NOTE: 这里的 Math.max 和 Math.min， 其实只在 从最后一层空节点跨越到 叶子节点时发生了作用， 或者换句话说，
            // 他俩的作用仅仅是初始化 min 和max。
            // 因为 只要 root为根的tree 满足 flag的条件， 就代表他是合法的bst， 那么 这个bst的最大值， 一定是 right的最大值， 即 right[2]， 最小值一定是 左子树的最小值， 即left[3]
            // 注意复习到这里不要被迷惑。
            return new int[]{1, 1 + left[1] + right[1], Math.max(root.val, right[2]), Math.min(root.val, left[3])};
        }

        // 如果当前节点已经不是BST， 则不用在care 最大最小值是多少， 因为当前节点以上的所有父节点，都不可能再是 BST。
        return new int[]{-1, Math.max(left[1], right[1]), -1, -1};
    }
}
