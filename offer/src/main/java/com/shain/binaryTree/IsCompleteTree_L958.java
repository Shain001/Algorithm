package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 在层序遍历所有节点过程中， 但凡遇到过一次 null， 不管是 在一层中， 还是 不同层中， 只要遇到过一次null， 以后就不可能再遇到非空节点。
 * 如果满足以上条件， 即为 完全二叉树， 否则不是。
 */
public class IsCompleteTree_L958 {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean flag = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();
                size--;

                if (cur == null) {
                    flag = true;
                    continue;
                } else {
                    if (flag) {
                        return false;
                    }

                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }

        return true;
    }
}
