package com.shain.binaryTree.traverse.levelTraverse;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelTraverse_L102 {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        traverse(queue);

        return result;
    }

    private void traverse(Deque<TreeNode> queue) {
        if (queue.size() == 0) {
            return;
        }

        List<Integer> curLevel = new ArrayList<>();
        int size = queue.size();
        while (size > 0) {
            TreeNode cur = queue.poll();
            curLevel.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            size--;
        }

        result.add(curLevel);
        traverse(queue);
    }

    public List<List<Integer>> levelOrder_recursive(TreeNode root) {
        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                curLevel.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                size--;
            }
            result.add(curLevel);
        }

        return result;
    }
}
