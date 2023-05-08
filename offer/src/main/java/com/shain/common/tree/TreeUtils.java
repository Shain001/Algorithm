package com.shain.common.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {
    public static TreeNode getTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < array.length; i += 2) {
            TreeNode current = queue.poll();

            if (array[i] != null) {
                current.left = new TreeNode(array[i]);
                queue.offer(current.left);
            }

            if (i + 1 < array.length && array[i + 1] != null) {
                current.right = new TreeNode(array[i + 1]);
                queue.offer(current.right);
            }
        }

        return root;
    }
}
