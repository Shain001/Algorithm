package com.shain.binaryTree.traverse.pureTraverse.pre;

import com.shain.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这题利用 节点遍历的性质， 对比 v1 和 v11 的区别， 即 左-根-右， 右-根-左 的区别。
 * 同理还有v3中对于 左-根-右 的性质的应用
 */
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
        if (curDepth > this.depth) {
            ans = root.val;
            this.depth = curDepth;
        }

        // 由于此处是前序遍历， 即 永远是先走左节点， 所以就算走到 最底层叶子节点那一层的时候， 左右节点都有值， 在if判断中也是记录的左侧节点的值， 所以保证了是最左侧节点。
        dfs(root.left, curDepth + 1);
        dfs(root.right, curDepth + 1);
    }

    public int findBottomLeftValue_v11(TreeNode root) {
        getLeftMost_v11(root, 0);

        return result;
    }

    private void getLeftMost_v11(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (this.depth <= depth) {
                this.depth = depth;
                this.result = root.val;
            }

            return;
        }
        // 这里先走了 右节点， 所以同一层级的左侧node一定比右侧的node后被遍历， 因此 line50 的if中加了等于号。
        getLeftMost_v11(root.right, depth + 1);
        getLeftMost_v11(root.left, depth + 1);
    }

    public int findBottomLeftValue_v2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode result = root;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    result = cur;
                    queue.offer(cur.right);
                    queue.offer(cur.left);
                }
                size--;
            }
        }

        return result.val;
    }

    /**
     * 后续遍历版本。
     * <p>
     * 注意理解理解题意，要求找到 "最左侧的节点"， 这个最左侧节点不一定是 左节点， 也可能是某个节点的 右节点。
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue_v3(TreeNode root) {
        return getLeftMost_v3(root, 0)[0];
    }

    private int[] getLeftMost_v3(TreeNode root, int depth) {
        if (root == null) {
            return new int[]{-1, -1};
        }

        int[] left = getLeftMost_v3(root.left, depth + 1);
        int[] right = getLeftMost_v3(root.right, depth + 1);

        if (root.left == null && root.right == null) {
            return new int[]{root.val, depth};
        }

        // 此处的 等于号保证了如果是深度一样， 返回的是 "最左侧"
        if (left[1] >= right[1]) {
            return left;
        }

        return right;
    }


    // review 03/12 直接中序遍历
    private Integer ans = null;

    public int findBottomLeftValue_v(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        dfs(root.left, depth + 1);
        if (ans == null || depth > this.depth) {
            this.depth = depth;
            ans = root.val;
        }
        dfs(root.right, depth + 1);
    }
}
